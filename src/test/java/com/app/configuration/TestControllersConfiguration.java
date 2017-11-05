package com.app.configuration;

import com.app.services.ProductService;
import com.app.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * Created by Guillaume Gingembre on 03/11/2017.
 */

@Configuration
public class TestControllersConfiguration {

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public ProductService productService() {
        return mock(ProductService.class);
    }

}
