package com.market.E_Commerce.Controller;

import com.market.E_Commerce.Exception.SellerNotFoundException;
import com.market.E_Commerce.Model.Seller;
import com.market.E_Commerce.RequestDTO.SellerRequestDto;
import com.market.E_Commerce.ResponseDTO.SellerResponseDto;
import com.market.E_Commerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController
{
    @Autowired
    SellerService sellerService;

    @PostMapping("add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto)
    {
        return sellerService.addseller(sellerRequestDto);
    }
    // Get all the sellers
    @GetMapping("/get_all_seller")
    public List<SellerResponseDto> getAllSeller()
    {
        return sellerService.getAllSeller();

    }
    // get a seller by pancard
    @GetMapping("/get_seller_by_pan")
    public ResponseEntity<?> getByPanNo(@RequestParam("panNo")String panNo)
    {
        SellerResponseDto sellerResponseDto;
        try{
            sellerResponseDto = sellerService.getByPanNo(panNo);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("seller with this pan no. not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sellerResponseDto,HttpStatus.ACCEPTED);

    }
    // find sellers of particular age

    @GetMapping("/get_sellers_by_age")
    public ResponseEntity<?> getSellersByAge(@RequestParam("age") int age) throws SellerNotFoundException
    {
        List<SellerResponseDto> sellerResponseDtoList = sellerService.getSellersByAge(age) ;
        if(sellerResponseDtoList.isEmpty())
        {
            return new ResponseEntity<>("No Seller Exist for this Age", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sellerResponseDtoList,HttpStatus.ACCEPTED);
    }

    }


