package com.joey.mybatis;

import java.util.List;

public interface TestMapper {
     <T> List<T> selectPrimaryKey();
}
