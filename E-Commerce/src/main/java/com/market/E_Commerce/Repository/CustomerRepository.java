package com.market.E_Commerce.Repository;

import com.market.E_Commerce.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>
{

    public Customer getCustomerByEmail(String email);

}
