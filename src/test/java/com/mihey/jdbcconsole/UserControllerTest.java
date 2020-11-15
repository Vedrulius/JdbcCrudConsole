package com.mihey.jdbcconsole;

import com.mihey.jdbcconsole.controller.UserController;
import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserControllerTest {
    UserController userController;
    int userId;

    @Before
    public void setUp() {
        userController = new UserController();
        userId = userController.saveUser(new User("TestUserName",
                "TestUserSurname", new Region("TestRegion")));
    }

    @Test
    public void shouldReturnPositiveIntegerTest() {
        Assert.assertNotEquals(0, userId);
        Assert.assertTrue(("" + userId).matches("[1-9]\\d*"));
    }

    @After
    public void clear() {
        userController.deleteUser(userId);
    }
}
