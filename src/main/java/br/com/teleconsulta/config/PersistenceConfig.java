package br.com.teleconsulta.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class PersistenceConfig {

    private EntityManagerFactory entityManagerPostgres;
    private EntityManagerFactory entityManagerSqlite;

    private EntityManagerFactory getPostgresFactory() {
        if (entityManagerPostgres == null) {
            entityManagerPostgres = Persistence.createEntityManagerFactory("teleconsultaPU_postgres");
        }
        return entityManagerPostgres;
    }

    private EntityManagerFactory getSqliteFactory() {
        if (entityManagerSqlite == null) {
            entityManagerSqlite = Persistence.createEntityManagerFactory("teleconsultaPU_sqlite");
        }
        return entityManagerSqlite;
    }

    @Produces
    @Named("emPostgres")
    @RequestScoped
    public EntityManager getEntityManagerPostgres() {
        return getPostgresFactory().createEntityManager();
    }

    @Produces
    @Named("emSqlite")
    @RequestScoped
    public EntityManager getEntityManagerSqlite() {
        return getSqliteFactory().createEntityManager();
    }

    public void closeEntityManagerPostgres(@Disposes @Named("emPostgres") EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }

    public void closeEntityManagerSqlite(@Disposes @Named("emSqlite") EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}
