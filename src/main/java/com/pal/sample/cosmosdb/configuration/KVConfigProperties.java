package com.pal.sample.cosmosdb.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties(prefix = "keyvault")
@Getter
@Setter
@Primary
public class KVConfigProperties {
    private String name;
    private String uri;
}
