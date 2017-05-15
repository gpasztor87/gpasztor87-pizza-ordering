package hu.unideb.inf.pizza.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Generikus DAO interfész, ebből származik az összes interfész.
 *
 * @param <T>
 * @param <PK>
 */
public interface GenericDaoInterface<T, PK extends Serializable> {

    /**
     * Létrehozza a a newInstance objektumot a perzisztens tárolóban.
     *
     * @param entity A léterhozandó objektum
     */
    void create(T entity);

    /**
     * Kiolvassa a perzisztens tárolóból a keresett objektumot.
     *
     * @param id A keresett objektum egyedi azonosítója
     * @return A keresett objektum
     */
    T findById(PK id);

    /**
     * Módosítja a perzisztens objektumot.
     *
     * @param entity A módosítandó objektum
     */
    void update(T entity);

    /**
     * Törli az objektumot a perzisztens tárolóból.
     *
     * @param entity A törlendő objektum
     */
    void delete(T entity);

    /**
     * Kiolvassa a perzisztens tárolóból az összes objektumot.
     *
     * @return Az összes objektum
     */
    List<T> findAll();

}
