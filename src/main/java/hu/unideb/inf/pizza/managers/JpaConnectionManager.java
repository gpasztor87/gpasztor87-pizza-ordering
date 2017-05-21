package hu.unideb.inf.pizza.managers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A JPA perzisztens réteggel kapcsolatot biztosító osztály.
 */
public class JpaConnectionManager implements ConnectionManager {

    /**
     * Az EntityManager egy példánya.
     */
    private EntityManager entityManager;

    /**
     * Az EntityManagerFactory egy példánya.
     */
    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("production");
    }

    /**
     * Az osztály konstruktora inicializálja az EntityManagert.
     */
    public JpaConnectionManager() {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    @Override
    public void commit() {
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() {
        entityManager.close();
        factory.close();
    }

    @Override
    public void rollback() {
        entityManager.getTransaction().rollback();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
