package com.market.E_Commerce.Controller;

import com.market.E_Commerce.RequestDTO.CustomerRequestDto;
import com.market.E_Commerce.ResponseDTO.CustomerResponseDto;
import com.market.E_Commerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    CustomerService customerService;

     @PostMapping("/add")
    public String addcustomer(@RequestBody CustomerRequestDto customerRequestDto)
     {
         return customerService.addCustomer(customerRequestDto);
     }

     //get customer name by id

    @GetMapping("get_customer_by_id")
    public ResponseEntity<?> getCustomerById(@RequestParam("id") int id){

        CustomerResponseDto customerResponseDto;
        try {
            customerResponseDto = customerService.getCustomerById(id);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Customer with such Id does not Exist!!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerResponseDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("get_all_customer")
    public List<CustomerResponseDto> getAllCustomer()
    {
      return   customerService.getAllCustomer();

    }
    @DeleteMapping("delete_by_id")
    public ResponseEntity<?> deleteCustomerById(@RequestParam("id") int id) {
        try {
            String result = customerService.deleteCustomerById(id);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }
        catch (RuntimeException e)
        {
            return new ResponseEntity<>("Customer with such ID does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("get_by_email")
    public ResponseEntity<?> getCustomerByEmail(@RequestParam String email)
    {
       CustomerResponseDto customerResponseDto;
       try{
           customerResponseDto= customerService.getCustomerByEmail(email);

       }
       catch(Exception e)
       {
           return new ResponseEntity<>("Customer with this email does not exist",HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(customerResponseDto, HttpStatus.ACCEPTED);
    }


}
