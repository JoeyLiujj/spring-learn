package cn.joey;

import java.util.Map;
import java.util.Properties;

/**
 * @auther liujiji
 * @date 2018/12/6 11:07
 */
public class User {

    private String name;
    private String[] arg;
    private Properties  pro;
    private Map  map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Properties getPro() {
        return pro;
    }

    public void setPro(Properties pro) {
        this.pro = pro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getArg() {
        return arg;
    }

    public void setArg(String[] arg) {
        this.arg = arg;
    }
}
