//package com.xtm.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.net.UnknownHostException;
//import java.time.Duration;
//
//
//@Configuration
//@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//
//
//
//    private Duration timeToLive = Duration.ZERO;
//    public void setTimeToLive(Duration timeToLive) {
//        this.timeToLive = timeToLive;
//    }
//
//    /**
//     * 设置redis key value序列化
//     * @return
//     */
//    private Jackson2JsonRedisSerializer valueSerializer(){
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        return jackson2JsonRedisSerializer;
//    }
//
//    private RedisSerializer<String> keySerializer() {
//        return new StringRedisSerializer();
//    }
//
//    /**
//     * 设置缓存序列化，主要为了在redis工具中方便查看
//     * @param redisConnectionFactory
//     * @return
//     * @throws UnknownHostException
//     */
//    @Bean(name = "redisTemplate")
//    @SuppressWarnings({"rawtypes","unchecked"})
//    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object,Object> template = new RedisTemplate<Object,Object>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        template.setValueSerializer(valueSerializer());
//        template.setKeySerializer(keySerializer());
//        template.afterPropertiesSet();
//        return template;
//    }
//    /**
//     *  设置 redis 数据默认过期时间
//     *  设置@cacheable 序列化方式
//     * @return
//     */
//    @Bean
//    public RedisCacheConfiguration redisCacheConfiguration(){
//        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
//        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofDays(30));
//        return configuration;
//    }
//}
//
