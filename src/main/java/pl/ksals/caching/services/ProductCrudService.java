package pl.ksals.caching.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ksals.caching.domain.Product;
import pl.ksals.caching.repository.ProductRepository;

import java.util.List;

@Service
@Slf4j
public class ProductCrudService {

    private final ProductRepository productRepository;

    public ProductCrudService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        log.info("Get all products");
       return productRepository.findAll();
    }

    public Product getById(final Long id){
        log.info("Get product by id " + id);
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product with id doesn't exist"));
    }

    public Product createProduct(final Product product){
        log.info("Create product");
        product.setId(null);
        return productRepository.save(product);
    }

    public Product updateProduct(final Product product, final Long id){
        log.debug("Update product");
        final Product existingProduct = getById(id);
        existingProduct.setCategory(product.getCategory());
        existingProduct.setName(product.getName());
        existingProduct.setRefNr(product.getRefNr());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(final Long id){
        log.info("Delete product " + id);
        productRepository.deleteById(id);
    }
}

