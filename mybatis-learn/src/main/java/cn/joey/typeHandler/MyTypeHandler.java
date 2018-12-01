package cn.joey.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @auther liujiji
 * @date 2018/11/22 14:06
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyTypeHandler implements TypeHandler<String> {

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        return str;
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        return str;
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
