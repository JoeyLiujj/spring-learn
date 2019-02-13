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
        jedis.set("MQ_COMP_ERROR_LIST_EC_MS_FIN_POLMESSAGE_efc7ccae-e652-494a-8029-6b00480400e1","{\"@type\":\"java.util.HashMap\",\"body\":\"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\",\"error\":\"手动修改状态\",\"status\":\"Error\"}");
        String name = jedis.get("MQ_COMP_ERROR_LIST_EC_MS_FIN_POLMESSAGE_efc7ccae-e652-494a-8029-6b00480400e1");
        System.out.println("MQ_COMP_ERROR_LIST_EC_MS_FIN_POLMESSAGE_efc7ccae-e652-494a-8029-6b00480400e1="+name);
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
