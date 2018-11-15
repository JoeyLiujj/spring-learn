package cn.joey.mvc.jdbc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RiskExpressionRefMapper implements RowMapper<RiskExpressionRef> {
    @Override
    public RiskExpressionRef mapRow(ResultSet rs, int rowNum) throws SQLException {
        RiskExpressionRef riskExpressionRef = new RiskExpressionRef();
        riskExpressionRef.setCalCode(rs.getString("calcode"));
        riskExpressionRef.setRiskCode(rs.getString("riskcode"));
        riskExpressionRef.setState(rs.getString("state"));
        riskExpressionRef.setMakeDate(rs.getDate("makedate"));
        riskExpressionRef.setMakeTime(rs.getString("maketime"));
        riskExpressionRef.setModifyDate(rs.getDate("modifydate"));
        riskExpressionRef.setModifyTime(rs.getString("modifytime"));
        riskExpressionRef.setAccType(rs.getString("acctype"));
        return riskExpressionRef;
    }

}
