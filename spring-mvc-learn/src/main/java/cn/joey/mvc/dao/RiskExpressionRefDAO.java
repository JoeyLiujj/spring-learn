package cn.joey.mvc.dao;

import cn.joey.mvc.annotation.MyBatisRepository;
import cn.joey.mvc.entity.RiskExpressionRef;

import java.util.List;

@MyBatisRepository
public interface RiskExpressionRefDAO {
    public List<RiskExpressionRef> findAll();
}
