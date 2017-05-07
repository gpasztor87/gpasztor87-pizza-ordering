package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.UserDao;
import hu.unideb.inf.pizza.models.User;
import hu.unideb.inf.pizza.services.interfaces.UserServiceInterface;

import static org.apache.commons.codec.digest.DigestUtils.*;

public class UserService implements UserServiceInterface {

    /**
     * DAO objektum az adatbázis kezeléséhez.
     */
    private UserDao userDao;

    /**
     * Az osztály paraméter nélküli konstruktora.
     */
    public UserService() {
        userDao = new UserDao();
    }

    @Override
    public User create(String name, String email, String password, String address, String phone) {
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
    public User update(int id, String name, String password, String address, String phone) {
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
    public boolean validate(String email, String password) {
        User user = userDao.findByEmail(email);
        String encryptedPassword = sha256Hex(password);

        return user != null && user.checkPassword(encryptedPassword);
    }

}
