package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.interfaces.UserDao;
import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import javax.validation.ValidationException;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class UserServiceTest {

    private UserDao userDao;

    private ConnectionManager connectionManager;

    private UserService userService;

    private User user;

    @Before
    public void setUp() throws Exception {
        userDao = Mockito.mock(UserDao.class);
        connectionManager = Mockito.mock(ConnectionManager.class);

        user = new User("Teszt Elek", "teszt@elek.org", "Debrecen", "06201234567");
        user.setId(1);
        user.setPassword(sha256Hex("123456"));

        Mockito.when(userDao.findByEmail("teszt@elek.org")).thenReturn(user);
        Mockito.when(userDao.findById(1)).thenReturn(user);

        userService = new UserServiceImpl(connectionManager, userDao);
    }

    @Test
    public void testMockCreation() throws Exception {
        Assert.assertNotNull(userDao);
        Assert.assertNotNull(userService);
    }

    @Test
    public void getUserByEmail() throws Exception {
        Assert.assertEquals(1, userService.getUserByEmail("teszt@elek.org").getId());
    }

    @Test
    public void getUserById() throws Exception {
        Assert.assertEquals(1, userService.getUserById(1).getId());
    }

    @Test
    public void createUser() throws Exception {
        userService.createUser(user);
        Mockito.verify(userDao).create(user);
    }

    @Test(expected = ValidationException.class)
    public void createUserWithInvalidEmail() throws Exception {
        userService.createUser(new User("Teszt Elek", "asdf1234", "Debrecen", "06201234567"));
    }

    @Test(expected = ValidationException.class)
    public void createUserWithEmptyAddress() throws Exception {
        userService.createUser(new User("Teszt Elek", "teszt@elek.com", "", "06201234567"));
    }

    @Test(expected = ValidationException.class)
    public void createUserWithEmptyPhone() throws Exception {
        userService.createUser(new User("Teszt Elek", "teszt@elek.com", "Debrecen", ""));
    }

    @Test
    public void updateUser() throws Exception {
        userService.updateUser(user);
        Mockito.verify(userDao).update(user);
    }

    @Test(expected = ValidationException.class)
    public void updateUserWithInvalidEmail() throws Exception {
        userService.updateUser(new User("Teszt Elek", "asdf1234", "Debrecen", "06201234567"));
    }

    @Test(expected = ValidationException.class)
    public void updateUserWithEmptyAddress() throws Exception {
        userService.updateUser(new User("Teszt Elek", "teszt@elek.com", "", "06201234567"));
    }

    @Test(expected = ValidationException.class)
    public void updateUserWithEmptyPhone() throws Exception {
        userService.updateUser(new User("Teszt Elek", "teszt@elek.com", "Debrecen", ""));
    }

    @Test
    public void validateUserSuccessful() throws Exception {
        Assert.assertEquals(true, userService.validateUser("teszt@elek.org", "123456"));
    }

    @Test
    public void validateUserWithWrongEmail() throws Exception {
        Assert.assertEquals(false, userService.validateUser("teszt@elek.com", "123456"));
    }

    @Test
    public void validateUserWithWrongPassword() throws Exception {
        Assert.assertEquals(false, userService.validateUser("teszt@elek.org", "12345"));
    }

}
