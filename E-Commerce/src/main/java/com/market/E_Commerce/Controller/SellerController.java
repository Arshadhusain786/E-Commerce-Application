package com.market.E_Commerce.Controller;

import com.market.E_Commerce.RequestDTO.SellerRequestDto;
import com.market.E_Commerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    // Get all the authors



}