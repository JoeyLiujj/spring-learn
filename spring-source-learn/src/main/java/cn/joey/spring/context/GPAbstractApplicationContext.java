package cn.joey.spring.context;

/**
 * @auther liujiji
 * @date 2018/12/13 13:58
 */
public abstract class GPAbstractApplicationContext {

    //提供给子类重写
    protected void onRefresh(){

    }

    protected abstract void refreshBeanFactory();
}
