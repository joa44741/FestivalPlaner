package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.AbstractLongEntity;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andreas John
 * @param <T> the entity for which the CRUD methods should be called
 */
public abstract class RepositoryBaseImpl<T extends AbstractLongEntity> implements Repository<T> {

    @PersistenceContext(unitName = "sw_projekt_john_pu")
    private EntityManager entityManager;

    private final Class<AbstractLongEntity> clazz;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * adapted from:
     * https://stackoverflow.com/questions/3888575/single-dao-generic-crud-methods-jpa-hibernate-spring
     */
    protected RepositoryBaseImpl() {
        final ParameterizedType genericSuperClass = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();
        this.clazz = (Class<AbstractLongEntity>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public T retrieveById(Long id) {
        final T entity = (T) getEntityManager().find(this.clazz, id);
        if (entity == null) {
            throw new EntityNotFoundException(
                    "Keine Entity vom Typ " + this.clazz.getSimpleName() + " mit Id = " + id + " gefunden!");
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

    @Override
    public T update(T entity) {
        final T updatedEntity = getEntityManager().merge(entity);
        return persistEntity(updatedEntity);
    }

    /**
     * adapted from:
     * http://www.adam-bien.com/roller/abien/entry/generic_crud_service_aka_dao
     *
     * @param namedQueryName - the name of the named query
     * @param parameters - parameters for the query
     * @param resultLimit - resultLimit
     * @return matching entities
     */
    @Override
    public List<T> query(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
        final Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        final Query query = this.entityManager.createNamedQuery(namedQueryName);
        if (resultLimit > NO_LIMIT) {
            query.setMaxResults(resultLimit);
        }
        rawParameters.forEach((entry) -> {
            query.setParameter(entry.getKey(), entry.getValue());
        });
        final List<T> resultList = query.getResultList();
        if (resultLimit == SINGLE_RESULT && resultList.isEmpty()) {
            throw new EntityNotFoundException(
                    "Keine Entity vom Typ " + this.clazz.getSimpleName() + " gefunden!");
        } else {
            return resultList;
        }
    }

    @Override
    public void remove(Long id) {
        final T entity = retrieveById(id);
        this.entityManager.remove(entity);
    }

}
