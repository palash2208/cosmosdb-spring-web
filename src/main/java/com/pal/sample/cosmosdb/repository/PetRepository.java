package com.pal.sample.cosmosdb.repository;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.pal.sample.cosmosdb.configuration.KVConfigProperties;
import com.pal.sample.cosmosdb.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepository {

    private final KVConfigProperties kvConfigProperties;

    private CosmosContainer petContainer;

    public PetRepository(KVConfigProperties kvConfigProperties) {
        this.kvConfigProperties = kvConfigProperties;
    }

    private CosmosContainer getPetContainer() {

        if (petContainer != null) return petContainer;

        SecretClient secretClient = new SecretClientBuilder()
                .vaultUrl(kvConfigProperties.getUri())
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        KeyVaultSecret cosmosdbUriSecret = secretClient.getSecret("pal-cosmosdb-uri");
        KeyVaultSecret cosmosdbKeySecret = secretClient.getSecret("pal-cosmosdb-key");

        CosmosClient cosmosClient = new CosmosClientBuilder()
                .endpoint(cosmosdbUriSecret.getValue())
                .key(cosmosdbKeySecret.getValue())
                .consistencyLevel(ConsistencyLevel.SESSION)
                .buildClient();

        petContainer = cosmosClient.getDatabase("app").getContainer("pets");

        return petContainer;

    }

    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();
        CosmosPagedIterable<Pet> petItemIterable = getPetContainer().readAllItems(new PartitionKey("partitionKey"), Pet.class);
        petItemIterable.forEach(pet -> pets.add(new Pet(pet.getId(), pet.getName(), pet.getDangerRating())));
        return pets;
    }

    public Pet getPetWithId(String id) {
        CosmosItemResponse<Pet> petItemIterable = getPetContainer().readItem(id, new PartitionKey("partitionKey"), Pet.class);
        return new Pet(
                petItemIterable.getItem().getId(),
                petItemIterable.getItem().getName(),
                petItemIterable.getItem().getDangerRating());
    }



}
