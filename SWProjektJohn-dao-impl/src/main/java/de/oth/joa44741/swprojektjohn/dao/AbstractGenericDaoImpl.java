/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.dao;

import de.oth.joa44741.swprojektjohn.entity.AbstractLongEntity;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public abstract class AbstractGenericDaoImpl<T extends AbstractLongEntity> implements AbstractGenericDao<T> {

    private final Class<AbstractLongEntity> clazz;

    protected abstract EntityManager getEntityManager();

    public AbstractGenericDaoImpl() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class<AbstractLongEntity>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public T retrieveById(Long id) {
        T entity = (T) getEntityManager().find(this.clazz, id);
        if (entity == null) {
            throw new EntityNotFoundException(
                    "Keine Entity vom Typ " + this.clazz.getSimpleName() + " mit id = " + id + " gefunden!");
        }
        return entity;
    }

    @Override
    public T persistEntity(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

}
