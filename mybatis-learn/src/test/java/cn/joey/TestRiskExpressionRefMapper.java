package cn.joey;

import cn.joey.entity.RiskExpressionRef;
import cn.joey.mapper.RiskExpressionRefMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TestRiskExpressionRefMapper {

    @Test
    public void testMapper(){
        String conf = "applicationContext.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(conf);
        RiskExpressionRefMapper contextBean = context.getBean("riskExpressionRefMapper", RiskExpressionRefMapper.class);
        List<RiskExpressionRef> list = contextBean.findAll();
        for (RiskExpressionRef obj : list) {
            System.out.println(obj.getCalCode()+"-----"+obj.getRiskCode());
        }
    }
    @Test
    public void testMapperScan(){
        String conf = "applicationContext-scan.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(conf);
        RiskExpressionRefMapper contextBean = context.getBean("riskExpressionRefMapper", RiskExpressionRefMapper.class);
        List<RiskExpressionRef> list = contextBean.findAll();
        for (RiskExpressionRef obj : list) {
            System.out.println(obj.getCalCode()+"-----"+obj.getRiskCode());
        }
    }
    @Test
    public void testMapperScanAnnotation(){
        String conf = "applicationContext-scan-annotation.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(conf);
        RiskExpressionRefMapper contextBean = context.getBean("riskExpressionRefMapper", RiskExpressionRefMapper.class);
        List<RiskExpressionRef> list = contextBean.findAll();
        for (RiskExpressionRef obj : list) {
            System.out.println(obj.getCalCode()+"-----"+obj.getRiskCode());
        }
    }

    @Test
    public void testMybatisSourceStep() throws IOException {
        // mybatis的配置文件
        String resource = "conf.xml";
        // 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Resources.getResourceAsStream(resource);
        // 构建SqlSession的工厂
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = build.openSession();
        /*
         * 映射sql的表示字符串
         * cn.joey.mapper.RiskExpressionRefMapper是mapper.xml文件中的mapper标签的namespace的属性的值
         * findAll是select标签的id属性值，通过select标签的id属性值可以找到要执行的SQL
         */
        // 执行查询返回一个唯一的user对象的sql
        List<RiskExpressionRef> list = sqlSession.selectList("cn.joey.mapper.RiskExpressionRefMapper.findAll","RAA00020");
        for(RiskExpressionRef obj:list){
            System.out.println(obj.getCalCode()+"------"+obj.getRiskCode());
        }
    }

    @Test
    public void testFiled() throws IllegalAccessException {
        RiskExpressionRef ref = new RiskExpressionRef();
        ref.setAccType("RC");
        ref.setCalCode("RAA00001");
        Field[] fields = ref.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("calCode") || field.getName().equals("accType")) {
                field.setAccessible(true);
                Object o = field.get(ref);
                System.out.println(o.toString());
            }
        }
    }

    @Test
    public void testListSequence(){
        List list = new ArrayList();

        list.add(2);
        list.add(3);
        list.add(1);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

}
