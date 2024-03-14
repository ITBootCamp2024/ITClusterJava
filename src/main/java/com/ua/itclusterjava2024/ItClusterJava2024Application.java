package com.ua.itclusterjava2024;

import com.ua.itclusterjava2024.dao.DAOConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableConfigurationProperties(DAOConfig.class)
public class ItClusterJava2024Application {

    public static void main(String[] args) {
        SpringApplication.run(ItClusterJava2024Application.class, args);
    }

}
