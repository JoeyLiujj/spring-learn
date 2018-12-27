package cn.joey.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/12/20 15:23
 */
public class TemplateSupport {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public <T> List<T> select(Class clazz){
        //获取连接，加载驱动
        //获得statement对象
        //获取ResultSet
        //根据ResultSet生成对象
        List<T> result = new ArrayList<>();
        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement pstmt;
        try {
            Table table = (Table) clazz.getAnnotation(Table.class);
            String tableName = table.name();
            pstmt = conn.prepareStatement("select * from "+tableName);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Field[] fields = clazz.getDeclaredFields();
            while(rs.next()){
                T instance = (T) clazz.newInstance();
                for(int i=1;i<=columnCount;i++){
                    String columnName = metaData.getColumnName(i);
                    for(Field field:fields){
                        Class<?> type = field.getType();
                        Column annotation = field.getAnnotation(Column.class);
                        String name = annotation.name();
                        if(name.equals(columnName)){
                            field.setAccessible(true);
                            if(type ==String.class){
                                field.set(instance,rs.getString(i));
                            }else if(type == int.class){
                                field.set(instance,rs.getInt(i));
                            }else if(type ==Date.class){
                                field.set(instance,rs.getDate(i));
                            }else{
                                field.set(instance,null);
                            }
                        }
                    }
                }
                result.add(instance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }
}