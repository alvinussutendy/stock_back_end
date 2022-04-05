package com.example.restservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Users {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id; // users id

  private String username; //users username

  private String password; //users password
  
  private String email; //users email

  /**
   * Getter for users id
   * @return users id
   */
  public Long getId() {
    return id;
  }

  /**
   * Setter for users id
   * @param users id
   */
  public void setId(Long id) {
    this.id = id;
  }
  
  /**
   * Getter for users username
   * @return users username
   */
  public String getUsername() {
    return username;
  }
  
  /**
   * Setter for users username
   * @param users username
   */
  public void setUsername(String username) {
    this.username = username;
  }
  
  /**
   * Getter for users password
   * @return users password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setter for users password
   * @param users password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Getter for users email
   * @return users email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setter for users email
   * @param users email
   */
  public void setEmail(String email) {
    this.email = email;
  }
}