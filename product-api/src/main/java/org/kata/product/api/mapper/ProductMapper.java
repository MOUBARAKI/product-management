package org.kata.product.api.mapper;

import org.kata.product.api.persistence.entities.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(final org.kata.product.api.models.Product product);
    org.kata.product.api.models.Product toDto(final Product product);
    List<org.kata.product.api.models.Product> toDtoList(final List<Product> products);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromDto(org.kata.product.api.models.Product productDTO, @MappingTarget Product product);
}
