package pl.ksals.caching.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.ksals.caching.domain.Product;
import pl.ksals.caching.model.Products;
import pl.ksals.caching.services.ProductCrudService;

@RestController
@RequestMapping("api/products")
public class ProductController {

    final ProductCrudService productCrudService;

    public ProductController(ProductCrudService productCrudService) {
        this.productCrudService = productCrudService;
    }

    @GetMapping
    public Products getAll(){
        return new Products(productCrudService.getAll());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable final Long id){
        return productCrudService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody final Product product){
        return productCrudService.createProduct(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody final Product product, @PathVariable final Long id){
        productCrudService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable final Long id){
        productCrudService.deleteProduct(id);
    }

}
