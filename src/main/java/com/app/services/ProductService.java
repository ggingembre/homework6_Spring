package com.app.services;

import com.app.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public void delete(Product entity) {
        dao.delete(entity);
    }


}
