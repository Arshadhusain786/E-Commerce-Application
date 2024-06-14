package com.market.E_Commerce.Repository;
import com.market.E_Commerce.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>
{

}
