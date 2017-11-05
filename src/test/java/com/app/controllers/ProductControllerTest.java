package com.app.controllers;

import com.app.configuration.SpringSecurityConfiguration;
import com.app.configuration.TestControllersConfiguration;
import com.app.configuration.WebConfiguration;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Guillaume Gingembre on 03/11/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class
        , SpringSecurityConfiguration.class
        , TestControllersConfiguration.class})
public class ProductControllerTest {

    private MockMvc mvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private WebApplicationContext context;

    private Product product;
    private User user;
    private long id;

    @Before
    public void setUp() throws Exception {
        product = Mockito.mock(Product.class);
        user = Mockito.mock(User.class);
        id = 1L;
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void addTest() throws Exception {
        mvc.perform(get("/product/add").with(user("user").roles("ADMIN", "USER")))
                .andExpect(view().name("productRegistration"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveTest() throws Exception {
        mvc.perform(post("/product/add/")
                .with(user("user")
                        .roles("ADMIN"))
                .flashAttr("product", product))
                .andExpect(model().attribute("product", product));
    }

    @Test
    public void infoTest() throws Exception {
        Mockito.when(productService.exists(id)).thenReturn(true);
        Mockito.when(productService.findOne(1L)).thenReturn(product);
        mvc.perform(get("/product/{id}", 1).with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("product", productService.findOne(1L)))
                .andExpect(view().name("productView"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        Mockito.when(productService.exists(id)).thenReturn(true);
        mvc.perform(get("/product/{id}/delete", id).with(user("user").roles("ADMIN")))
                .andExpect(view().name("productDeleted"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        Mockito.when(productService.exists(id)).thenReturn(true);
        Mockito.when(productService.findById(id)).thenReturn(product);
        mvc.perform(get("/product/{id}/update", id)
                .with(user("user").roles("ADMIN")))
                .andExpect(model().attribute("product", productService.findOne(id)))
                .andExpect(view().name("productUpdateForm"))
                .andExpect(status().isOk());
    }


    @Test
    public void updatedTest() throws Exception {
        Mockito.when(productService.exists(id)).thenReturn(true);
        mvc.perform(post("/product/updated/", id)
                .with(user("user")
                        .roles("ADMIN"))
                .flashAttr("command", product))
                .andExpect(model().attribute("command", product))
                .andExpect(view().name("productUpdated"))
                .andExpect(status().isOk());
    }

    @Test
    public void searchFormTest() throws Exception {
        mvc.perform(get("/product/search").with(user("user").roles("ADMIN", "USER")))
                .andExpect(view().name("productSearch"))
                .andExpect(status().isOk());
    }

    @Test
    public void searchResultTest() throws Exception {
        Mockito.when(productService.exists(id)).thenReturn(true);
        mvc.perform(post("/product/search/", product)
                .with(user("user")
                        .roles("ADMIN", "USER"))
                .flashAttr("productSearch", product))
                .andExpect(model().attribute("productSearch", product))
                .andExpect(view().name("productSearchResult"))
                .andExpect(status().isOk());
    }

    @Test
    public void showProductsTest() throws Exception {
        when(productService.findAll()).thenReturn(Collections.singletonList(product));
        mvc.perform(get("/product/showAll").with(user("admin").roles("ADMIN")))
                .andExpect(model().attribute("products", equalTo(productService.findAll())))
                .andExpect(view().name("products"))
                .andExpect(status().isOk());
    }
}
