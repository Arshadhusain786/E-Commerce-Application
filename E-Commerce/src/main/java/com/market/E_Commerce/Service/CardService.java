package com.market.E_Commerce.Service;

import com.market.E_Commerce.Exception.CustomerNotFoundException;
import com.market.E_Commerce.Model.Card;
import com.market.E_Commerce.Model.Customer;
import com.market.E_Commerce.Repository.CardRepository;
import com.market.E_Commerce.Repository.CustomerRepository;
import com.market.E_Commerce.RequestDTO.CardRequestDto;
import com.market.E_Commerce.ResponseDTO.CardDto;
import com.market.E_Commerce.ResponseDTO.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CardService
{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

   public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
       Customer customer;
       try {
           customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
       }
       catch(Exception e)
       {
           throw new CustomerNotFoundException("Invalid customer Id");
       }
       //If customer exist
       // make a card object..
       Card card = Card.builder()
               .cardNo(cardRequestDto.getCardNo())
               .cvv(cardRequestDto.getCvv())
               .cardType(cardRequestDto.getCardType())
               .customer(customer)
               .build();

       // add the card to current card list of customer
       customer.getCards().add(card);

       customerRepository.save(customer); // save both customer and card

       // prepare a response Dto
       CardResponseDto cardResponseDto = new CardResponseDto();
       cardResponseDto.setName(customer.getName());

       //convert every card into card Dtos
       //converting list of cards into list of cardDtos

       List<CardDto>cardDtoList = new ArrayList<>();
       for(Card card1 : customer.getCards())
       {
           CardDto cardDto = new CardDto();
           cardDto.setCardNo(card1.getCardNo());
           cardDto.setCardType(card1.getCardType());

           cardDtoList.add(cardDto);
       }
       cardResponseDto.setCardDtos(cardDtoList);

       return cardResponseDto;

   }
   public String removeCard(int id) throws Exception
   {
       Customer customer = customerRepository.findById(id).get();

       if(customer==null)
       {
           throw new Exception("customer with this id not exist");
       }
       List<Card> cards = cardRepository.findByCustomer(customer);

       for (Card card : cards) {
           cardRepository.delete(card);
       }
       return "Cards removed Successfully of given customer id ";

   }
}
