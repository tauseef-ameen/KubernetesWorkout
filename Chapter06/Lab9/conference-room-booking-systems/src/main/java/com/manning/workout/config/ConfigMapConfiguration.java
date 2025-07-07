package com.manning.workout.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "manning.workout")
public class ConfigMapConfiguration {
    private String building1;
    private String building2;
    private String building3;
    private String building4;
    private String building5;

}
