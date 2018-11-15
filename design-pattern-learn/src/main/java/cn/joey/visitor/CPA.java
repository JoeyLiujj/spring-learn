package cn.joey.visitor;

/**
 * @auther liujiji
 * @date 2018/10/15 17:06
 */
public class CPA implements AccountBookViewer{

    @Override
    public void viewIncomeBill(IncomeBill incomeBill) {
        System.out.println("注册会计师查看收入交税了没。");
    }

    @Override
    public void viewConsumeBill(ConsumeBill consumeBill) {
        if (consumeBill.getItem().equals("工资")) {
            System.out.println("注册会计师查看工资是否交了个税。");
        }
    }
}
