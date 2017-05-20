package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.interfaces.UserDao;
import hu.unideb.inf.pizza.managers.ConnectionManager;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
        user.setPassword("123456");

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

    @Test
    public void updateUser() throws Exception {
        userService.updateUser(user);
        Mockito.verify(userDao).update(user);
    }

}
