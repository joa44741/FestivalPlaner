/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.AbstractLongEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Andi
 */
public class BusinessServiceImplTestBase {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("sw_projekt_john_pu-test");
        entityManager = entityManagerFactory.createEntityManager();
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected void begin() {
        entityManager.getTransaction().begin();
    }

    protected void persist(Object entity) {
        entityManager.persist(entity);
    }

    protected void commit() {
        entityManager.getTransaction().commit();
    }

    protected <T extends AbstractLongEntity> T getSingleResult(String sql, Class<T> clazz) {
        TypedQuery<T> query = entityManager.createQuery(sql, clazz);
        return (T) query.getSingleResult();
    }

    protected <T extends AbstractLongEntity> List<T> getResultList(String sql, Class<T> clazz) {
        TypedQuery<T> query = entityManager.createQuery(sql, clazz);
        return (List<T>) query.getResultList();
    }

    @After
    public void tearDown() {
        entityManager.clear();
        entityManager.close();
        entityManagerFactory.close();
    }

}
