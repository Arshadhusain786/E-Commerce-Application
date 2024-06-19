package com.market.E_Commerce.Service;

import com.market.E_Commerce.Model.Seller;
import com.market.E_Commerce.Repository.SellerRepository;
import com.market.E_Commerce.RequestDTO.SellerRequestDto;
import com.market.E_Commerce.ResponseDTO.SellerResponseDto;
import com.market.E_Commerce.converter.SellerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<SellerResponseDto> getAllSeller()
    {
        // Fetching all sellers from the repository
        List<Seller> sellers = sellerRepo.findAll();

        // Converting Seller to SellerResponseDto
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>(sellers.size());
        for (Seller seller : sellers) {
            SellerResponseDto sellerResponseDto = SellerResponseDto.builder()
                    .name(seller.getName())
                    .age(seller.getAge())
                    .email(seller.getEmail())
                    .mobNo(seller.getMobNo())
                    .panNo(seller.getPanNo())
                    .build();
            sellerResponseDtoList.add(sellerResponseDto);
        }

        // Returning the list of SellerResponseDto
        return sellerResponseDtoList;
    }
    public SellerResponseDto getByPanNo(String panNo)
    {
        // finding seller
        Seller seller = sellerRepo.getByPanNo(panNo);

        // converting seller to selleResponseDto

        SellerResponseDto sellerResponseDto;
        sellerResponseDto = SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .email(seller.getEmail())
                .mobNo(seller.getMobNo())
                .panNo(seller.getPanNo()).build();

        return sellerResponseDto;

    }

    public List<SellerResponseDto> getSellersByAge(int age)
    {
        List<Seller> sellers = sellerRepo.findAll();

        List<SellerResponseDto> requiredSellers = new ArrayList<>();

        for(Seller seller1:sellers)
        {
            SellerResponseDto sellerResponseDto1 = SellerResponseDto.builder()
                    .age(seller1.getAge())
                    .panNo(seller1.getPanNo())
                    .email(seller1.getEmail())
                    .name(seller1.getName())
                    .mobNo(seller1.getMobNo())
                    .build();
            requiredSellers.add(sellerResponseDto1);
        }
        return requiredSellers;

    }
}
