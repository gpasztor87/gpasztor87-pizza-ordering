package hu.unideb.inf.pizza.services;

import hu.unideb.inf.pizza.dao.UserDao;
import hu.unideb.inf.pizza.dao.interfaces.UserDaoInterface;
import hu.unideb.inf.pizza.managers.ConnectionManager;
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
     * A {@link ConnectionManager} interfész egy implementációjának példánya.
     */
    private ConnectionManager connectionManager;

    /**
     * Az osztály konstruktora inicializálja a userDao objektumot.
     *
     * @param connectionManager A connectionManager
     */
    public UserService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        userDao = new UserDao(connectionManager.getEntityManager());
    }

    @Override
    public User createUser(User user) {
        String encryptedPassword = sha256Hex(user.getPassword());
        user.setPassword(encryptedPassword);

        try {
            connectionManager.beginTransaction();
            userDao.create(user);
            connectionManager.commit();
        } catch (Exception e) {
            connectionManager.rollback();
        }

        return user;
    }

    @Override
    public User updateUser(User user) {
        String encryptedPassword = sha256Hex(user.getPassword());
        user.setPassword(encryptedPassword);

        try {
            connectionManager.beginTransaction();
            userDao.update(user);
            connectionManager.commit();
        } catch (Exception e) {
            connectionManager.rollback();
        }

        return user;
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id);
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
