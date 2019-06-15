package cn.joey;

/**
 * @auther liujiji
 * @date 2019/6/2 14:38
 */
public class DefaultFormatter implements Formatter {
    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }
}
