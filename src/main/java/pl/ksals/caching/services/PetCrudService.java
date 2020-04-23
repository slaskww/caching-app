package pl.ksals.caching.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ksals.caching.domain.Pet;
import pl.ksals.caching.repository.PetRepository;

import java.util.List;

@Service
@Slf4j
public class PetCrudService {

    private final PetRepository petRepository;

    public PetCrudService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAll(){
        log.info("getAll");
       return petRepository.findAll();
    }

    public Pet getOne(final String name){
        log.info("getOne");
        return petRepository.findById(name).orElseThrow(() -> new IllegalArgumentException());
    }

    public Pet createPet(final Pet pet){
        log.info("createPet");
        return petRepository.save(pet);
    }

    public Pet updatePet(final Pet pet, String name){

        log.info("updatePet");
       final Pet existingPet = getOne(name);
       existingPet.setBreed(pet.getBreed());
       return petRepository.save(existingPet);
    }

    public void deletePet(final String name){
        log.info("deletePet");
        petRepository.deleteById(name);
    }
}
