package cn.icmyfuture.iarc.data.config;

import cn.icmyfuture.iarc.data.common.RedisCacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig extends RedisCacheConfig {
    public static final String getAllUsers = "getAllUsers";

    static {
        setCacheTime(getAllUsers, 180L);
    }
}