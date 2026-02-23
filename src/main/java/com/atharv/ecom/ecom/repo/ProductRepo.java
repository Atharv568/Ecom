package com.atharv.ecom.ecom.repo;

import com.atharv.ecom.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
     @Query("Select p from Product p where "+
             "Lower(p.name) like lower(concat('%', :keyword , '%')) or "+
             "Lower(p.brand) like lower(concat('%', :keyword , '%')) or "+
             "Lower(p.category) like lower(concat('%', :keyword , '%')) or "+
             "Lower(p.desc) like lower(concat('%', :keyword , '%')) ")

    List<Product> searchProducts(String keyword);
}
