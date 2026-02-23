package com.atharv.ecom.ecom.controller;

import com.atharv.ecom.ecom.model.Product;
import com.atharv.ecom.ecom.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

      @PostMapping("/product")
      public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
            try{
                Product product1 = service.addproduct(product, imageFile);
                return new ResponseEntity<>(product1, HttpStatus.CREATED);
            } catch(Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
            }
      }
      @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int productId){
      Product product = service.getProductById(productId);
          byte[] imageFile= product.getImageData();
          return ResponseEntity.ok().body(imageFile);
      }
      @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                                @RequestPart Product product, @RequestPart MultipartFile imageFile){
          Product product1= null;
          try {
              product1 = service.updateProduct(id, product, imageFile);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
          if(product1!= null)
              return new ResponseEntity<>("Updated the Prodcut", HttpStatus.OK);
          else
              return new ResponseEntity<>("Cound'nt update the produc", HttpStatus.BAD_REQUEST);
      }

      @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
          Product product = service.getProductById(id);
          if(product!= null){
              service.deleteProduct(id);
              return new ResponseEntity<>("Product deleted", HttpStatus.OK);
          }
          else {
              return new ResponseEntity<>("Product not Found", HttpStatus.NOT_FOUND);
          }
      }
      @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
          List<Product> products= service.searchProducts(keyword);
          return new ResponseEntity<>(products, HttpStatus.OK);
      }


}


