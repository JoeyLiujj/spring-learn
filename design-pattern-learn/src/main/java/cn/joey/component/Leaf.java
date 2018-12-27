package cn.joey.component;

import java.nio.charset.Charset;

/**
 * @auther liujiji
 * @date 2018/10/22 10:51
 */
public class Leaf extends Component {


    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        System.out.println("Connot add a component to a leaf");
    }

    @Override
    public void remove(Component component) {
        System.out.println("can not remove a component to a leaf");
    }

    @Override
    public void display(int level) {
        System.out.println(
                new String(new byte[]{'-'}, Charset.defaultCharset())+name);
    }
}
