package com.app.services;

import com.app.configuration.TestServiceConfiguration;
import com.app.dao.ProductDao;
import com.app.entities.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Guillaume Gingembre on 05/11/2017.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestServiceConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductServiceTest {

    @Autowired
    private ProductDao dao;

    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductService(dao);
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(3, productService.findAll().size());
    }

    @Test
    public void save() throws Exception {
        Product product = new Product();
        product.setName("Android Phone");
        product.setDescription("Phone made by Google for Google");
        product.setPrice(new BigDecimal(1000));
        product.setProducer("Google");

        assertEquals(3, productService.findAll().size());
        productService.save(product);
        assertEquals(4, productService.findAll().size());
    }

    @Test
    public void update() throws Exception {
        Product product = productService.findOne(1L);

        String name = "IPhone";
        assertEquals(name, productService.findOne(1L).getName());

        product.setName("Iphone 8");
        productService.save(product);
        assertEquals("Iphone 8", productService.findOne(1L).getName());

    }

    @Test
    public void findOne() throws Exception {
        assertEquals("IPhone", (String)productService.findOne(1L).getName());

    }

    @Test
    public void findById() throws Exception {
        assertEquals("IPhone", (String)productService.findOne(1L).getName());

    }

    @Test
    @Transactional
    public void deleteTest() throws Exception {
        assertNotNull(productService.findOne(3L));
        assertEquals(3, productService.findAll().size());

        productService.delete(3L);
        assertNull(productService.findOne(3L));
        assertEquals(2, productService.findAll().size());
    }

    @Test
    public void exists() throws Exception {
        assertEquals(true, (boolean) productService.exists(1L));
    }

}
