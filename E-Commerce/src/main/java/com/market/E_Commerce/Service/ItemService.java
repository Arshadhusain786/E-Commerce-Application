package com.market.E_Commerce.Service;

import com.market.E_Commerce.Exception.ProductNotFoundException;
import com.market.E_Commerce.Model.Item;
import com.market.E_Commerce.Model.Product;
import com.market.E_Commerce.Repository.ProductRepository;
import com.market.E_Commerce.ResponseDTO.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ItemService
{
    @Autowired
    ProductRepository productRepository;


    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException
    {
        //check product exist or not
        Product product;
        try {
            product = productRepository.findById(productId).get();
        }
        catch (Exception e)
        {
            throw new ProductNotFoundException("Sorry Invalid Product ID");
        }

        // now save the product as an item

        Item item = Item.builder()
                .requiredQuantity(0)
                .product(product).build();

        // map item to product

        product.setItem(item);

        // save both product and item
        productRepository.save(product);

        // prepare the responseDto
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus()).build();

        return itemResponseDto;

    }


}
