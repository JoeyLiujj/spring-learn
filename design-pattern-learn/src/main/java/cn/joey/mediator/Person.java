package cn.joey.mediator;

/**
 * @auther liujiji
 * @date 2018/10/19 14:13
 */
public abstract class Person {
    String name;
    Mediator mediator;
    Person(String name,Mediator mediator){
        this.name = name;
        this.mediator = mediator;
    }
}
