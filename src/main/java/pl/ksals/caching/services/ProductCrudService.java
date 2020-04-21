package pl.ksals.caching.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    //adnotacja sprawia, że wynik metody trafia do cache
    //dodatkowy warunek przy użyciu SpEL, cachowanie nastapi gdy metoda zwróci co najmniej 5 elementów
    @Cacheable(cacheNames = "products", unless = "#result.size() < 5")
    public List<Product> getAll(){
        log.info("Get all products");
       return productRepository.findAll();
    }

    //adnotacja sprawia, że wynik metody trafia do cache
    //dodatkowy warunek przy użyciu SpEL, cachowanie nastapi gdy id produkttu jest większe od 4
    @Cacheable(cacheNames = "single-product", condition = "#id > 4")
    public Product getById(final Long id){
        log.info("Get product by id " + id);
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product with id doesn't exist"));
    }

    //adnotacją CacheEvict sprawia, że uruchonienie metody powoduje usunięcie danych z cache,
    //adnotacja Caching grupuje adnotacje  @CacheEvict, a także  @Cacheable
    @Caching(evict = {
            @CacheEvict(cacheNames = "products", allEntries = true),
            @CacheEvict(cacheNames = "single-product", allEntries = true)
    })
    public Product createProduct(final Product product){
        log.info("Create product");
        product.setId(null);
        return productRepository.save(product);
    }

    //adnotacją CacheEvict sprawia, że uruchonienie metody powoduje usunięcie danych z cache,
    //adnotacja Caching grupuje adnotacje  @CacheEvict, a także  @Cacheable
    @Caching(evict = {
            @CacheEvict(cacheNames = "products", allEntries = true),
            @CacheEvict(cacheNames = "single-product", allEntries = true)
    })
    public Product updateProduct(final Product product, final Long id){
        log.info("Update product");
        final Product existingProduct = getById(id);
        existingProduct.setCategory(product.getCategory());
        existingProduct.setName(product.getName());
        existingProduct.setRefNr(product.getRefNr());
        return productRepository.save(existingProduct);
    }

    //adnotacją CacheEvict sprawia, że uruchonienie metody powoduje usunięcie danych z cache,
    //adnotacja Caching grupuje adnotacje  @CacheEvict, a także  @Cacheable
    @Caching(evict = {
            @CacheEvict(cacheNames = "products", allEntries = true),
            @CacheEvict(cacheNames = "single-product", allEntries = true)
    })
    public void deleteProduct(final Long id){
        log.info("Delete product " + id);
        productRepository.deleteById(id);
    }
}

