package cn.joey.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtil {

    @Autowired
    private RedisTemplate template;

    public void setTemplate(RedisTemplate template) {
        this.template = template;
    }

    public Boolean hasKey(String mylist) {
        return template.hasKey(mylist);
    }

    public boolean expire(String mylist, int i) {
        return template.expire(mylist,i, TimeUnit.MILLISECONDS);
    }

    public Long getExpire(String mylist) {
        return template.getExpire(mylist);
    }
}
