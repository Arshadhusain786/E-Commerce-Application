package com.market.E_Commerce.Model;


import com.market.E_Commerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // it includes both getter and setter and many more
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="card")
@Builder

public class Card
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    private String cardNo;
    private int cvv;
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;

}
