package com.app.entity;

import com.app.entities.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;


import static org.junit.Assert.*;

/**
 * Created by Guillaume Gingembre on 04/11/2017.
 */
public class ProductTest {

    private Product product1;
    private Product product2;

    @Before
    public void Init() throws Exception {

        product1 = new Product();
        product1.setId(1L);
        product1.setName("Iphone");
        product1.setDescription("Expensive phone");
        product1.setProducer("Apple");
        product1.setPrice(new BigDecimal(1000));

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Xperia");
        product2.setDescription("Smartphone with great camera");
        product2.setProducer("Sony");
        product2.setPrice(new BigDecimal(600));
    }

    @Test
    public void equalsTest() throws Exception {
        assertFalse("equals by method", product1.equals(product2));
        product2.setId(1L);
        assertTrue("equals by method", product1.equals(product2));
    }

    @Test
    public void hashCodeTest() throws Exception {
        assertNotEquals(product1, product2);
        assertNotEquals(product1.hashCode(), product2.hashCode());
        product2.setId(1L);
        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    public void creationTest() {
        assertEquals("Iphone", product1.getName());
        assertEquals(BigDecimal.valueOf(1000), product1.getPrice());
        assertEquals("Apple", product1.getProducer());
        assertEquals("Expensive phone", product1.getDescription());
    }


}
