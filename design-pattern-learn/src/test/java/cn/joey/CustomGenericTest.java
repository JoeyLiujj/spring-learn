package cn.joey;

import cn.joey.adapter.Adapter;
import cn.joey.adapter.Targetable;
import cn.joey.bridge.*;
import cn.joey.chain.MyHandler;
import cn.joey.command.Command;
import cn.joey.command.Invoker;
import cn.joey.command.MyCommand;
import cn.joey.command.Receiver;
import cn.joey.decorator.Decorator;
import cn.joey.decorator.Source;
import cn.joey.interpreter.Context;
import cn.joey.interpreter.Minus;
import cn.joey.interpreter.Plus;
import cn.joey.mediator.HouseOwner;
import cn.joey.mediator.Mediator;
import cn.joey.mediator.MediatorStructure;
import cn.joey.mediator.Tenant;
import cn.joey.memento.*;
import cn.joey.observer.MySubject;
import cn.joey.observer.Observer1;
import cn.joey.observer.Observer2;
import cn.joey.prototype.Prototype;
import cn.joey.proxy.Proxy;
import cn.joey.state.State;
import cn.joey.templatemethod.AbstractCalculator;
import cn.joey.visitor.*;
import org.junit.Test;

import java.io.IOException;

public class CustomGenericTest {

    @Test
    public void testAdapterPattern(){
        Targetable adapter = new Adapter();
        adapter.method1();
        adapter.method2();
    }

    /**
     * 把事务和具体的实例化分开，使它们可以独立变化。例如JDBC桥DriverManager
     * JDBC提供统一接口，每个数据库提供各自的实现，用一个叫做数据库驱动的程序来桥接就行了
     * @author Liujj
     */
    @Test
    public void testBridgePattern(){
        Sourceable source1=new SourceSub1();
        Sourceable source2=new SourceSub2();
        Bridge bridge = new MyBridge();
        bridge.setSource(source1);
        bridge.method();
        bridge.setSource(source2);
        bridge.method();
    }

    /**
     * 责任链模式
     * 每个对象持有下一个对象的引用，就会形成一条链，请求在这条链上传递，直到某一对象决定处理该请求
     * @author Joey
     *
     */
    @Test
    public void testChainPattern(){
        MyHandler handler1=new MyHandler("h1");
        MyHandler handler2=new MyHandler("h2");
        MyHandler handler3=new MyHandler("h3");
        MyHandler handler4=new MyHandler("h4");
        handler1.setHandler(handler2);
        handler2.setHandler(handler3);
        handler3.setHandler(handler4);

        handler1.operateor();
    }

    /**
     * @author Joey 命令者模式
     */
    @Test
    public void testCommandPattern(){
        Receiver receiver = new Receiver();//请求
        Command command = new MyCommand(receiver);
        Invoker invoker = new Invoker(command);//后台逻辑处理
        invoker.action();
    }

    /**
     * 装饰器模式 实现同一接口 动态的增加一些新的功能，装饰对象持有被装饰对象的实例
     *
     * @author Joey
     *
     */
    @Test
    public void testDecoratorPattern(){
        cn.joey.decorator.Sourceable source = new Source();
        Decorator decorator = new Decorator(source);
        decorator.method();
    }

    /**
     * 解释器模式用来做各种各样的解释器，如正则表达式等的解释器等等
     *
     * @author Liujj
     */
    @Test
    public  void testInterpreterPattern(){
        int result = new Minus().interpret(new Context(new Plus()
                .interpret(new Context(9, 2)), 8));
        System.out.println(result);
    }

    /**
     * 保存一个对象的某个状态，以便在适当的时候恢复对象
     * @author Joey
     *
     */
    @Test
    public void testMementoPattern(){
        User user=new User();
        user.setId(10);
        user.setName("zhangsan");
        User user2=new User();
        user2.setId(20);
        user2.setName("lisi");
        Original original=new Original("egg",user);
        //创建备忘录
        Storage storage=new Storage(original.createMemento());
        //修改原始类状态
        System.out.println("初始化状态为："+ original.getValue());
        System.out.println(original.getUser().getName());
        original.setValue("niu");
        original.setUser(user2);
        System.out.println("修改后的状态为：" + original.getValue());
        System.out.println(original.getUser().getName());
        //回复原始的状态
        original.restoreMemento(storage.getMemento());
        System.out.println(original.getValue());
        System.out.println(original.getUser().getName());
    }

    @Test
    public void testObserverPattern(){
        MySubject subject=new MySubject();
        subject.add(new Observer1());
        subject.add(new Observer2());
        subject.operation();
    }

