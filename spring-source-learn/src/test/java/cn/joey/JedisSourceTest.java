package cn.joey;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JedisSourceTest {

    @Test
    public void test(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("name","Joey");
        String name = jedis.get("name");
        System.out.println("name="+name);
    }

    /**
     * 自定义分片存入多个redis实例
     */
    @Test
    public void test_03(){
        String k1 = "四十二章经第一章";
        String v1 = "111111111111";
        String k2 = "四十二章经第二章";
        String v2 = "222222222222";
        String k3 = "四十二章经第三章";
        String v3 = "3333333333";
        List<String> keyList = new ArrayList<String>();
        keyList.add(k1);
        keyList.add(k2);
        keyList.add(k3);
        Map<String,String> map = new HashMap<String,String>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        for (String key : keyList) {
            if("四十二章经第一章".equals(k1)){
                Jedis jedis = new Jedis("127.0.0.1",6379);
                jedis.set(key,map.get(key));
            }else if("四十二章经第二章".equals(k2)){
                Jedis jedis = new Jedis("127.0.0.1",6380);
                jedis.set(key,map.get(key));
            }else if("四十二章经第三章".equals(k3)){
                Jedis jedis = new Jedis("127.0.0.1",6381);
                jedis.set(key,map.get(key));
            }
        }
    }

    @Test
    public void testJedisPool(){
        //需要构造存储多个reids实例信息 的list
        List<JedisShardInfo> jedisList = new ArrayList<JedisShardInfo>();
        //创建结点信息
        JedisShardInfo info1 = new JedisShardInfo("127.0.0.1",6379);
        JedisShardInfo info2 = new JedisShardInfo("127.0.0.1",6380);
        JedisShardInfo info3 = new JedisShardInfo("127.0.0.1",6381);

        jedisList.add(info1);
        jedisList.add(info2);
        jedisList.add(info3);

        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(200);

        //创建Jedis连接池
        ShardedJedisPool pool = new ShardedJedisPool(config, jedisList);
        //使用连接处获取数据
        ShardedJedis jedis = pool.getResource();
        for(int i=0;i<100;i++) {
            String value = jedis.get("key_" + i);
            System.out.println("获取到key_"+i+"的值为"+value);
        }
    }


    @Test
    public void test1() throws InterruptedException {
    }
}
