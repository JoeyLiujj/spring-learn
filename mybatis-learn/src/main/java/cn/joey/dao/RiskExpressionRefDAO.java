package cn.joey.dao;

import cn.joey.annotation.MyBatisRepository;
import cn.joey.entity.RiskExpressionRef;

import java.util.List;

@MyBatisRepository
public interface RiskExpressionRefDAO {
    public List<RiskExpressionRef> findAll();
}
