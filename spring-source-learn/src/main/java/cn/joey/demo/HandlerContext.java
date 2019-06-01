package cn.joey.demo;

import java.util.Map;

/**
 * @auther liujiji
 * @date 2019/4/22 11:01
 */
public class HandlerContext {

    private Map<String,Class> handlerMap;

    public HandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public AbstractHandler getInstance(String type) throws IllegalAccessException, InstantiationException {
        Class clazz = handlerMap.get(type);
        if(clazz==null){
            throw new IllegalArgumentException("not found ifElseToStrategyMode for type:"+type);
        }
        return (AbstractHandler)BeanTool.getBean(clazz);
    }
}
