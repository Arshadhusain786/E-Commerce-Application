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
@Table(name="customer")

public class Customer
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    private String name;

    @Column(unique= true)
    private String email;

    @Column(unique= true)
    private String mobNo;

    @OneToMany(mappedBy = "customer",cascade=CascadeType.ALL)
    List<Card> cards = new ArrayList<>();

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Ordered> orders = new ArrayList<>();




}
