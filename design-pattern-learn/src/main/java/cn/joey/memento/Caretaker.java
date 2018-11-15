package cn.joey.memento;

/**
 * @auther liujiji
 * @date 2018/10/19 10:13
 */
public class Caretaker {
    private GameMemento memento;

    public GameMemento getMemento() {
        return memento;
    }

    public void setMemento(GameMemento memento) {
        this.memento = memento;
    }
}
