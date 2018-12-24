package cn.joey.jdbc;

import cn.joey.User;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @auther liujiji
 * @date 2018/12/20 15:27
 */
public class JDBCCustomGenericTest {
    SpringJdbcTemplateTest templateTest = new SpringJdbcTemplateTest();
    @Test
    public void queryTest(){
        templateTest.init();

        JdbcTemplate jdbcTemplate = templateTest.getJdbcTemplate();
        String sql="select * from t_user";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        System.out.println(users);

        TransactionTemplate transactionTemplate=new TransactionTemplate();
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                return null;
            }
        });

    }

}
