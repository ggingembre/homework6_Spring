package com.app.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.app.configuration.TestServiceConfiguration;
import com.app.dao.*;
import com.app.entities.User;

import static org.junit.Assert.*;

/**
 * Created by Guillaume Gingembre on 04/11/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestServiceConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserDao dao;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(dao);
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(3, userService.findAll().size());
    }

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setUsername("name4");
        user.setPassword("aaaa");

        assertEquals(3, userService.findAll().size());
        userService.save(user);
        assertEquals(4, userService.findAll().size());

    }

    @Test
    public void update() throws Exception {
        User user = userService.findOne("admin");

        String password = "aaa";
        assertEquals("admin", userService.findOne("admin").getPassword());

        user.setPassword(password);
        userService.save(user);
        assertEquals(password, userService.findOne("admin").getPassword());

    }

    @Test
    public void findOne() throws Exception {
        assertEquals("admin", (String)userService.findOne("admin").getUsername());

    }

    @Test
    @Transactional
    public void deleteTest() throws Exception {
        assertNotNull(userService.findOne("user1"));
        assertEquals(3, userService.findAll().size());

        userService.delete("admin");
        assertNull(userService.findOne("admin"));
        assertEquals(2, userService.findAll().size());

    }


}
