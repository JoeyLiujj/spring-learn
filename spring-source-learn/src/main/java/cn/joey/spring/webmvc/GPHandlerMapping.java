package cn.joey.spring.webmvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @auther liujiji
 * @date 2018/12/13 16:29
 */
public class GPHandlerMapping {

    private Method method;
    private Pattern pattern;
    private Object controller;

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public GPHandlerMapping(Pattern pattern, Object controller, Method method){
        this.controller = controller;
        this.pattern = pattern;
        this.method = method;

    }

    public Method getMethod() {
        return method;
    }
}
