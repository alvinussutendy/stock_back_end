package com.example.restservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Stock {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String name;

  private Long quantity;
  
  private Double price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public Long getQuantity() {
    return quantity;
  }
  
  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
  
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}