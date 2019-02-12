package com.alexandru.obaj.soa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration class holding remote stock client API configurations.
 */
@Component
@ConfigurationProperties(prefix = "soa.http")
@Data
public class SoaHttpConfig {

    private String baseUrl;

    private String refDataSymbolsSufix;

    private String symbolPriceSufix;
}
