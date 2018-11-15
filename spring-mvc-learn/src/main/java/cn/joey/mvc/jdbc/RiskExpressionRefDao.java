package cn.joey.mvc.jdbc;

import java.util.List;

public interface RiskExpressionRefDao {
    public void save(RiskExpressionRef obj);
    public void update(RiskExpressionRef obj);
    public void delete(String calcode);
    public RiskExpressionRef findByNo(String calcode);
    public List<RiskExpressionRef> findAll();
}
