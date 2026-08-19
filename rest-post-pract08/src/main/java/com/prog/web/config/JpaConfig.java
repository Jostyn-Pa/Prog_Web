package com.prog.web.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class JpaConfig {
    //EntityManagerFactory
    @Produces
    @ApplicationScoped
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("dbposts1");
    }
    //EntityManger
    @Produces
    @RequestScoped
    public EntityManager entityManager(EntityManagerFactory emf) {
       return emf.createEntityManager();
    }

    //close con @Disposes
    public void close(@Disposes EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

}
