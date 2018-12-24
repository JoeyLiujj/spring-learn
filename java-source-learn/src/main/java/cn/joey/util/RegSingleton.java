package cn.joey.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther liujiji
 * @date 2018/12/19 14:09
 */
public class RegSingleton {
    private static Map m_registry=new HashMap();
    static {
        RegSingleton x = new RegSingleton();
        m_registry.put(x.getClass().getName(), x);
    }
    protected RegSingleton(){}

    public static RegSingleton getInstance(String name) {
        if(name==null) return null;
        if(m_registry.get(name)==null){
            try {
                m_registry.put(name,Class.forName(name).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return (RegSingleton) m_registry.get(name);
    }
}
