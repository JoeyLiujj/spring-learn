package cn.joey;

/**
 * @auther liujiji
 * @date 2019/6/2 14:26
 */
public interface Formatter {

    /**
     *  格式化操作
     * @param object 待格式化的对象
     * @return 返回格式化后的内容
     */
    String format(Object object);
}
