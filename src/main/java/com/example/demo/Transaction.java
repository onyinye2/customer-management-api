package com.example.demo;

public class Transaction {
    private Long id;
    private Long quantity;
    private Double total;
    private String tName;

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Transaction(){}

    public Transaction(Long id, Long quantity, Double total, String name) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.tName = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
