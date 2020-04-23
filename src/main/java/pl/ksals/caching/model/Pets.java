package pl.ksals.caching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ksals.caching.domain.Pet;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pets {

    private List<Pet> pets;
}
