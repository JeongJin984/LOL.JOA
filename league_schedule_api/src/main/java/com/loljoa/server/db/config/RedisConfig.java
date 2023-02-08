package com.loljoa.server.db.config;

//@Configuration
//@RequiredArgsConstructor
public class RedisConfig {

//    private final ObjectMapper objectMapper;
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory(host, port);
//    }
//
//    @Bean
//    public RedisCacheManager redisCacheManager() {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
//                .defaultCacheConfig()
//                .disableCachingNullValues()
//                .serializeValuesWith(
//                        RedisSerializationContext.SerializationPair
//                                .fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper))
//                );
//
//        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
//        redisCacheConfigurationMap
//                .put("gameData", redisCacheConfiguration.entryTtl(Duration.ofMinutes(5)))
//        ;
//
//        return RedisCacheManager.RedisCacheManagerBuilder
//                .fromConnectionFactory(redisConnectionFactory())
//                .withInitialCacheConfigurations(redisCacheConfigurationMap)
//                .cacheDefaults(redisCacheConfiguration)
//                .build();
//
//    }


}