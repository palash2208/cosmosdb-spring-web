package com.pal.sample.cosmosdb.dummy;

import com.pal.sample.cosmosdb.model.Pet;

import java.util.Arrays;
import java.util.List;

public class PetDummy {

    public static List<Pet> getDummyPetList() {
        return Arrays.asList(
                new Pet("1", "pet1", 2),
                new Pet("2", "pet2", 4)
        );
    }

}
