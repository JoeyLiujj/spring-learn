package cn.joey.visitor;

/**
 * @auther liujiji
 * @date 2018/10/15 16:57
 */
public interface AccountBookViewer {
    void viewIncomeBill(IncomeBill incomeBill);
    void viewConsumeBill(ConsumeBill consumeBill);
}
