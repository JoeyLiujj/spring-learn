package cn.joey.mybatis.controller;

import cn.joey.mybatis.entity.RiskExpressionRef;
import cn.joey.mybatis.mapper.RiskExpressionRefMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/mybatis")
public class MyBatisController {
    /**
     * 之前由于mybatis-spring-boot-starter jar包的导入失败，
     * 导致mapper Bean 加载不成功，具体原因未知
     * 虽然Could not Autowire 但是还是可以运行的，不影响结果
     */
    @Autowired
    private RiskExpressionRefMapper riskExpressionRefMapper;

    @RequestMapping("/insertRiskExpressionRef")
    public String insert(){
        RiskExpressionRef ref = prepareData();
        final int insert = riskExpressionRefMapper.insert(ref);
        List<RiskExpressionRef> list = riskExpressionRefMapper.findByRiskCode("123401");
        return "插入"+insert+"条记录，查询出插入的记录："+list.get(0).toString();
    }

    private RiskExpressionRef prepareData(){
        RiskExpressionRef ref = new RiskExpressionRef();
        ref.setCalCode("RAA000001");
        ref.setRiskCode("123402");
        ref.setAccType("RS");
        ref.setMakeDate(new Date());
        ref.setMakeTime("08:00:00");
        ref.setModifyDate(new Date());
        ref.setModifyTime("08:00:00");
        return ref;
    }
}
