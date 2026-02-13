package com.atharv.ecom.ecom.controller;

import com.atharv.ecom.ecom.model.Product;
import com.atharv.ecom.ecom.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private productService service;

      @RequestMapping("/")
      public String greet(){
          return "Hello World to Ecom Page";
      }

      @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(Product product){
          return new ResponseEntity<>(service.getAllProducts(product), HttpStatus.OK);
      }
      @GetMapping("/product/{id}")
      public ResponseEntity<Product> getProductById(@PathVariable int id){
          Product product = service.getProductById(id);
          if(product != null)
          return new ResponseEntity (service.getProductById(id), HttpStatus.OK);
          else
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
}
