package com.app.entity;

import com.app.entities.User;
import org.junit.Before;
import org.junit.Test;
import com.app.entities.Role;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Created by Guillaume Gingembre on 04/11/2017.
 */
public class UserTest {

    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {

        Role role = Role.ADMIN;

        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password");
        user1.setRoles(new HashSet<>(Arrays.asList(role)));

        user2 = new User();
        user2.setUsername("user1");
        user2.setPassword("password");
        user2.setRoles(new HashSet<>(Arrays.asList(role)));

    }

    @Test
    public void equalsTest() throws Exception {
        assertReflectionEquals(user1, user2);
        assertTrue("equals by method", user1.equals(user2));
        user2.setUsername("user2");
        assertFalse("equals by method", user1.equals(user2));
    }

    @Test
    public void hashCodeTest() throws Exception {
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        user2.setUsername("user2");
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

}
