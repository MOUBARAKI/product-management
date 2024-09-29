package org.kata.product.api.service.impl;

import org.kata.product.api.exception.ProductNotFoundException;
import org.kata.product.api.mapper.ProductMapper;
import org.kata.product.api.models.Product;
import org.kata.product.api.persistence.repository.ProductRepository;
import org.kata.product.api.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product createProduct(Product product) {
        product.setCreatedAt(System.currentTimeMillis());
        product.setUpdatedAt(System.currentTimeMillis());
        return productMapper.toDto(productRepository.save(productMapper.toEntity(product)));
    }

    @Override
    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.toDto(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        org.kata.product.api.persistence.entities.Product savedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        org.kata.product.api.persistence.entities.Product updatedProduct =
                productMapper.updateProductFromDto(product, savedProduct);
        updatedProduct.setUpdatedAt(System.currentTimeMillis());
        return productMapper.toDto(productRepository.save(updatedProduct));
    }
}
