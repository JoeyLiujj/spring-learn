package cn.joey.visitor;

/**
 * @auther liujiji
 * @date 2018/10/15 17:02
 */
public class Boss implements AccountBookViewer {

    private double totalIncome;
    private double totalConsume;

    @Override
    public void viewIncomeBill(IncomeBill incomeBill) {
        totalIncome +=incomeBill.getAmount();
    }

    @Override
    public void viewConsumeBill(ConsumeBill consumeBill) {
        totalConsume +=consumeBill.getAmount();
    }
    public double getTotalIncome() {
        System.out.println("老板查看一共收入多少，数目是："+totalIncome);
        return totalIncome;
    }
    public double getTotalConsume(){
        System.out.println("老板查看一共消费多少，数目是："+totalConsume);
        return totalConsume;
    }
}
