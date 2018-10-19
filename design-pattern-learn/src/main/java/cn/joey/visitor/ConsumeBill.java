package cn.joey.visitor;

/**
 * @auther liujiji
 * @date 2018/10/15 16:58
 */
public class ConsumeBill implements Bill {
    private double amount;
    private String item;

    public ConsumeBill(double amount,String item){
        super();
        this.amount=amount;
        this.item=item;
    }
    @Override
    public void accept(AccountBookViewer viewer) {
        viewer.viewConsumeBill(this);
    }

    public double getAmount(){
        return amount;
    }
    public String getItem(){
        return item;
    }
}
