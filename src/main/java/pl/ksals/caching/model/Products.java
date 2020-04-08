package pl.ksals.caching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ksals.caching.domain.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    private List<Product> products;
}
