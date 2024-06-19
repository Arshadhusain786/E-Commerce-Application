package com.market.E_Commerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data // it includes both getter and setter and many more
@NoArgsConstructor
@AllArgsConstructor
@Builder   // now we can make builder of this class (a way to create object)
@Entity
@Table(name="seller")

public class Seller
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    private String name;
    private int age;

    @Column(unique= true)
    private String email;

    @Column(unique= true)
    private String mobNo;

    @Column(unique= true)
    private String panNo;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    // initialize it empty list
    List<Product> products = new ArrayList<>();





}