package com.app.util;

import com.app.services.ProductService;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.app.entities.*;
import com.app.services.UserService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;


/**
 * Util class for creating default entities for test
 *
 */
public class InitDefaultEntities {

    public static void initDefaultUsers(UserService usersService,PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        Collection<Role> roles = Arrays.asList(Role.ADMIN);
        user.setRoles(roles);
        usersService.save(user);

        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("user"));
        Collection<Role> roles1 = Arrays.asList(Role.USER);
        user1.setRoles(roles1);
        usersService.save(user1);

        User user2 = new User();
        user2.setUsername("user1");
        user2.setPassword(passwordEncoder.encode("user1"));
        user2.setRoles(roles1);
        usersService.save(user2);
    }

    public static void initDefaultProducts(ProductService productService){

        Product galaxy = new Product("Galaxy", "Samsung", new BigDecimal(750), "Korean smartphone");
        productService.save(galaxy);

        Product iPhone = new Product("Iphone", "Apple", new BigDecimal(1000),"Expensive smart phone");
        productService.save(iPhone);

        Product xperia = new Product("Xperia", "Sony", new BigDecimal(500), "Smartphone with great camera");
        productService.save(xperia);

    }

}
