package cn.joey.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/10/22 10:56
 */
public class Composite extends Component {
    public Composite(String name) {
        super(name);
    }

    private List<Component> children = new ArrayList();

    @Override
    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void display(int level) {
        System.out.println(new String(new byte[]{'-'},level)+name);
        for(int i=0;i<children.size();i++){
            Component component = children.get(i);
            component.display(level + 2);
        }
    }
}
