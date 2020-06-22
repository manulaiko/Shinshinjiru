package com.manulaiko.shinshinjiru;

import com.manulaiko.shinshinjiru.exception.UnableToCreateSettingsFileException;
import lombok.SneakyThrows;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.io.File;

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

    @SneakyThrows
    @Bean
    public FileBasedConfiguration configuration() {
        var file = new File(System.getProperty("user.home") + File.separator +".shinshinjiru.properties");
        if (!file.exists() && !file.createNewFile()) {
            throw new UnableToCreateSettingsFileException();
        }

        var builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class);
        var params = new Parameters();

        builder.configure(params.fileBased().setFile(file))
                .setAutoSave(true);

        return builder.getConfiguration();
    }
}
