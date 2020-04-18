package com.manulaiko.shinshinjiru;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Spring configuration.
 * =====================
 *
 * Configures the different spring options.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Configuration
public class SpringConfig {
    /**
     * Configures the event executor to run asynchronously.
     *
     * @return Event executor.
     */
    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster eventMulticaster() {
        var eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());

        return eventMulticaster;
    }
}
