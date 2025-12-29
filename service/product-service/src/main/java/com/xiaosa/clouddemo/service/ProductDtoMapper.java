package com.xiaosa.clouddemo.service;

import com.xiaosa.clouddemo.domain.Product;
import com.xiaosa.clouddemo.dto.product.ProductDto;
import com.xiaosa.clouddemo.entity.ProductCommon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {
    ProductDto toDto(Product product);
    ProductCommon toCommon(Product  product);
}
