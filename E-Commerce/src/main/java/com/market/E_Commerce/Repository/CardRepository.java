package com.market.E_Commerce.Repository;

import com.market.E_Commerce.Model.Card;
import com.market.E_Commerce.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CardRepository extends JpaRepository<Card,Integer>
{
    public List<Card> findByCustomer(Customer customer);
}
