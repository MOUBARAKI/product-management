package org.kata.product.api.service;

import org.kata.product.api.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product createProduct(Product product);

    void deleteProduct(Integer id);

    Product getProductById(Integer id);

    Product updateProduct(Integer id, Product product);

    Page<Product> findAllProducts(Pageable pageable);
}
