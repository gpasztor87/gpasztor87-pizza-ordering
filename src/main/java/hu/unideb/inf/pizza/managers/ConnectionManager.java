package hu.unideb.inf.pizza.managers;

import javax.persistence.EntityManager;

/**
 * A kapcsolatokat biztosító manager osztály interfésze.
 */
public interface ConnectionManager {

    /**
     * Elindít egy tranzakciós folyamatot a perzisztens tórolón.
     */
    void beginTransaction();

    /**
     * Végrehajtja az előzőleg kiadott módosításokat a perzisztens adattárolón.
     */
    void commit();

    /**
     * Lezárja a kapcsolatot a perzisztens adattárolóval.
     */
    void close();

    /**
     * Visszavonja az utolsó módosítást a perzisztens tárolón.
     */
    void rollback();

    /**
     * Visszaadja az EntityManager aktuális példányát.
     *
     * @return Az EntityManager aktuális példánya
     */
    EntityManager getEntityManager();

}
