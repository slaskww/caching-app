package pl.ksals.caching.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ksals.caching.services.ProductCrudService;

@RestController
@RequestMapping("api/products")
public class ProductController {

    final ProductCrudService productCrudService;

    public ProductController(ProductCrudService productCrudService) {
        this.productCrudService = productCrudService;
    }

}
