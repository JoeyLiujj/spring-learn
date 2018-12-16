package cn.joey.spring.context;

import cn.joey.spring.annotation.GPAutoWired;
import cn.joey.spring.annotation.GPController;
import cn.joey.spring.annotation.GPService;
import cn.joey.spring.aop.GPAopConfig;
import cn.joey.spring.beans.GPBeanDefinition;
import cn.joey.spring.beans.GPBeanPostProcessor;
import cn.joey.spring.context.support.GPBeanDefinitionReader;
import cn.joey.spring.core.GPBeanFactory;
import cn.joey.spring.beans.GPBeanWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther liujiji
 * @date 2018/12/13 14:00
 */
public class GPApplicationContext extends GPDefaultListableBeanFactory implements GPBeanFactory {

    private GPBeanDefinitionReader reader;
    private String[] configLocations;
    private Map<String, Object> beanCacheMap = new HashMap<String, Object>();
    private Map<String, GPBeanWrapper> beanWrapperMap = new ConcurrentHashMap<String, GPBeanWrapper>();

    public GPApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public void refresh(){
        //定位
        this.reader =new GPBeanDefinitionReader(configLocations);
        //加载
        List<String> beanDefinitions = reader.loadBeanDefinitions();

        //注册
        doRegistry(beanDefinitions);

        //依赖注入，配置了lazy-init属性的，在此进行依赖注入操作
        //调用getBean对象
        doAutoWired();

    }

    private void doRegistry(List<String> beanDefinitions) {
        //注册到ioc容器中

        //beanName有三种情况 默认是类名首字母小写 自定义名字 接口注入
        try {
            for(String beanName:beanDefinitions){
                Class<?> beanClass  = Class.forName(beanName);
                //如果是一个接口是不能初始化的
                if(beanClass.isInterface()) continue;

                GPBeanDefinition beanDefinition = reader.registerBean(beanName);
                if(beanDefinition!=null){
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                for(Class<?> i:interfaces){
                    //如果有多个实现类，只能覆盖
                    //
                    this.beanDefinitionMap.put(i.getName(),beanDefinition);
                }
                //到这里为止，容器初始化完毕
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void refreshBeanFactory() {

    }


    private GPAopConfig instantionAopConfig(GPBeanDefinition beanDefinition) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        GPAopConfig config = new GPAopConfig();
        String expression  =reader.getConfig().getProperty("pointCut");
        String[] before = reader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] after = reader.getConfig().getProperty("aspectAfter").split("\\s");

        String className = beanDefinition.getBeanClassName();
        Class<?> clazz = Class.forName(className);
        Pattern pattern = Pattern.compile(expression);

        Class<?> aspectClass = Class.forName(before[0]);
        for(Method method:clazz.getMethods()) {
            Matcher matcher = pattern.matcher(method.toString());
            if(matcher.find()){
                config.put(method,aspectClass.newInstance(),new Method[]{aspectClass.getMethod(before[1]),aspectClass.getMethod(after[1])});
            }

        }
       return config;
    }

    private void doAutoWired() {
        //依赖注入
        //此阶段初始化对象，以及注入属性  如果
        //将初始化的对象保存，并将属性注入到实例化的对象中
        for(Map.Entry<String,GPBeanDefinition> beanDefinitionEntry:this.beanDefinitionMap.entrySet()){
            String beanName = beanDefinitionEntry.getKey();

            if(!beanDefinitionEntry.getValue().isLazyInit()){
                Object obj = getBean(beanName);
            }
        }

        for(Map.Entry<String,GPBeanWrapper> beanWrapperEntry:this.beanWrapperMap.entrySet()){
            populateBean(beanWrapperEntry.getKey(),beanWrapperEntry.getValue().getOriginalInstance());
        }
    }

    private void populateBean(String key, Object instance) {
        Class clazz = instance.getClass();

        if(!(clazz.isAnnotationPresent(GPController.class)||clazz.isAnnotationPresent(GPService.class))){
            return ;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if(!clazz.isAnnotationPresent(GPAutoWired.class)){continue;}
            //获取GPAutoWired注解的值
            GPAutoWired autoWired = field.getAnnotation(GPAutoWired.class);
            String autoWiredName = autoWired.value();

            if ("".equals(autoWiredName)) {
                autoWiredName  = field.getType().getName();
            }
            field.setAccessible(true);

            try {
                field.set(instance,this.beanWrapperMap.get(autoWiredName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public Object getBean(String beanName) {
        GPBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

        String className = beanDefinition.getBeanClassName();

        try{
            GPBeanPostProcessor beanPostProcessor = new GPBeanPostProcessor();
            Object instance = instantionBean(beanDefinition);
            if(null==instance){
                return null;
            }
            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

            GPBeanWrapper beanWrapper = new GPBeanWrapper(instance);
            beanWrapper.setAopConfig(instantionAopConfig(beanDefinition));
            beanWrapper.setPostProcessor(beanPostProcessor);
            this.beanWrapperMap.put(beanName, beanWrapper);


            beanPostProcessor.postProcessAfterInitialization(instance,beanName);

            return this.beanWrapperMap.get(beanName).getWrapperInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Object instantionBean(GPBeanDefinition beanDefinition) {
        //实例化bean
        //将实例化后的对象放入到beanWrapperMap中
        Object instance = null;
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            if(this.beanCacheMap.containsKey(beanClassName)){
                instance = this.beanCacheMap.get(beanClassName);
            }else{
                Class<?> clazz = Class.forName(beanClassName);
                instance = clazz.newInstance();
                this.beanCacheMap.put(beanClassName, instance);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public String[] getBeanDefinitionNames(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }

}
