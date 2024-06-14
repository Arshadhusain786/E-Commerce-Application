package com.market.E_Commerce.ResponseDTO;

import com.market.E_Commerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductResponseDto
{

    private String name;
    private double price;
    private int quantity;
    private ProductStatus productStatus;
}
