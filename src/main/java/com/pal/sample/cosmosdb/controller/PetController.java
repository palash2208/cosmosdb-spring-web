package com.pal.sample.cosmosdb.controller;

import com.pal.sample.cosmosdb.model.Pet;
import com.pal.sample.cosmosdb.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/")
    public String ping() {
        return "Healthy";
    }

    @GetMapping("/pets/all")
    public List<Pet> getPets() {
        return petService.getAllPets();
    }

    @GetMapping("/pets/{id}")
    public Pet getPetWithId(@PathVariable String id) {
        return petService.getPetWithId(id);
    }

}
