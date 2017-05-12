package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.UserDao;
import hu.unideb.inf.pizza.dao.interfaces.UserDaoInterface;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.UserServiceInterface;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserServiceTest {

    private EntityManagerFactory entityManagerFactory;

    @Mock
    private UserDaoInterface userDao;

    @Mock
    private UserServiceInterface userService;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
        userDao = new UserDao();

        userService = new UserService();
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void testCreateUser() {
        // Given
        User user = this.createTestUser();

        // When
        User savedUser = userService.createUser(user);

        // Then
        User expectedUser = userService.getUserById(savedUser.getId());

        Assert.assertEquals(expectedUser.getEmail(), user.getEmail());
    }

    @Test
    public void testGetUserByEmail() {
        // Given
        User user = this.createTestUser();

        // When
        userService.createUser(user);

        // Then
        Assert.assertEquals(userService.getUserByEmail(user.getEmail()).getId(), user.getId());
    }

    @Test
    public void testUpdateUser() {
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
