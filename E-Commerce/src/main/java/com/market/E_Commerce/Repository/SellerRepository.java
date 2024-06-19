package com.market.E_Commerce.Repository;

import com.market.E_Commerce.Model.Seller;
import com.market.E_Commerce.ResponseDTO.SellerResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface SellerRepository extends JpaRepository<Seller,Integer>
{


   public Seller getByPanNo(String panNo);
}
