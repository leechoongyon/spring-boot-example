package com.example.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestProperties {

    @Configuration
    @Getter
    @Setter
    @ConfigurationProperties(prefix = "test.first-level")
    public static class FirstLevelProperties {
        private String name;
    }

    @Configuration
    @Getter
    @Setter
    @ConfigurationProperties(prefix = "test.second-level")
    public static class SecondLevelProperties {
        private String name;
    }
}
