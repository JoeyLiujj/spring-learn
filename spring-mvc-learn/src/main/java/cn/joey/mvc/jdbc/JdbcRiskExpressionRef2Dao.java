package cn.joey.mvc.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class JdbcRiskExpressionRef2Dao implements RiskExpressionRefDao{

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void save(RiskExpressionRef obj) {
        String sql="insert into riskexpressionref ('CalCode', 'RiskCode', 'AccType', 'State', 'MakeDate', 'MakeTime', 'ModifyDate', 'ModifyTime') VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {obj.getCalCode(),obj.getRiskCode(),obj.getAccType(),obj.getState(),obj.getMakeDate(),obj.getMakeTime(),obj.getModifyDate(),obj.getModifyTime()};
        this.template.update(sql,params);
    }

    @Override
    public void update(RiskExpressionRef obj) {
        String sql="UPDATE riskexpressionref SET 'RiskCode' = ?, 'AccType' = ?, 'State' = ?, 'MakeDate' = ?, 'MakeTime' = ?, 'ModifyDate' = ?, 'ModifyTime' = ? WHERE 'CalCode' = ?";
        Object[] params = {obj.getRiskCode(),obj.getAccType(),obj.getState(),obj.getMakeDate(),obj.getMakeTime(),obj.getModifyDate(),obj.getModifyTime(),obj.getCalCode()};
        this.template.update(sql,params);
    }

    @Override
    public void delete(String calcode) {
        String sql = "delte from riskexpressionref where calcode=?";
        Object[] params = {calcode};
        this.template.update(sql,params);
    }

    @Override
    public RiskExpressionRef findByNo(String calcode) {
        String sql = "select * from riskexpressionref where calcode=?";
        Object[] params = {calcode};
        RowMapper<RiskExpressionRef> mapper = new RiskExpressionRefMapper();
        RiskExpressionRef riskExpressionRef = this.template.queryForObject(sql, params, mapper);
        return riskExpressionRef;
    }

    @Override
    public List<RiskExpressionRef> findAll() {
        String sql="select * from riskexpressionref";
        RowMapper<RiskExpressionRef> mapper = new RiskExpressionRefMapper();
        List<RiskExpressionRef> riskExpressionRefList = this.template.query(sql, mapper);
        return riskExpressionRefList;
    }
}
