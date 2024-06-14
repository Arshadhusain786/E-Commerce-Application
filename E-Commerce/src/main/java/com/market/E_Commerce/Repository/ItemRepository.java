package com.market.E_Commerce.Repository;

import com.market.E_Commerce.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer>
{

}
