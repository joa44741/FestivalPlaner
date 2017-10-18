/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.boundary;

import de.oth.joa44741.swprojektjohn.entity.AbstractLongEntity;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andi
 * @param <T>
 */
public abstract class AbstractBusinessServiceBase<T extends AbstractLongEntity> {

    @PersistenceContext(unitName = "sw_projekt_john_pu")
    private EntityManager entityManager;

    private final Class<AbstractLongEntity> clazz;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected AbstractBusinessServiceBase() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class<AbstractLongEntity>) genericSuperClass.getActualTypeArguments()[0];
    }

    public T retrieveById(Long id) {
        T entity = (T) getEntityManager().find(this.clazz, id);
        if (entity == null) {
            throw new EntityNotFoundException(
                    "Keine Entity vom Typ " + this.clazz.getSimpleName() + " mit id = " + id + " gefunden!");
        }
        return entity;
    }

    public T persistEntity(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public List<T> findAll() {
        final String statement = "SELECT t FROM " + clazz.getSimpleName() + " t";
        final Query query = getEntityManager().createQuery(statement);
        return query.getResultList();
    }

}
