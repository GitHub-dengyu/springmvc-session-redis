package com.huato.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfig {
	
	private volatile JedisConnectionFactory mJedisConnectionFactory;  
    private volatile RedisTemplate<String, String> mRedisTemplate;  
    private volatile RedisCacheManager mRedisCacheManager;  
   
    
    public JedisConnectionFactory redisConnectionFactory() {  
        return mJedisConnectionFactory;  
    }  
  
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {  
        return mRedisTemplate;  
    }  
  
    @Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {  
        return mRedisCacheManager;  
    }
    
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(10);
		jedisPoolConfig.setMaxWaitMillis(100);
		jedisPoolConfig.setTestOnBorrow(true);
		return jedisPoolConfig;
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("192.168.10.46");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
		return jedisConnectionFactory;
	}
    public RedisConfig() {  
        super();  
    }  
  
    public RedisConfig(JedisConnectionFactory mJedisConnectionFactory, RedisTemplate<String,String> mRedisTemplate,  
            RedisCacheManager mRedisCacheManager) {  
        super();  
        this.mJedisConnectionFactory = mJedisConnectionFactory;  
        this.mRedisTemplate = mRedisTemplate;  
        this.mRedisCacheManager = mRedisCacheManager;  
    }  
	
	 @Bean  
	    public KeyGenerator customKeyGenerator() {  
	        return new KeyGenerator() {  
	            @Override  
	            public Object generate(Object o, Method method, Object... objects) {  
	                StringBuilder sb = new StringBuilder();  
	                sb.append(o.getClass().getName());  
	                sb.append(method.getName());  
	                for (Object obj : objects) {  
	                    sb.append(obj.toString());  
	                }  
	                return sb.toString();  
	            }  
	        };  
	    }  
	

}
