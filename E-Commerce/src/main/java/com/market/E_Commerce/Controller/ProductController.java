package com.market.E_Commerce.Controller;

import com.market.E_Commerce.Enum.ProductCategory;
import com.market.E_Commerce.RequestDTO.ProductRequestDto;
import com.market.E_Commerce.ResponseDTO.ProductResponseDto;
import com.market.E_Commerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto)
    {
        ProductResponseDto productResponseDto;
        try {
            productResponseDto = productService.addProduct(productRequestDto);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/get_products_by_category/{category}")
    public ResponseEntity<?> getProductsByCategoty(@PathVariable("category")ProductCategory productCategory)
    {
        List<ProductResponseDto> productResponseDtos =  productService.getProductsByCategory(productCategory);
        if(productResponseDtos.isEmpty())
        {
            return new ResponseEntity<>("Product not exist of that Category",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productResponseDtos,HttpStatus.ACCEPTED);
    }

}
