package com.market.E_Commerce.ResponseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerResponseDto
{
    private String name;

    private int age;

    private String email;

    private String mobNo;

    private String panNo;
}
