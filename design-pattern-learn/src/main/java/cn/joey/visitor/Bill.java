package cn.joey.visitor;

/**
 * @auther liujiji
 * @date 2018/10/15 16:56
 */
public interface Bill {
    void accept(AccountBookViewer viewer);
}
