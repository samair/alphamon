package com.webvidhi.pubsub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.webvidhi.pubsub.service.DevMessageSubscriber;
import com.webvidhi.pubsub.service.MessagePublisher;
import com.webvidhi.pubsub.service.RedisMessagePublisher;
import com.webvidhi.pubsub.service.RedisMessageSubscriber;

@Configuration
@ComponentScan(basePackages="com.webvidhi.pubsub")
@EnableRedisRepositories(basePackages = "com.webvidhi.pubsub")
public class RedisConfig {
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }
    
    @Autowired
    RedisMessageSubscriber redisMessageSubscriber;

    @Autowired
    DevMessageSubscriber devMessageSubscriber;
    
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(redisMessageSubscriber);
    }
    
    @Bean
    MessageListenerAdapter devMessageListener() {
        return new MessageListenerAdapter(devMessageSubscriber);
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        container.addMessageListener(devMessageListener(), deviceTopic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate(), topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("realtime");
    }
    
    @Bean
    ChannelTopic deviceTopic() {
        return new ChannelTopic("devices");
    }
}
