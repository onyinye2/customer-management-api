package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double total_sales;
    private Double balance_due;
    
    public Double getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(Double total_sales) {
        this.total_sales = total_sales;
    }

    public Double getBalance_due() {
        return balance_due;
    }

    public void setBalance_due(Double balance_due) {
        this.balance_due = balance_due;
    }
    

    public Customer(String name) {
        this.name = name;
        this.total_sales = 0.0;
        this.balance_due = 0.0;
    }

    public Customer(){
        
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
    
}
