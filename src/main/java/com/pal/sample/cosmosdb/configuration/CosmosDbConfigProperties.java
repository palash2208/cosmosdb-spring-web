package com.pal.sample.cosmosdb.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cosmosdb")
@Getter
@Setter
public class CosmosDbConfigProperties {
    private String uri;
    private String key;
}
