package cn.joey.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @auther liujiji
 * @date 2018/12/20 15:23
 */
public class SpringJdbcTemplateTest {

    private JdbcTemplate jdbcTemplate;

    public void init() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setPassword("1234qwerfv");
        dataSource.setUsername("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sampledb?useUnicode=true&characterEncoding=utf-8");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}