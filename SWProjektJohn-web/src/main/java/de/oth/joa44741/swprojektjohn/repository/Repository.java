package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.AbstractLongEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andreas John
 * @param <T> the entity for which the CRUD methods should be called
 */
public interface Repository<T extends AbstractLongEntity> {

    public static final int NO_LIMIT = 0;
    public static final int SINGLE_RESULT = 1;

    T retrieveById(Long id);

    List<T> findAll();

    List<T> query(String namedQueryName, Map<String, Object> parameters, int resultLimit);

    T persistEntity(T entity);

    T update(T entity);

    void remove(Long id);

}
