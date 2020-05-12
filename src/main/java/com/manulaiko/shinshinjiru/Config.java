package com.manulaiko.shinshinjiru;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Application config.
 * ===================
 *
 * Configures Spring components.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Configuration
public class Config {
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        var multicaster = new SimpleApplicationEventMulticaster();
        multicaster.setTaskExecutor(new SimpleAsyncTaskExecutor());

        return multicaster;
    }
}
