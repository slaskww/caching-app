package pl.ksals.caching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ksals.caching.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, String> {

}
