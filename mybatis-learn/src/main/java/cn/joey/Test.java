package cn.joey;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.util.ClassUtils;

public class Test {

	public static void main(String[] args) {
		// mybatis的配置文件
		String resource = "conf.xml";
		// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		ClassLoader loader = ClassUtils.getDefaultClassLoader();
		InputStream is = loader.getResourceAsStream(resource);
		// 构建SqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		SqlSession session = sessionFactory.openSession();
		/*
		 * 映射sql的表示字符串
		 * config.userMapper是userMapper.xml文件中的mapper标签的namespace的属性的值
		 * getUser是select标签的id属性值，通过select标签的id属性值可以找到要执行的SQL
		 */
		String statement = "config.userMapper.getUser";
		// 执行查询返回一个唯一的user对象的sql
		User user = session.selectOne(statement, 3);
		System.out.println(user);

	}
}
