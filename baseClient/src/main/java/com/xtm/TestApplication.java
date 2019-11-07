package com.xtm;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.apache.dubbo.config.ConsumerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    // 注解支持的配置Bean
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    /**
     * dubbo消费者配置不主动监督服务，请测无效
     *
     * @return
     */
//    @Bean
//    public ConsumerConfig consumerConfig() {
//        ConsumerConfig consumerConfig = new ConsumerConfig();
//        consumerConfig.setCheck(false);
//        consumerConfig.setTimeout(20000);
//        return consumerConfig;
//    }
}