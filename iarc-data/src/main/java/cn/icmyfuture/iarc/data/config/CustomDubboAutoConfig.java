package cn.icmyfuture.iarc.data.config;

import com.alibaba.dubbo.config.*;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.PreDestroy;

@Configuration
@EnableConfigurationProperties({CustomDubboAutoConfig.CustomDubboProperties.class})
@Order(Ordered.LOWEST_PRECEDENCE)
public class CustomDubboAutoConfig {
    private static final Logger logger = LoggerFactory.getLogger(CustomDubboAutoConfig.class);

    @ConfigurationProperties(prefix = "spring.dubbo")
    @Setter
    @Getter
    class CustomDubboProperties{
        private MonitorConfig monitor;
        private ConsumerConfig consumer;
        private ProviderConfig provider;
        private ModuleConfig module;
        private MethodConfig method;
    }


    @Autowired
    private CustomDubboProperties dubboProperties;

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = this.dubboProperties.getConsumer();
        if (consumerConfig == null) {
            consumerConfig = new ConsumerConfig();
        }
        return consumerConfig;
    }

    @Bean
    public MonitorConfig monitorConfig(){
        MonitorConfig monitorConfig = this.dubboProperties.getMonitor();
        if (monitorConfig == null) {
            monitorConfig = new MonitorConfig();
        }
        return monitorConfig;
    }


    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = this.dubboProperties.getProvider();
        if (providerConfig == null) {
            providerConfig = new ProviderConfig();
        }
        return providerConfig;
    }

    @Bean
    public MethodConfig requestMethodConfig() {
        MethodConfig methodConfig = dubboProperties.getMethod();
        if (methodConfig == null) {
            methodConfig = new MethodConfig();
        }
        return methodConfig;
    }

    @PreDestroy
    public void shutdownDestroy() {
        ProtocolConfig.destroyAll();
    }
}
