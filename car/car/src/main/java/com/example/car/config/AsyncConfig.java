package com.example.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskexecutor() {
        //cuando se inicie la ap se crea un objeto de este tipo
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //El número máximo de hilos
        executor.setMaxPoolSize(10);
        //maximo numero d hilos  a la vex
        executor.setCorePoolSize(5);
        //  executor.setMIN
        executor.setThreadNamePrefix("demothread-");
        executor.initialize();

        return executor;
    }
}
