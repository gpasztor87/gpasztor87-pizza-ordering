package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.UserDao;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.UserServiceInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserService implements UserServiceInterface {

    private UserDao userDao;

    public UserService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("production");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userDao = new UserDao(entityManager);
    }

    public boolean validate(String email, String password) {
        User user = userDao.findByEmail(email);
        return user != null && user.checkPassword(password);
    }

}
