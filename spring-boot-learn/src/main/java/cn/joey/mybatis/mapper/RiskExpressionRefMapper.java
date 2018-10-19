package cn.joey.mybatis.mapper;

import cn.joey.mybatis.entity.RiskExpressionRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 两种写法
 * ① 第一种是基于mybatis3.x版本后提供的注解方式
 * ② 第一种是早期的写法，将sql写入到xml文件中
 */
@Mapper
public interface RiskExpressionRefMapper {
    @Select("select * from RiskExpressionRef where riskCode=#{riskCode}")
    List<RiskExpressionRef> findByRiskCode(@Param("riskCode") String riskCode);

    int insert(RiskExpressionRef obj);
}
