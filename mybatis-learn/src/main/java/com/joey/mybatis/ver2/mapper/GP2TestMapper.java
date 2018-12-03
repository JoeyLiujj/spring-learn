package com.joey.mybatis.ver2.mapper;

import com.joey.mybatis.ver2.annotation.GP2Mapper;
import com.joey.mybatis.ver2.annotation.GP2Select;

import java.util.List;

/**
 * @auther liujiji
 * @date 2018/12/3 9:57
 */
@GP2Mapper
public interface GP2TestMapper {
    @GP2Select("select * from riskexpressionref where calcode=%id")
    <T> List<T> selectByPrimarykey(String id);
}
