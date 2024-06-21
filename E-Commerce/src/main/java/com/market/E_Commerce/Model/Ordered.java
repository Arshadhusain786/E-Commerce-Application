package com.market.E_Commerce.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data // it includes both getter and setter and many more
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="orders")
public class Ordered
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;

    @CreationTimestamp
    private Date orderdate;
    private int totalCost;
    private int deliveryCharge;
    private String cardUsedForPayment;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();



}
