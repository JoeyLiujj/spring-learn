package cn.joey.mapper;

import cn.joey.annotation.MyBatisRepository;
import cn.joey.entity.RiskExpressionRef;

import java.util.List;

public interface RiskExpressionRefMapper {
    List<RiskExpressionRef> findAll(String id);

    default String test(){
        return null;
    }
}
