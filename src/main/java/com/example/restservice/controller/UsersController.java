package com.example.restservice.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restservice.model.Users;
import com.example.restservice.repository.UsersRepository;

// @ResponseBody means the returned String is the response, not a view name
// @RequestParam means it is a parameter from the GET or POST request

@Controller // This means that this class is a Controller
@RequestMapping(path="/api/users") // This means URL's start with /api/users (after Application path)
public class UsersController {
  @Autowired // This means to get the bean called usersRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UsersRepository usersRepository;
  
  /**
   * Method for create new users
   * @return ResponseEntity: http header and body
   * @param users object
   */
  @CrossOrigin(origins = "http://localhost:3000") //just allow origin from http://localhost:3000
  @PostMapping("/add") //the end point is http://localhost:8080/api/users/add with method post
  public ResponseEntity<Users> createUsers(@RequestBody Users users) {
	 List<Users> usersData = usersRepository.findByUsername(users.getUsername());
    if (!usersData.isEmpty()) {
    	return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    else {
    	try {    	
        	Users _users = new Users();
        	_users.setUsername(users.getUsername());
        	_users.setEmail(users.getEmail());
        	_users.setPassword(users.getPassword());
        	usersRepository.save(_users);
          return new ResponseEntity<>(_users, HttpStatus.CREATED);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  }
  
  /**
   * Method for get all users
   * @return ResponseEntity: http header and body
   */
  @CrossOrigin(origins = "http://localhost:3000") //just allow origin from http://localhost:3000
  @GetMapping(path="/") //the end point is http://localhost:8080/api/users/ with method get
  public ResponseEntity<List<Users>> getAllUsers() {
	    try {
	      List<Users> users = new ArrayList<Users>();
	      usersRepository.findAll().forEach(users::add);  
	      if (users.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(users, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
  
  /**
   * Method for get users by username
   * @return ResponseEntity: http header and body
   * @param users object
   */
  @CrossOrigin(origins = "http://localhost:3000") //just allow origin from http://localhost:3000
  @PostMapping("/") //the end point is http://localhost:8080/api/users/ with method post
  public ResponseEntity<Users> getUsersByUsername(@RequestBody Users users) {
    List<Users> usersData = usersRepository.findByUsername(users.getUsername());
    if (!usersData.isEmpty()) {
    	if(usersData.get(0).getPassword().equals(users.getPassword())) {
    	      return new ResponseEntity<>(HttpStatus.OK);
    	}
    	else {
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	}
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}