package top.cicle.bigger.util;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import top.cicle.bigger.controller.AttachmentController;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	   
	    private static Logger logger = Logger.getLogger(RedisCacheConfig .class);

        private volatile JedisConnectionFactory mJedisConnectionFactory;
        private volatile RedisTemplate<String, String> mRedisTemplate;
        private volatile RedisCacheManager mRedisCacheManager;

        public RedisCacheConfig() {
            super();
        }

        public RedisCacheConfig(JedisConnectionFactory mJedisConnectionFactory, RedisTemplate<String, String> mRedisTemplate, RedisCacheManager mRedisCacheManager) {
            super();
            this.mJedisConnectionFactory = mJedisConnectionFactory;
            this.mRedisTemplate = mRedisTemplate;
            this.mRedisCacheManager = mRedisCacheManager;
        }

        public JedisConnectionFactory redisConnectionFactory() {
            return mJedisConnectionFactory;
        }

        public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
            return mRedisTemplate;
        }

        public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
            return mRedisCacheManager;
        }

        
        
        @Bean
        public KeyGenerator keyGenerator() {
            return new KeyGenerator() {
                public Object generate(Object target, Method method,
                        Object... params) {
                    //�涨  ������+������+������ Ϊkey
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.getClass().getName()+"_");     //����
                    sb.append(method.getName()+"_");
                    for (Object obj : params) {
                        sb.append(obj.toString()+",");
                    }
                    logger.info(sb.toString());
                    System.out.println(sb.toString());
                    return sb.toString();
                }
            };
        }
        
}