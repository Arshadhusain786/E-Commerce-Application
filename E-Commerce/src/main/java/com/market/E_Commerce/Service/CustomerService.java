package com.market.E_Commerce.Service;

import com.market.E_Commerce.Model.Cart;
import com.market.E_Commerce.Model.Customer;
import com.market.E_Commerce.Repository.CustomerRepository;
import com.market.E_Commerce.RequestDTO.CustomerRequestDto;
import com.market.E_Commerce.ResponseDTO.CustomerResponseDto;
import com.market.E_Commerce.converter.CustomerConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = CustomerConvertor.CustomerRequestDtoToCustomer(customerRequestDto);

        //allocate a cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        //set cart in customer
        customer.setCart(cart);
        customerRepository.save(customer);
        return "Congrats! welcome to the market";

    }

    public CustomerResponseDto getCustomerById(int id) {

        Customer customer = customerRepository.findById(id).get();

        //convert customer to customerResponseDto
        CustomerResponseDto customerResponseDto;

        customerResponseDto = CustomerResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .build();

        return customerResponseDto;
    }

    public List<CustomerResponseDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();

        // Convert customer to customer response DTOs
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>(customers.size());

        for (Customer customer : customers) {
            CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
                    .name(customer.getName())
                    .age(customer.getAge())
                    .email(customer.getEmail())
                    .mobNo(customer.getMobNo())
                    .build();

            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }

    public String deleteCustomerById(int id) {
        List<Customer> customers = customerRepository.findAll();
        boolean customerPresent = false;

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customerRepository.deleteById(id);
                customerPresent = true;
                break; // Exit the loop once the customer is found and deleted
            }
        }

        if (!customerPresent) {
            throw new RuntimeException("Customer does not exist");
        } else {
            return "Customer deleted successfully";
        }
    }
    @GetMapping("/get_by_email")
    public CustomerResponseDto getCustomerByEmail(String email)
    {
        Customer customer = customerRepository.getCustomerByEmail(email);

        // prepare customer Response Dto
        // converting customer to customerResponseDto

        CustomerResponseDto customerResponseDto;
        customerResponseDto=CustomerResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .build();
        return customerResponseDto;

    }

}

