package com.market.E_Commerce.Repository;

import com.market.E_Commerce.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CardRepository extends JpaRepository<Card,Integer>
{

}
