package com.example.config.dubbo;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@DubboComponentScan("com.example.*")
@Configuration
public class DubboConfiguration {

    @Bean
    @ConfigurationProperties("dubbo.application")
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig();
    }
    @Bean
    @ConfigurationProperties("dubbo.registry")
    public RegistryConfig registryConfig() {
        return new RegistryConfig();
    }
    @Bean
    @ConfigurationProperties("dubbo.protocol")
    public ProtocolConfig protocolConfig(){
        return new ProtocolConfig();
    }

    @Bean
    @ConfigurationProperties("dubbo.provider")
    public ProviderConfig providerConfig() {
        return new ProviderConfig();
    }

    @Bean
    @ConfigurationProperties("dubbo.consumer")
    public ConsumerConfig consumerConfig(){
        return new ConsumerConfig();
    }

}
