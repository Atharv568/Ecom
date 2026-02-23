package com.atharv.ecom.ecom.service;

import com.atharv.ecom.ecom.model.Product;
import com.atharv.ecom.ecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Product addproduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImage(imageFile.getOriginalFilename());
        product.setImagetype(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }


    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImagetype(product.getImagetype());
        product.setImage(imageFile.getOriginalFilename());
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
    return repo.searchProducts(keyword);

    }
}
