package com.pal.sample.cosmosdb.configuration;

import com.azure.cosmos.*;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CosmosDbConfiguration {

    @Bean
    public SecretClient getSecretClient(KVConfigProperties kvConfigProperties) {
        return new SecretClientBuilder()
                .vaultUrl(kvConfigProperties.getUri())
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }

    @Bean
    @Primary
    public CosmosClient getCosmosClient(SecretClient secretClient) {

        KeyVaultSecret cosmosdbUriSecret = secretClient.getSecret("pal-cosmosdb-uri");
        KeyVaultSecret cosmosdbKeySecret = secretClient.getSecret("pal-cosmosdb-key");

        return new CosmosClientBuilder()
                .endpoint(cosmosdbUriSecret.getValue())
                .key(cosmosdbKeySecret.getValue())
                .consistencyLevel(ConsistencyLevel.SESSION)
                .buildClient();
    }

//    @Bean
//    public CosmosClient getCosmosClient(CosmosDbConfigProperties cosmosDbConfigProperties) {
//        return new CosmosClientBuilder()
//                .endpoint(cosmosDbConfigProperties.getUri())
//                .key(cosmosDbConfigProperties.getKey())
//                .consistencyLevel(ConsistencyLevel.SESSION)
//                .buildClient();
//    }

    @Bean
    public CosmosDatabase getAppCosmosDb(CosmosClient cosmosClient) {
        return cosmosClient.getDatabase("app");
    }

    @Bean
    public CosmosContainer getCosmosDbPetContainer(CosmosDatabase cosmosDatabase) {
        return cosmosDatabase.getContainer("pets");
    }

}
