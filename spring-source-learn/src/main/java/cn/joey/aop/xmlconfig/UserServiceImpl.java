package cn.joey.aop.xmlconfig;

import cn.joey.aop.annotationconfig.CustomAnnotation;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

//@CustomAnnotation
@Component("userService")
public class UserServiceImpl implements UserService, BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    public UserServiceImpl() {
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={Exception.class},readOnly = true)
    public void add() throws IOException {
        System.out.println("增加用户-----add()");
        // 配置文件中<aop:aspectj-autoproxy expose-proxy="true"/>
//        ((UserService) AopContext.currentProxy()).update(1);
//        update(1);
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

    private String brand;
    private String color;
    private int maxSpeed;
    private BeanFactory beanFactory;
    private String beanName;


    @Override
    public void setBeanName(String name) {
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
