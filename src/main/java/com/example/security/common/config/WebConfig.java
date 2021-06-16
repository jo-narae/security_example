package com.example.security.common.config;

import com.example.security.common.util.EncryptHelper;
import com.example.security.common.util.EncryptHelperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public EncryptHelper encryptConfigure() { return new EncryptHelperImpl(); }
}
