/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.dao;

import de.oth.joa44741.swprojektjohn.entity.AbstractLongEntity;
import java.util.List;

public interface AbstractGenericDao<T extends AbstractLongEntity> {

    T retrieveById(Long id);

    T persistEntity(T entity);

    List<T> findAll();
}
