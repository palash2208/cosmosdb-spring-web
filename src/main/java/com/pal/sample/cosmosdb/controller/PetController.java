package com.pal.sample.cosmosdb.controller;

import com.azure.security.keyvault.secrets.SecretClient;

import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.pal.sample.cosmosdb.configuration.CosmosDbConfigProperties;
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

    @Autowired
    private CosmosDbConfigProperties cosmosDbConfigProperties;

    @Autowired
    private SecretClient secretClient;

    @GetMapping("/pets/config")
    public String getConfig() {
        return cosmosDbConfigProperties.getUri();
    }

    @GetMapping("/pets/keyVault")
    public String getKeyVaultSecretName() {
     KeyVaultSecret secVal = secretClient.getSecret("pal-cosmosdb-uri");
     return secVal.getValue();
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
