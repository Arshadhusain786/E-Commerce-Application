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

@Service
public class OrderService {
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemService itemService;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception
    {
        /*customer is valid or not
        product exist or not
        quantity is sufficient or not
        view item first also get the item
        if it comes here means customer ID is valid, product exist, quantity is available,view
        now we are ready to place the order
        calculate the total cost of product
        prepare order
        prepare the card String
        update customer current order list
        out of stock case handle
        after placing order product quantity decrease
        subtract from product
        update item
        save customer, order automatically saved && product, item automatically saved
        prepare responseDto */

        Customer customer = customerRepository.findById(orderRequestDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Invalid Customer id !!!"));

        // Check if the product exists
        Product product = productRepository.findById(orderRequestDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Invalid Product Id"));

        // Check if the required quantity is available
        if (product.getQuantity() < orderRequestDto.getRequiredQuantity() || product.getQuantity() == 0) {
            throw new Exception("Sorry! Required quantity not available");
        }

        // Initialize a new order
        Ordered order = new Ordered();
        order.setTotalCost(orderRequestDto.getRequiredQuantity() * product.getPrice());
        order.setDeliveryCharge(40);

        // Check if the customer has any cards
        if (customer.getCards().isEmpty()) {
            throw new Exception("No cards available for customer");
        }

        // Get the first card from the customer's card list
        Card card = customer.getCards().get(0);

        // Mask the card number except for the last four digits
        String cardNo = "X".repeat(card.getCardNo().length() - 4) + card.getCardNo().substring(card.getCardNo().length() - 4);
        order.setCardUsedForPayment(cardNo);

        // Create an item for the order and associate it with the product
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(order);

        // Add the item to the order
        order.getItems().add(item);

        // Associate the order with the customer
        order.setCustomer(customer);

        // Update the product quantity after placing the order
        int leftQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        if (leftQuantity <= 0) {
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        product.setQuantity(leftQuantity);

        // Save the updated product
        productRepository.save(product);

        // Add the order to the customer's order list
        customer.getOrders().add(order);

        // Save the customer, which will also save the order due to cascading
        Customer savedCustomer = customerRepository.save(customer);

        // Check if the orders list is not empty before accessing the last element
        if (savedCustomer.getOrders().isEmpty()) {
            throw new Exception("Order not saved correctly");
        }
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size() - 1);

        // Prepare and return the response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getName())
                .orderDate(savedOrder.getOrderdate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .build();

        // Send an email notification
        String text = "Congrats! Your order with total value " + order.getTotalCost() + " has been placed";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("evild6395@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Your order has been placed successfully");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDto;
    }
}
