/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.AbstractLongEntity;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andi
 * @param <T>
 */
public class BusinessServiceBaseImpl<T extends AbstractLongEntity> implements BusinessServiceBase<T> {

    // TODO: kommentar schreiben, da Code aus stackoverflow verwendet
    //TODO: Exceptions definieren!
    @PersistenceContext(unitName = "sw_projekt_john_pu")
    private EntityManager entityManager;

    private final Class<AbstractLongEntity> clazz;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected BusinessServiceBaseImpl() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();
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

    @Override
    public List<T> findAll() {
        final String statement = "SELECT t FROM " + clazz.getSimpleName() + " t";
        final Query query = getEntityManager().createQuery(statement);
        return query.getResultList();
    }

    protected Optional<T> getOptionalSingleResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        final List<T> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }

}
