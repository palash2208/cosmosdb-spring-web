package com.pal.sample.cosmosdb.repository;

import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.pal.sample.cosmosdb.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepository {

    @Autowired
    private CosmosContainer petContainer;

    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();
        CosmosPagedIterable<Pet> petItemIterable = petContainer.readAllItems(new PartitionKey("partitionKey"), Pet.class);
        petItemIterable.forEach(pet ->  {
            pets.add(new Pet(pet.getId(), pet.getName(), pet.getDangerRating()));
        });
        return pets;
    }

    public Pet getPetWithId(String id) {
        CosmosItemResponse<Pet> petItemIterable = petContainer.readItem(id, new PartitionKey("partitionKey"), Pet.class);
        return new Pet(
                petItemIterable.getItem().getId(),
                petItemIterable.getItem().getName(),
                petItemIterable.getItem().getDangerRating());
    }



}
