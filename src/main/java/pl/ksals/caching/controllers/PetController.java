package pl.ksals.caching.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.ksals.caching.domain.Pet;
import pl.ksals.caching.model.Pets;
import pl.ksals.caching.services.PetCrudService;

@RestController
@RequestMapping("api/pets")
public class PetController {

    private final PetCrudService petCrudService;

    public PetController(PetCrudService petCrudService) {
        this.petCrudService = petCrudService;
    }

    @GetMapping
    public Pets getAll(){
        return new Pets(petCrudService.getAll());
    }

    @GetMapping("/{name}")
    public Pet getOne(@PathVariable final String name){
        return petCrudService.getOne(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createPet(@RequestBody final Pet pet){
        return petCrudService.createPet(pet);
    }

    @PutMapping("/{name}")
    public Pet updatePet(@RequestBody final Pet pet, @PathVariable final String name){
       return petCrudService.updatePet(pet, name);
    }
    @DeleteMapping("/{name}")
    public void deletePet(@PathVariable final String name){
        petCrudService.deletePet(name);
    }
}
