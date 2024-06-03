package com.electronics_store.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // bật quản lý transaction tự động dùng khi gọi @Transactional
public class ApplicationConfig {}
