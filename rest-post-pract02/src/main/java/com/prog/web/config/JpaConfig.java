package com.prog.web.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class JpaConfig {

    //EntityManagerFactory
    @ApplicationScoped
    @Produces
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("dbposts1");
    }
    //EntityManager
    @Produces
    @RequestScoped
    public EntityManager entityManager(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }

    //Close @Disposes
    public void close(@Disposes EntityManager em){
        if(em != null && em.isOpen()) {
            em.close();
        }
    }
}
