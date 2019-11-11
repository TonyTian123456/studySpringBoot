package com.example.redis;

import org.redisson.Redisson;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Configurable
public class RedissonManager {
    private static Config config = new Config();
    //声明redisso对象
    private static Redisson redisson = null;
   //实例化redisson
 static{

     config.useSingleServer().setAddress("127.0.0.1:6379");
          //得到redisson对象
        redisson = (Redisson) Redisson.create(config);


}


   /* @Bean
    @ConfigurationProperties("spring.redis")
    public RedisProperties redisProperties() {
        return new RedisProperties();
    }

    @Bean
    public RedisClient redisClient(RedisProperties redisProperties) {
        RedisClient redisClient = new RedisClient();
        redisClient.setRedisProperties(redisProperties);
        redisClient.init();
        return redisClient;
    }*/


 //获取redisson对象的方法
    public static Redisson getRedisson(){
        return redisson;
    }
}