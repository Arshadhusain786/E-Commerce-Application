package com.market.E_Commerce.Controller;


import com.market.E_Commerce.RequestDTO.CardRequestDto;
import com.market.E_Commerce.ResponseDTO.CardResponseDto;

import com.market.E_Commerce.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")


public class CardController
{
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto)
    {
        CardResponseDto cardResponseDto;
       try{
           cardResponseDto=cardService.addCard(cardRequestDto);
       }
       catch(Exception e)
       {
           return  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity(cardResponseDto,HttpStatus.ACCEPTED);
    }
    //  //remove all cards for a particular customer id

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeCard(@RequestParam("id") int id)
    {
        try{
            cardService.removeCard(id);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Customer Not Exist!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("All Card with given customer id deleted successfully",HttpStatus.ACCEPTED);

    }


}
