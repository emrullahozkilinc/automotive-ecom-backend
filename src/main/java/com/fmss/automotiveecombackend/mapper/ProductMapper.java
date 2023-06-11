package com.fmss.automotiveecombackend.mapper;

import com.fmss.automotiveecombackend.data.dbmodel.Product;
import com.fmss.automotiveecombackend.data.dto.request.AddProductPayload;
import com.fmss.automotiveecombackend.data.dto.request.EditProductPayload;
import com.fmss.automotiveecombackend.data.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(implementationName = "ProductMapperImpl", componentModel = "spring")
public interface ProductMapper {

    Product fromAddProductPayloadToProduct(AddProductPayload payload);
    void fromEditProductPayloadToProduct(@MappingTarget Product product, EditProductPayload payload);
    ProductResponse toProductResponse(Product product);
    Product toProduct(ProductResponse productResponse);
}
