package com.market.E_Commerce.converter;

import com.market.E_Commerce.Model.Seller;
import com.market.E_Commerce.RequestDTO.SellerRequestDto;

public class SellerConverter
{
    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto)
    {
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .mobNo(sellerRequestDto.getMobNo())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }
}