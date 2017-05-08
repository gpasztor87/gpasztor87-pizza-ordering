package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.UserDao;
import hu.unideb.inf.pizza.dao.interfaces.UserDaoInterface;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.UserServiceInterface;

import static org.apache.commons.codec.digest.DigestUtils.*;

/**
 * A UserService interfészt megvalósító osztály.
 */
public class UserService implements UserServiceInterface {

    /**
     * A {@link UserDaoInterface} interfész egy implementációjának példánya.
     */
    private UserDaoInterface userDao;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public UserService() {
        userDao = new UserDao();
    }

    @Override
    public User createUser(String name, String email, String password, String address, String phone) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);

        String encryptedPassword = sha256Hex(password);
        user.setPassword(encryptedPassword);

        userDao.create(user);

        return user;
    }

    @Override
    public User updateUser(int id, String name, String password, String address, String phone) {
        User user = userDao.findById(id);

        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);

        String encryptedPassword = sha256Hex(password);
        user.setPassword(encryptedPassword);

        userDao.update(user);

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean validateUser(String email, String password) {
        User user = userDao.findByEmail(email);
        String encryptedPassword = sha256Hex(password);

        return user != null && user.checkPassword(encryptedPassword);
    }

}
