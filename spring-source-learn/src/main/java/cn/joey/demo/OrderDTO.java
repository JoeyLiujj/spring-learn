package cn.joey.demo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @auther liujiji
 * @date 2019/4/22 10:32
 */
@Data
public class OrderDTO {

    private String code;
    private BigDecimal price;
    private String type;
}
