package com.market.E_Commerce.RequestDTO;

import com.market.E_Commerce.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductRequestDto
{
    private int sellerId;
    private String name;
    private double price;
    private int quantity;

    private ProductCategory category;
}
