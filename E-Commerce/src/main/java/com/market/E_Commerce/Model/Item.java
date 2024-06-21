package com.market.E_Commerce.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // it includes both getter and setter and many more
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="item")
@Builder
public class Item
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    int requiredQuantity;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @OneToOne
    @JoinColumn
    Product product;

    @ManyToOne
    @JoinColumn
    Ordered order;

}
