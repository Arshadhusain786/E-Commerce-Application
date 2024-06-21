package com.market.E_Commerce.Controller;

import com.market.E_Commerce.Repository.CartRepository;
import com.market.E_Commerce.RequestDTO.OrderRequestDto;
import com.market.E_Commerce.ResponseDTO.OrderResponseDto;
import com.market.E_Commerce.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController
{
    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody OrderRequestDto orderRequestDto)
    {
        String response="";
        try
        {
            response= cartService.addToCart(orderRequestDto);

        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity(response,HttpStatus.ACCEPTED);
    }
    @PostMapping("/checkout/{customerId}")

    public ResponseEntity checkOutCart(@PathVariable("customerId") int customerId)
    {
        List<OrderResponseDto> orderResponseDtoList;
        try
        {
            orderResponseDtoList = cartService.checkOutCart(customerId);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderResponseDtoList,HttpStatus.ACCEPTED);

    }
}
