package cn.joey.mediator;

/**
 * @auther liujiji
 * @date 2018/10/19 14:19
 * 具体的中介公司 如：自如 链家
 */
public class MediatorStructure extends Mediator {
    //首先中介结构必须知道所有房主和租房人的信息
    private HouseOwner houseOwner;
    private Tenant tenant;
    @Override
    public void constact(String message, Person person) {
        if (person == houseOwner) { //如果是房主，则租房者获得信息
            tenant.getMessage(message);
        }else{  //反正是房主获得信息
            houseOwner.getMessage(message);
        }
    }

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
