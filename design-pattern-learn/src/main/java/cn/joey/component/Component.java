package cn.joey.component;

/**
 * @auther liujiji
 * @date 2018/10/22 10:49
 */
public abstract class Component {
    protected String name;

    Component(String name) {
        this.name = name;
    }

    public abstract void add(Component component);

    public abstract void remove(Component component);

    public abstract void display(int level);
}
