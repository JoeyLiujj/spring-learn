package cn.joey.mvc.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class JdbcRiskExpressionRefDao extends JdbcDaoSupport implements RiskExpressionRefDao{

    @Override
    public void save(RiskExpressionRef obj) {
        String sql="insert into riskexpressionref ('CalCode', 'RiskCode', 'AccType', 'State', 'MakeDate', 'MakeTime', 'ModifyDate', 'ModifyTime') VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {obj.getCalCode(),obj.getRiskCode(),obj.getAccType(),obj.getState(),obj.getMakeDate(),obj.getMakeTime(),obj.getModifyDate(),obj.getModifyTime()};
        super.getJdbcTemplate().update(sql,params);
    }

    @Override
    public void update(RiskExpressionRef obj) {
        String sql="UPDATE riskexpressionref SET 'RiskCode' = ?, 'AccType' = ?, 'State' = ?, 'MakeDate' = ?, 'MakeTime' = ?, 'ModifyDate' = ?, 'ModifyTime' = ? WHERE 'CalCode' = ?";
        Object[] params = {obj.getRiskCode(),obj.getAccType(),obj.getState(),obj.getMakeDate(),obj.getMakeTime(),obj.getModifyDate(),obj.getModifyTime(),obj.getCalCode()};
        super.getJdbcTemplate().update(sql,params);
    }

    @Override
    public void delete(String calcode) {
        String sql = "delte from riskexpressionref where calcode=?";
        Object[] params = {calcode};
        this.getJdbcTemplate().update(sql,params);
    }

    @Override
    public RiskExpressionRef findByNo(String calcode) {
        String sql = "select * from riskexpressionref where calcode=?";
        Object[] params = {calcode};
        RowMapper<RiskExpressionRef> mapper = new RiskExpressionRefMapper();
        RiskExpressionRef riskExpressionRef = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return riskExpressionRef;
    }

    @Override
    public List<RiskExpressionRef> findAll() {
        String sql="select * from riskexpressionref";
        RowMapper<RiskExpressionRef> mapper = new RiskExpressionRefMapper();
        List<RiskExpressionRef> riskExpressionRefList = this.getJdbcTemplate().query(sql, mapper);
        return riskExpressionRefList;
    }
}
