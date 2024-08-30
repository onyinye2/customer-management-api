package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable long id){
        return customerService.getById(id);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer newCustomer){
        return customerService.createCustomer(newCustomer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable long id, @RequestBody String updatedName){
        return customerService.updateCustomer(id, updatedName); //just send in a plain string
    }                                                           // "blah blah" not needed

    @DeleteMapping("/{id}")
    public void  deleteCustomer(@PathVariable long id){
        //Customer c = customerService.getById(id);
        customerService.deleteCustomer(id);
    }

    @PostMapping("/purchase/{id}")
    public boolean purchase(@PathVariable Long id, @RequestBody Transaction t){
        return customerService.purchase(id, t.getTotal());
    }

    @PostMapping("/purchasewc/{id}")
    public boolean purchaseWithCredit(@PathVariable Long id, @RequestBody Transaction t){
        return customerService.purchaseWithCredit(id, t.getTotal());
    }

    @PostMapping("/payment/{id}")
    public boolean makePayment(@PathVariable Long id, @RequestBody Transaction t){
        return customerService.makePayment(id, t.getTotal());
    }


    



    
}
