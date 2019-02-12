package com.alexandru.obaj.soa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Configuration class defining an asynchronous able component for processing of alarms.
 */
@EnableAsync
@Configuration
public class SoaAsyncConfig {

    @Value("${soa.async.corePoolSize}")
    private int corePoolSize;

    @Value("${soa.async.maxPoolSize}")
    private int maxPoolSize;

    @Value("${soa.async.queueSize}")
    private int queueSize;

    @Bean(name = "asyncAlarmProcessor")
    public Executor getAsyncAlarmProcessor() {
        return getExecutor("AlarmProcessor");

    }

    private Executor getExecutor(String threadNamePrefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueSize);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }
}
