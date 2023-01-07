package com.pal.sample.cosmosdb;

import com.pal.sample.cosmosdb.configuration.CosmosDbConfigProperties;
import com.pal.sample.cosmosdb.configuration.KVConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CosmosDbConfigProperties.class, KVConfigProperties.class})
public class CosmosdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmosdbApplication.class, args);
	}

}
