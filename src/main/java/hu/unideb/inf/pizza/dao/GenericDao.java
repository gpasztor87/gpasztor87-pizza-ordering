package hu.unideb.inf.pizza.dao;


import hu.unideb.inf.pizza.dao.interfaces.GenericDaoInterface;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class GenericDao<T, PK extends Serializable> implements GenericDaoInterface<T, PK> {

    private EntityManager entityManager;

    GenericDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
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
        return (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
