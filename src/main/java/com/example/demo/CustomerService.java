package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    //private List<Customer> customerList;

    @Autowired private CustomerRepository repo;

    public CustomerService() {
        //Customer cOne = new Customer(0, "John Doe");
        //Customer cTwo = new Customer(1, "Jane Adams");
        //Customer cThree = new Customer(2, "Alice Jones");

        //this.
        //customerList = new ArrayList<Customer>(){{add(cOne); add(cTwo); add(cThree);}};
        //repo.saveAll(customerList);
    }

    public List<Customer> getCustomers(){
        //return customerList;
        return (List<Customer>) repo.findAll();
    }

    public Optional<Customer> getById(long id){
        //return customerList.get((int) id);
        return repo.findById(id); //not getreferencebyid
    }

    public Customer createCustomer(Customer newCustomer){
        long len = repo.count();
        //customerList.add(newCustomer);
        return repo.save(newCustomer);
        /*if(repo.count() > len){
            return true;
        }else{
            return false;
        }*/
    }

    public Customer updateCustomer(long id, String updatedName){
        //Customer c = customerList.get((int) id); // customer val
        Optional<Customer> customer = repo.findById(id);
        //int cIndex = customerList.indexOf(c); //customer val index
        if(customer.isPresent()){
            Customer c = customer.get();
            //String oldText = c.getName(); //get old name
            c.setName(updatedName); //set new name
            return repo.save(c);
            /*if(oldText.equalsIgnoreCase(updatedName)){
                return new Customer(-1, "Failed to Update Customer");
            }else{
                return repo.save(c);
            }*/
        }else{
            return new Customer();
        }
    }

    public boolean deleteCustomer(Long id){
        long len = repo.count();
        //customerList.remove(c);
        repo.deleteById(id);
        if(repo.count() < len){
            return true;
        }else{
            return false;
        }

    }

    public boolean purchase(Long id, Double value){
        Optional<Customer> customer = repo.findById(id);
        
        if(customer.isPresent()){
            Customer c = customer.get();
            double total = c.getTotal_sales() + value;
            if(total < 0.0){
                repo.save(c); //if invalid return before changing value
                return false;
            }
            c.setTotal_sales(total);
            repo.save(c);
            return true;
        }else{
            return false;
        }
    }

    public boolean purchaseWithCredit(Long id, Double value){
        Optional<Customer> customer = repo.findById(id);
        
        if(customer.isPresent()){
            Customer c = customer.get();
            double sTotal = c.getTotal_sales() + value;
            double bTotal = c.getBalance_due() + value;
            if(sTotal < 0.0){
                repo.save(c); //if invalid return before changing value
                return false;
            }
            if(bTotal < 0.0){
                repo.save(c); //if invalid return before changing value
                return false;

            }
            c.setTotal_sales(sTotal);//set new name
            c.setBalance_due(bTotal);
            repo.save(c);
            return true;
        }else{
            return false; //would be better to return an object with message
        }
    }

    public boolean makePayment(Long id, Double value){
        Optional<Customer> customer = repo.findById(id);
        
        if(customer.isPresent()){
            Customer c = customer.get();
            if(value > c.getBalance_due()){
                repo.save(c); // cannot make payment greater than what is due;
                return false;
            }
            c.setBalance_due(c.getBalance_due() - value);
            repo.save(c);
            return true;
        }else{
            return false;
        }
    }
    
}
