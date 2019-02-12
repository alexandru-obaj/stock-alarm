package com.alexandru.obaj.soa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ConfigurationProperties(prefix = "soa")
@Component
public class SoaEmailConfig {

    private final Map<String, String> mail = new HashMap<>();


    public Properties getEmailProperties() {
        Properties emailProperties = new Properties();
        mail.forEach((key, value) -> emailProperties.put("mail." + key, value));
        return emailProperties;
    }
}
