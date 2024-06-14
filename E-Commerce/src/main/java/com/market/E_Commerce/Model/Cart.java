package com.market.E_Commerce.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data // it includes both getter and setter and many more
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart")
public class Cart
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    private double cartTotal;

    @OneToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();



}
