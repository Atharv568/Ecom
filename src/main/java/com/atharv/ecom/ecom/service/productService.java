package com.atharv.ecom.ecom.service;

import com.atharv.ecom.ecom.model.Product;
import com.atharv.ecom.ecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class productService {
    @Autowired
    private ProductRepo repo;


    public List<Product> getAllProducts(Product product) {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }
}
