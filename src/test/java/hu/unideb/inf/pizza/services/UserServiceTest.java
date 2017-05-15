package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.managers.JpaConnectionManager;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.UserServiceInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {

    private ConnectionManager connectionManager;

    private UserServiceInterface userService;

    @Before
    public void init() {
        connectionManager = new JpaConnectionManager("test");
        userService = new UserService(connectionManager);
    }

    @After
    public void destroy() {
        connectionManager.close();
    }

    @Test
    public void createUser() {
        // Given
        User user = this.createTestUser();

        // When
        User savedUser = userService.createUser(user);

        // Then
        User expectedUser = userService.getUserById(savedUser.getId());

        Assert.assertEquals(expectedUser.getEmail(), user.getEmail());
    }

    @Test
    public void getUserByEmail() {
        // Given
        User user = this.createTestUser();

        // When
        userService.createUser(user);

        // Then
        Assert.assertEquals(userService.getUserByEmail(user.getEmail()).getId(), user.getId());
    }

    @Test
    public void updateUser() {
        // Given
        User user = this.createTestUser();

        // When
        userService.createUser(user);
        user.setName("Teszt Elek");
        user.setEmail("teszt.elek@example.org");
        user.setPassword("123456");
        user.setPhone("012345678");
        user.setAddress("Budapest");
        userService.updateUser(user);

        // Then
        User expectedUser = userService.getUserById(user.getId());

        Assert.assertEquals(expectedUser.getName(), user.getName());
        Assert.assertEquals(expectedUser.getEmail(), user.getEmail());
        Assert.assertEquals(expectedUser.getPassword(), user.getPassword());
        Assert.assertEquals(expectedUser.getPhone(), user.getPhone());
        Assert.assertEquals(expectedUser.getAddress(), user.getAddress());
    }

    private User createTestUser() {
        User user = new User();
        user.setName("Gipsz Jakab");
        user.setPhone("06201234567");
        user.setAddress("Debrecen");
        user.setEmail("gipsz.jakab@example.org");
        user.setPassword("password");

        return user;
    }
}
