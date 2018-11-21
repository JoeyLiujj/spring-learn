package cn.joey.mapper;

import cn.joey.annotation.MyBatisRepository;
import cn.joey.entity.RiskExpressionRef;

import java.util.List;

public interface RiskExpressionRefMapper {
    public List<RiskExpressionRef> findAll();
}
