package cn.joey.spring.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther liujiji
 * @date 2018/12/13 14:32
 */

//只是对application中的expression的封装
    //目标代理对象的一方法增强
    //配置文件的目的就是告诉spring，那些类的那些方法需要增强，增强的内容是什么
    //那么就需要把配置文件，对配置文件中所体现的内容进行封装
public class GPAopConfig {


    //把目标对象需要增强的代码内容作为value，
    private Map<Method, GPAspect> points = new HashMap<Method, GPAspect>();

    public void put(Method target,Object aspect,Method[] points){
        this.points.put(target,new GPAspect(aspect,points));
    }

    public GPAspect get(Method method){
        return this.points.get(method);
    }

    public boolean contains(Method method){
        return this.points.containsKey(method);
    }

    //对增强的代码的封装
    public class GPAspect{
        private Object aspect; //将LogAspect对象赋值给它
        private Method[] points;//会将LogAspect的before和after方法赋值给它
        public GPAspect(Object aspect,Method[] points){
            this.aspect = aspect;
            this.points = points;
        }

        public Object getAspect() {
            return aspect;
        }

        public void setAspect(Object aspect) {
            this.aspect = aspect;
        }

        public Method[] getPoints() {
            return points;
        }

        public void setPoints(Method[] points) {
            this.points = points;
        }
    }
}
