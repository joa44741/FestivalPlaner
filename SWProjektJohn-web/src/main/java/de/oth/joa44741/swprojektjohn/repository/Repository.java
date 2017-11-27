/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.AbstractLongEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Johnny
 * @param <T> the entity for which the CRUD methods should be called
 */
public interface Repository<T extends AbstractLongEntity> {

    public static final int NO_LIMIT = 0;
    public static final int SINGLE_RESULT = 0;

    T retrieveById(Long id);

    List<T> findAll();

    List<T> query(String namedQueryName, Map<String, Object> parameters, int resultLimit);

    T persistEntity(T entity);

    T update(T entity);

    void remove(Long id);

}
