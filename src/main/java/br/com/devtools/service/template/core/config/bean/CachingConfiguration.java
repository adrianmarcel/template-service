package br.com.devtools.service.template.core.config.bean;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CachingConfiguration {

  private final RedisProperties properties;

  @Bean
  public RedisTemplate<String, Object> templateCache() {
    var configuration =
        new RedisStandaloneConfiguration(properties.getHost(), properties.getPort());
    configuration.setPassword(RedisPassword.of(properties.getPassword()));

    var jedisConnectionFactory = new JedisConnectionFactory(configuration);

    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setDefaultSerializer(new StringRedisSerializer());
    template.setConnectionFactory(jedisConnectionFactory);

    jedisConnectionFactory.afterPropertiesSet();
    return template;
  }
}
