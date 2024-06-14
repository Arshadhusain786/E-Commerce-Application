package com.market.E_Commerce.Service;

import com.market.E_Commerce.Model.Seller;
import com.market.E_Commerce.Repository.SellerRepository;
import com.market.E_Commerce.RequestDTO.SellerRequestDto;
import com.market.E_Commerce.converter.SellerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService
{
  @Autowired
    SellerRepository sellerRepo;
  public String addseller(SellerRequestDto sellerRequestDto)
  {
      //  only seller object is recognised by this class
      // lets make seller object first using builder
      // setting all paramter in one line code
      Seller seller = SellerConverter.SellerRequestDtoToSeller(sellerRequestDto);
      sellerRepo.save(seller);

      return "Congrats! Now you can starts selling";
  }
}
