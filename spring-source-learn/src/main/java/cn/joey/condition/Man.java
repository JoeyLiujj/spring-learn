package cn.joey.condition;

import lombok.extern.slf4j.Slf4j;

/**
 * @auther liujiji
 * @date 2018/12/20 17:08
 */
@Slf4j
public class Man implements Person {
    @Override
    public void birth() {
        log.info("我不会生孩子...");
    }
}
