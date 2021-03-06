package com.example.restservice.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restservice.model.Stock;
import com.example.restservice.repository.StockRepository;

// This class is used as a controller for Stock model

@Controller // This means that this class is a Controller
@RequestMapping(path="/api/stock") // This means URL's start with /api/stock (after Application path)
public class StockController {
  @Autowired // This means to get the bean called stockRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private StockRepository stockRepository; //repository for connecting to Stock table
  
  /**
   * Method for create new stock
   * @return ResponseEntity: http header and body
   * @param stock object
   */
  @CrossOrigin(origins = "http://localhost:3000") //just allow origin from http://localhost:3000
  @PostMapping("/add") //the end point is http://localhost:8080/api/stock/add with method post
  public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
    try {
    	Stock _stock = new Stock();
    	_stock.setName(stock.getName());
    	_stock.setQuantity(stock.getQuantity());
    	_stock.setPrice(stock.getPrice());
    	stockRepository.save(_stock);
      return new ResponseEntity<>(_stock, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  /**
   * Method for get all stock
   * @return ResponseEntity: http header and body
   */
  @CrossOrigin(origins = "http://localhost:3000") //just allow origin from http://localhost:3000
  @GetMapping(path="/") //the end point is http://localhost:8080/api/stock/ with method get
  public ResponseEntity<List<Stock>> getAllStock() {
	    try {
	      List<Stock> stock = new ArrayList<Stock>();
	      stockRepository.findAll().forEach(stock::add);  
	      if (stock.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(stock, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
  
  /**
   * Method for get stock by id
   * @return ResponseEntity: http header and body
   * @param stock id
   */
  @CrossOrigin(origins = "http://localhost:3000") //just allow origin from http://localhost:3000
  @GetMapping("/{id}") //the end point is http://localhost:8080/api/stock/{id} with method get
  public ResponseEntity<Stock> getStockById(@PathVariable("id") long id) {
    Optional<Stock> stockData = stockRepository.findById(id);
    if (stockData.isPresent()) {
    	return new ResponseEntity<>(stockData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * Method for update stock
   * @return ResponseEntity: http header and body
   * @param stock id and stock object
   */
  @CrossOrigin(origins = "http://localhost:3000") //just allow origin from http://localhost:3000
  @PutMapping("/{id}") //the end point is http://localhost:8080/api/stock/{id} with method put
  public ResponseEntity<Stock> updateStock(@PathVariable("id") long id, @RequestBody Stock stock) {
    Optional<Stock> stockData = stockRepository.findById(id);
    if (stockData.isPresent()) {
    	Stock _stock = stockData.get();
      _stock.setName(stock.getName());
      _stock.setQuantity(stock.getQuantity());
      _stock.setPrice(stock.getPrice());
      return new ResponseEntity<>(stockRepository.save(_stock), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * Method for delete stock
   * @return ResponseEntity: http header and body
   * @param stock id
   */
  @CrossOrigin(origins = "http://localhost:3000") //just allow origin from http://localhost:3000
  @DeleteMapping("/{id}") //the end point is http://localhost:8080/api/stock/{id} with method delete
  public ResponseEntity<HttpStatus> deleteStock(@PathVariable("id") long id) {
    try {
      stockRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}