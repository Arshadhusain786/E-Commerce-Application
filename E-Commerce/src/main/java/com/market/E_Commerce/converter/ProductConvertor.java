package com.market.E_Commerce.converter;

import com.market.E_Commerce.Enum.ProductStatus;
import com.market.E_Commerce.Model.Product;
import com.market.E_Commerce.RequestDTO.ProductRequestDto;
import com.market.E_Commerce.ResponseDTO.ProductResponseDto;

public class ProductConvertor
{
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto)
    {
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
    public static ProductResponseDto productToProductResponseDto(Product product)
    {
        return  ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
