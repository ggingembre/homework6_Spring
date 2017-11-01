package com.app.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.app.entities.*;
import com.app.services.UserService;


/**
 * Util class for creating default entities for test
 *
 * @KontarMaryna
 */
public class InitDefaultEntities {

    public static void initDefaultUsers(UserService usersService,PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRole(Role.ADMIN);

        usersService.save(user);


        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("user"));
        user1.setRole(Role.USER);
        usersService.save(user1);

        User user2 = new User();
        user2.setUsername("user1");
        user2.setPassword(passwordEncoder.encode("user1"));
        usersService.save(user2);
    }
}
