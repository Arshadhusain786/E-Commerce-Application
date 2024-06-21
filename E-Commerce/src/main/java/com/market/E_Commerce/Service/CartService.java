package com.market.E_Commerce.Service;

import com.market.E_Commerce.Enum.ProductStatus;
import com.market.E_Commerce.Exception.CustomerNotFoundException;
import com.market.E_Commerce.Exception.ProductNotFoundException;
import com.market.E_Commerce.Model.*;
import com.market.E_Commerce.Repository.CustomerRepository;
import com.market.E_Commerce.Repository.ProductRepository;
import com.market.E_Commerce.RequestDTO.OrderRequestDto;
import com.market.E_Commerce.ResponseDTO.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService
{
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderService orderService;

    public String addToCart(OrderRequestDto orderRequestDto) throws Exception
    {
        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        // Fetch product by ID, throw exception if not found
        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        } catch (Exception e) {
            throw new ProductNotFoundException("Invalid Product Id");
        }

        // Check if the requested quantity is available
        if (product.getQuantity() < orderRequestDto.getRequiredQuantity() || product.getQuantity() == 0) {
            throw new Exception("Sorry! Required quantity not available");
        }
        Cart cart = customer.getCart();
        int newCost = cart.getCartTotal()+ orderRequestDto.getRequiredQuantity()* product.getPrice();
        cart.setCartTotal(newCost);

        // add item to current cart
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);

        cart.getItems().add(item);

        // save customer and cart both
        customerRepository.save(customer);

        return "Item has been added to the cart !";

    }
    public List<OrderResponseDto> checkOutCart(int customerId) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(customerId).get();
        } catch (Exception e) {
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        int totalCost=customer.getCart().getCartTotal();
        Cart cart = customer.getCart();
        for(Item item : cart.getItems())
        {
           Ordered order = new Ordered();
           order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
           order.setDeliveryCharge(0);
           order.setCustomer(customer);
           order.getItems().add(item);

            // Get the first card from the customer's card list
            Card card = customer.getCards().get(0);

            // Mask the card number except for the last four digits
            String cardNo = "";
            for (int i = 0; i < card.getCardNo().length() - 4; i++) {
                cardNo += 'X';
            }
            cardNo += card.getCardNo().substring(card.getCardNo().length() - 4);
            order.setCardUsedForPayment(cardNo);

            // Update the product quantity after placing the order
            int leftQuantity = item.getProduct().getQuantity() - item.getRequiredQuantity();
            if (leftQuantity <= 0) {
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(order);

            // prepare response Dto
            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .productName(item.getProduct().getName())
                    .orderDate(order.getOrderdate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(40)
                    .build();

            orderResponseDtos.add(orderResponseDto);
        }
        cart.setItems(new ArrayList<>());
        cart.setCartTotal(0);
        customerRepository.save(customer);

        //Send an email

        String text = "Congrats! your order with total value"+totalCost+"has been placed";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("evild6395@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("issue book notification");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDtos;
    }

}
