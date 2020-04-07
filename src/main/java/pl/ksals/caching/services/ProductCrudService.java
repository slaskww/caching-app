package pl.ksals.caching.services;

import org.springframework.stereotype.Service;
import pl.ksals.caching.domain.Product;
import pl.ksals.caching.repository.ProductRepository;

import java.util.List;

@Service
public class ProductCrudService {

    private final ProductRepository productRepository;

    public ProductCrudService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
       return productRepository.findAll();
    }

    public Product getById(final Long id){
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product with id doesn't exist"));
    }

    public Product createProduct(final Product product){
        product.setId(null);
        return productRepository.save(product);
    }

    public Product updateProduct(final Product product, final Long id){
        final Product existingProduct = getById(id);
        existingProduct.setCategory(product.getCategory());
        existingProduct.setName(product.getName());
        existingProduct.setRefNr(product.getRefNr());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(final Long id){
        productRepository.deleteById(id);
    }
}

