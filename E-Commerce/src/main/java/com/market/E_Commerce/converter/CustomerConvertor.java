package com.market.E_Commerce.converter;

import com.market.E_Commerce.Model.Customer;
import com.market.E_Commerce.RequestDTO.CustomerRequestDto;
import lombok.experimental.UtilityClass;


@UtilityClass
public class CustomerConvertor
{
    public Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto)
    {
       return Customer.builder()
               .name(customerRequestDto.getName())
               .age(customerRequestDto.getAge())
               .email(customerRequestDto.getEmail())
               .mobNo(customerRequestDto.getMobNo())
               .build();
    }
}
