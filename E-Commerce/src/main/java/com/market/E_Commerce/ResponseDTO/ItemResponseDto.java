package com.market.E_Commerce.ResponseDTO;


import com.market.E_Commerce.Enum.ProductCategory;
import com.market.E_Commerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto
{
    private String name;
    private double price;
    private ProductCategory productCategory;
    private ProductStatus productStatus;
}
