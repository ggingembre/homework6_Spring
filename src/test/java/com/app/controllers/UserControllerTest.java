package com.app.controllers;

import com.app.configuration.SpringSecurityConfiguration;
import com.app.configuration.TestControllersConfiguration;
import com.app.configuration.WebConfiguration;
import com.app.entities.User;
import com.app.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
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

public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext context;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = mock(User.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void loginTest() throws Exception {
        mvc.perform(get("/login").with(anonymous()))
                .andExpect(status().isOk());
    }

    @Test
    public void logoutTest() throws Exception {
        mvc.perform(post("/logout").with(anonymous()))
                .andExpect(redirectedUrl("/login?logout"))
                .andExpect(status().isFound());
    }

    @Test
    public void authenticatedLogoutTest() throws Exception {
        mvc.perform(post("/logout").with(user("user").roles("ADMIN", "USER")))
                .andExpect(redirectedUrl("/login?logout"))
                .andExpect(status().isFound());
    }


    /*
    why is this failing?? It says:

    java.lang.AssertionError: Model attribute 'users'
    Expected: <[Mock for User, hashCode: 70788844]>
     but: was <[Mock for User, hashCode: 70788844]>
    To me, it looks right?
     */
    @Test
    public void showUsers() throws Exception {
        when(userService.findAll()).thenReturn(Collections.singletonList(user));
        mvc.perform(get("/user/show").with(user("user").roles("ADMIN")))
                .andExpect(model().attribute("users", equalTo(userService.findAll())))
                .andExpect(view().name("users"))
                .andExpect(status().isOk());
    }
}
