package com.pal.sample.cosmosdb.service;

import com.pal.sample.cosmosdb.model.Pet;
import com.pal.sample.cosmosdb.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.getPets();
    }

    public Pet getPetWithId(String id) {
        return petRepository.getPetWithId(id);
    }


}

