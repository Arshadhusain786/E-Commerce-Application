package com.market.E_Commerce.Service;

import com.market.E_Commerce.Enum.ProductCategory;
import com.market.E_Commerce.Exception.SellerNotFoundException;
import com.market.E_Commerce.Model.Product;
import com.market.E_Commerce.Model.Seller;
import com.market.E_Commerce.Repository.ProductRepository;
import com.market.E_Commerce.Repository.SellerRepository;
import com.market.E_Commerce.RequestDTO.ProductRequestDto;
import com.market.E_Commerce.ResponseDTO.ProductResponseDto;
import com.market.E_Commerce.converter.ProductConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch(Exception e)
        {
            throw new SellerNotFoundException("Invalid seller id");
        }
        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        //Adding product to the list
        seller.getProducts().add(product);

        // saved the seller(parent) and product(child)
        sellerRepository.save(seller);

        ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);

        return productResponseDto;

    }

    public List<ProductResponseDto> getProductsByCategory(ProductCategory productCategory)
    {
        List<Product> products = productRepository.findAllByProductCategory(productCategory);

        //prepare a list of response dto
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product:products)
        {
            ProductResponseDto productResponseDto=ProductConvertor.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

}