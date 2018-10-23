package com.taikang.common.propertyUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import com.taikang.common.safeUtil.SafeHelper;

public class TaiKangPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
	private static Map ctxPropertiesMap;
	
	private static Logger log = LoggerFactory.getLogger(TaiKangPropertyPlaceholderConfigurer.class);
	
    private static Resource[] locations;
    
    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

    @Override
    public void setLocations(Resource[] locations) {
        this.locations = locations;
    }

    

	@Override
	protected void convertProperties(Properties prop) {
		if (prop.containsKey("db.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("db.pass");
			value = sh.dec(value);
			prop.setProperty("db.pass", value);
		}
		if (prop.containsKey("db0.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("db0.pass");
			value = sh.dec(value);
			prop.setProperty("db0.pass", value);
		}
		if (prop.containsKey("db1.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("db1.pass");
			value = sh.dec(value);
			prop.setProperty("db1.pass", value);
		}
		if (prop.containsKey("db2.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("db2.pass");
			value = sh.dec(value);
			prop.setProperty("db2.pass", value);
		}
		if (prop.containsKey("db3.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("db3.pass");
			value = sh.dec(value);
			prop.setProperty("db3.pass", value);
		}
		if (prop.containsKey("db4.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("db4.pass");
			value = sh.dec(value);
			prop.setProperty("db4.pass", value);
		}
		if (prop.containsKey("db5.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("db5.pass");
			value = sh.dec(value);
			prop.setProperty("db5.pass", value);
		}
		if (prop.containsKey("db6.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("db6.pass");
			value = sh.dec(value);
			prop.setProperty("db6.pass", value);
		}
		if (prop.containsKey("db7.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("db7.pass");
			value = sh.dec(value);
			prop.setProperty("db7.pass", value);
		}

		if (prop.containsKey("mysqldb.pass")) {
			SafeHelper sh = SafeHelper.getInstance();
			String value = prop.getProperty("mysqldb.pass");
			value = sh.dec(value);
			prop.setProperty("mysqldb.pass", value);
		}
		for (Object key : prop.keySet()) {
			String keyStr = key.toString();
			String value = prop.getProperty(keyStr);
			if (keyStr.indexOf("db.pass.")!=-1) {
				value = SafeHelper.dec(value);
			}
			prop.put(keyStr, value);
		}
		
		super.convertProperties(prop);
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		ctxPropertiesMap = new HashMap();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}
	
	private boolean isWindows(){
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){  
		  return true;  
		}
		return false;
	}
	  
	
	@Override
    protected void loadProperties(Properties props) throws IOException {
		
        if (locations != null) {
            log.info("Start loading properties file.....");
            
            boolean isWindow = this.isWindows();
            
            for (Resource location : this.locations) {
                InputStream is = null;
                try {
                	String filename = location.getFilename();
                    if(isWindow){
	                    if (filename.contains("develop")) {
	                        log.info("Loading properties file from " + location);
	                    	is = location.getInputStream();
	                        this.propertiesPersister.load(props, is);	
	                    }
                    }else{
                    	if (!filename.contains("develop")) {	      
                            log.info("Loading properties file from " + location);
                        	is = location.getInputStream();
	                        this.propertiesPersister.load(props, is);	
	                    }
                    }
                } catch (IOException ex) {
                	log.info("读取配置文件失败.....");
                    ex.printStackTrace();
                    throw ex;
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            }
        }

    }
	

	public static Object getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}
	/**
	 * 获得资源文件路径
	 * @return
	 */
	public static Set<String> getResourceFolderLocations(){
		Set<String> pathSet = new HashSet<String>();
		for(int i=0;i<locations.length;i++){
			Resource res = locations[i];
			try {
				File file = res.getFile();
				String filePath = file.getParent();
				pathSet.add(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pathSet;
	}
}
