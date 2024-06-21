
package com.market.E_Commerce.Model;


import com.market.E_Commerce.Enum.ProductCategory;
import com.market.E_Commerce.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // it includes both getter and setter and many more
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="product")

public class Product
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    private String name;
    private int price;
    private int quantity;

    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;


    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    Item item;


}