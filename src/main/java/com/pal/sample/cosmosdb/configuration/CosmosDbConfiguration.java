package com.pal.sample.cosmosdb.configuration;

import com.azure.cosmos.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CosmosDbConfiguration {

    @Bean
    public CosmosClient getCosmosClient(CosmosDbConfigProperties cosmosDbConfigProperties) {
        return new CosmosClientBuilder()
                .endpoint(cosmosDbConfigProperties.getUri())
                .key(cosmosDbConfigProperties.getKey())
                .consistencyLevel(ConsistencyLevel.SESSION)
                .buildClient();
    }

    @Bean
    public CosmosDatabase getAppCosmosDb(CosmosClient cosmosClient) {
        return cosmosClient.getDatabase("app");
    }

    @Bean
    public CosmosContainer getCosmosDbPetContainer(CosmosDatabase cosmosDatabase) {
        return cosmosDatabase.getContainer("pets");
    }

}
