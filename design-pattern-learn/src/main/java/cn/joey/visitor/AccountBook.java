package cn.joey.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/10/15 17:10
 * 账本类，相当于当前访问者模式例子中的数据结构
 */
public class AccountBook {
    private List<Bill> billList = new ArrayList<Bill>();
    public void addBill(Bill bill){
        billList.add(bill);
    }
    public void show(AccountBookViewer viewer){
        for (Bill bill : billList) {
            bill.accept(viewer);
        }
    }
}
