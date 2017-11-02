/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import java.util.List;

public interface BusinessServiceBase<T> {

    T retrieveById(Long id);

    List<T> findAll();

    T persistEntity(T entity);

}
