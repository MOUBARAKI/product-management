package org.kata.product.api.controller;


import org.kata.product.api.V1Api;
import org.kata.product.api.models.PaginatedProducts;
import org.kata.product.api.models.Product;
import org.kata.product.api.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements V1Api {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PaginatedProducts> getAllProducts(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.findAllProducts(pageable);
        PaginatedProducts paginatedProducts = new PaginatedProducts();
        paginatedProducts.setProducts(productPage.getContent());
        paginatedProducts.setTotalElements((int) productPage.getTotalElements());
        paginatedProducts.setTotalPages(productPage.getTotalPages());
        paginatedProducts.setSize(productPage.getSize());
        paginatedProducts.setNumber(productPage.getNumber());
        return ResponseEntity.ok(paginatedProducts);
    }


    @Override
    public ResponseEntity<Product> getProductById(Integer id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);

    }

    @Override
    public ResponseEntity<Product> updateProduct(Integer id, Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }
}
