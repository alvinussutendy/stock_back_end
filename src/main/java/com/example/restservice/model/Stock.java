package com.example.restservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//This class is Stock model
@Entity // This tells Hibernate to make a table out of this class
public class Stock {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id; //stock id

  private String name; //stock name

  private Long quantity; //stock quantity
  
  private Double price; //stock price

  /**
   * Getter for Stock id
   * @return stock id
   */
  public Long getId() {
    return id;
  }

  /**
   * Setter for stock id
   * @param id: id stock
   */
  public void setId(Long id) {
    this.id = id;
  }
  
  /**
   * Getter for stock name
   * @return stock name
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for stock name
   * @param stock name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Getter for stock quantity
   * @return stock quantity
   */
  public Long getQuantity() {
    return quantity;
  }
  
  /**
   * Setter for stock quantity
   * @param stock quantity
   */
  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
  
  /**
   * Getter for stock price
   * @return stock price
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Setter for stock price
   * @param stock price
   */
  public void setPrice(Double price) {
    this.price = price;
  }
}