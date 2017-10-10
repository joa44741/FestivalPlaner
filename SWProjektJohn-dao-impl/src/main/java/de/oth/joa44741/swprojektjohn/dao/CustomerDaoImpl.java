/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.dao;

import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andi
 */
@Stateless
public class CustomerDaoImpl extends AbstractGenericDaoImpl<CustomerEntity> implements CustomerDao {

    @PersistenceContext(unitName = "sw_projekt_john_pu")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
