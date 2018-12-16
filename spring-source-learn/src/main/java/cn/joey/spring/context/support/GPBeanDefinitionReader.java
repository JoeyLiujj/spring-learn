package cn.joey.spring.context.support;

import cn.joey.spring.beans.GPBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @auther liujiji
 * @date 2018/12/13 16:00
 */
public class GPBeanDefinitionReader {

    private Properties config =new Properties();

    public List<String> registryBeanClasses = new ArrayList<String>();

    private final String SCAN_PACKAGE = "scanPackage";

    public GPBeanDefinitionReader(String[] configLocations) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configLocations[0].replace("classpath:",""));

        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String packageName) {
        //得到一个包名，扫描该包下的所有文件

        URL url = this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.","/"));

        File urlFile = new File(url.getFile());
        File[] files = urlFile.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                doScanner(packageName+"."+file.getName());
            }else{
                registryBeanClasses.add(packageName+"."+file.getName().replace(".class",""));
            }
        }
    }

    public Properties getConfig(){
        return config;
    }

    public List<String> loadBeanDefinitions() {
        return this.registryBeanClasses;
    }

    public GPBeanDefinition registerBean(String className){
        if(this.registryBeanClasses.contains(className)){
            GPBeanDefinition beanDefinition =new GPBeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".")+1)));
            return beanDefinition;
        }
        return null;
    }
    public String lowerFirstCase(String str){
        char[] chars = str.toCharArray();
        chars[0]+=32;
        return new String(chars);
    }
}
