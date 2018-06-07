package cn.joey.aop.xmlconfig;

import cn.joey.aop.UserService;
import cn.joey.aop.annotationconfig.CustomAnnotation;

import java.io.IOException;

@CustomAnnotation
public class UserServiceImpl implements UserService {

    @Override
    public void add() throws IOException {
        System.out.println("增加用户-----add()");
    }

    @Override
    @CustomAnnotation
    public void update(int a) {
        System.out.println("修改用户方法---update()");
    }

    @Override
    public void delete() {
        System.out.println("删除用户----delete()");
    }

    @Override
    public void search() {
        System.out.println("查询用户----search()");
    }
}
