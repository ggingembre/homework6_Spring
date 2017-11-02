package com.app.services;

import com.app.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.entities.Product;

import java.util.List;

/**
 * Created by Guillaume Gingembre on 30/10/2017.
 */

@Service
public class ProductService {

    private final ProductDao dao;

    @Autowired
    public ProductService(ProductDao dao) {
        this.dao = dao;
    }

    @Transactional
    public <S extends Product> S save(S s) {
        return dao.save(s);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById (Long s) {
        return dao.findById(s);
    }

    @Transactional(readOnly = true)
    public Product findOne(Long s) {
        return dao.findOne(s);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long s) {
        return dao.exists(s);
    }

    @Transactional
    public void delete(long s) {
        Product entity = dao.findById(s);
        dao.delete(entity);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllExample (Product product) {

        if (product.getName().isEmpty()) {
            product.setName(null);
        }

        if (product.getProducer().isEmpty()) {
            product.setProducer(null);
        }

        if (product.getDescription().isEmpty()) {
            product.setDescription(null);
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withIgnorePaths("id","price");

        // ignore numbers, because I did not find how to deal with ExampleMatcher and numbers
        // I will write my own logic about numbers in the controller, but it is an area of improvement for this code

        Example<Product> example = Example.of(product, matcher);

        return dao.findAll(example);
    }

}
