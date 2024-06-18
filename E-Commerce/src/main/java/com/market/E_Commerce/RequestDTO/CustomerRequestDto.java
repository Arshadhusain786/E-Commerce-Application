package com.market.E_Commerce.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDto
{
    private String name;
    private int age;
    private  String email;
    private String mobNo;



}