    /**
     * 只针对直接获取属性时 改变值，如果使用setXXX，则不影响克隆，对原来的对象没有影响
     * @author Liujj
     * @Date 2017年5月24日
     * @time 下午5:50:39
     */
    @Test
    public void testPrototypePattern(){
        try {
            Prototype proto = new Prototype();
            proto.setStr("zhangsan");
            proto.setI(10);
            System.out.println("原始数据：" + proto.getStr()+"--->"+proto.getI());
            Prototype copy = (Prototype) proto.clone();
            copy.setStr("lisi");
            copy.setI(20);
            System.out.println("浅复制:" + copy.getStr() + "--->" +copy.getI());
            Prototype deepCopy=(Prototype)proto.deepClone();
            deepCopy.setStr("wangwu");
            deepCopy.setI(30);
            System.out.println("深复制：" + deepCopy.getStr()+"--->"+deepCopy.getI());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testProxyPattern(){
        cn.joey.proxy.Sourceable proxy = new Proxy();
        proxy.method();
    }

    /**
     * 当对象的状态改变时，同时改变其行为，例如QQ的状态改变，对应的操作更改
     * @author Joey
     *
     */
    @Test
    public void testStatePattern(){
        State state = new State();
        cn.joey.state.Context context = new cn.joey.state.Context(state);
        state.setValue1("state1");
        context.method();

        state.setValue1("state2");
        context.method();
    }

    /**
     * 模板方法模式
     * 一个抽象类中，有一个主方法，再定义1...n个方法，可以是抽象的，也可以是实际的方法，
     * 定义一个类，继承该抽象类，重写抽象方法，通过调用抽象类，实现对子类的调用
     *
     * @author Liujj
     */
    @Test
    public void testTemplateMethodPattern(){
        String exp = "8+8";
        AbstractCalculator cal = new cn.joey.templatemethod.Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }

    /**
     * 访问者模式 使数据结构和作用于结构上的操作相解耦合
     *  有许多对象构成、比较稳定的对象结构，这些对象的类都有一个accept方法用来接受访问者对象的访问。
     *
     *
     * @author Joey
     *
     */
    @Test
    public void testVisitorPattern(){
        AccountBook accountBook = new AccountBook();

        accountBook.addBill(new IncomeBill(10000,"买商品"));
        accountBook.addBill(new IncomeBill(12000,"卖广告位"));

        accountBook.addBill(new ConsumeBill(1000,"工资"));
        accountBook.addBill(new ConsumeBill(2000,"材料费"));

        AccountBookViewer boss=new Boss();
        AccountBookViewer cpa = new CPA();
        accountBook.show(cpa);
        accountBook.show(boss);


        ((Boss)boss).getTotalConsume();
        ((Boss)boss).getTotalIncome();

    }

    @Test
    public void testMemento() {
        Game game = new Game();
        HeroState heroState = new HeroState();
        heroState.setHP(100);
        heroState.setMP(100);
        SceneState sceneState = new SceneState();
        sceneState.setCoin(1000);
        sceneState.setWood(1000);
        game.sethState(heroState);
        game.setsState(sceneState);

        System.out.println("游戏状态开始备份");
        GameMemento gameMemento = new GameMemento(heroState,sceneState);
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(gameMemento);
        System.out.println("游戏状态备份完成");
        System.out.println("开始游戏，当前英雄生命值："+game.gethState().getHP());
        System.out.println("开始游戏，当前英雄魔法值："+game.gethState().getMP());
        System.out.println("当前场景金币："+game.getsState().getCoin());
        game.play();
        System.out.println("游戏结束，当前英雄生命值："+game.gethState().getHP());
        System.out.println("游戏结束，当前英雄魔法值："+game.gethState().getMP());
        System.out.println("当前场景金币："+game.getsState().getCoin());
        System.out.println("游戏状态还原开始");
        game.restore(gameMemento);
        System.out.println("游戏状态还原结束");
        System.out.println("当前英雄生命值："+game.gethState().getHP());
        System.out.println("当前英雄魔法值："+game.gethState().getMP());
        System.out.println("当前场景金币："+game.getsState().getCoin());

    }

    /**
     * 中介者模式
     * 解除两个类之间的关联性
     * @author Joey
     *
     */
    @Test
    public void testMediatorPattern(){
        MediatorStructure mediator = new MediatorStructure();
        HouseOwner houseOwner = new HouseOwner("张三", mediator);
        Tenant tenant = new Tenant("李四",mediator);

        //中介结构要知道房主和租房者
        mediator.setHouseOwner(houseOwner);
        mediator.setTenant(tenant);

        tenant.constact("听说你那里有三室一厅的房主出租。。。。");
        houseOwner.constact("是的！请问你需要租吗？");

    }
}
