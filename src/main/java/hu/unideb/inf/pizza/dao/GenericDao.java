package hu.unideb.inf.pizza.dao;


import hu.unideb.inf.pizza.dao.interfaces.GenericDaoInterface;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDao<T, PK extends Serializable> implements GenericDaoInterface<T, PK> {

    private EntityManager entityManager;

    GenericDao() {
        entityManager = Persistence.createEntityManagerFactory("production").createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T findById(PK id) {
        return (T) entityManager.find(getTypeClass(), id);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + getTypeClass().getName()).getResultList();
    }

    private Class<?> getTypeClass() {
        Class<?> classType = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return classType;
    }
}
