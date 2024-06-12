package com.electronics_store.configuration;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching // bật cache
@EnableTransactionManagement // bật quản lý transaction tự động dùng khi gọi @Transactional
public class ApplicationConfig {

    // caching
    @Bean
    public Caffeine<Object, Object> caffeine() {
        return Caffeine.newBuilder()
                .initialCapacity(50) // số lượng phần tử ban đầu
                .maximumSize(300) // số lượng entry tối đa
                .expireAfterAccess(5, TimeUnit.MINUTES) // thời gian tồn tai là 5 minutes
                .weakKeys(); // tự động thu hồi key nếu không còn sử dụng
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine());
        return cacheManager;
    }

    /*JPA Auditing*/
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable(
                SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
