package com.app.dao;

import com.app.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Guillaume Gingembre on 30/10/2017.
 */
public interface ProductDao extends JpaRepository<Product, Long> {
    Product findById(long id);
}
