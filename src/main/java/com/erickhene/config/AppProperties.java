package com.erickhene.config;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;

@Getter
public class AppProperties {
    private AppProperties(){}
    @Value("${server.port}")
    public static  String serverPort;
    @Value("${spring.datasource.driver-class-name}")
    public static String driverClassName;
    @Value("${spring.datasource.url}")
    public static String dataSourceUrl;
    @Value("${spring.datasource.username}")
    public static String dataSourceUsername;
    @Value("${spring.datasource.password}")
    public static String dataSourcePassword;
}
