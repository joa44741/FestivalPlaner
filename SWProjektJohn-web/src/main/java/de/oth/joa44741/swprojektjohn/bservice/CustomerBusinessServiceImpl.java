/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.dao.CustomerDao;
import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andi
 */
@Stateless
@Local
public class CustomerBusinessServiceImpl implements CustomerBusinessService {

    @Inject
    private CustomerDao customerDao;

    @Override
    public CustomerEntity retrieveCustomerById(Long id) {
        return customerDao.retrieveById(id);
    }

    @Override
    public CustomerEntity persistCustomer(CustomerEntity entity) {
        return customerDao.persistEntity(entity);
    }

    @Override
    public List<CustomerEntity> findAllCustomers() {
        return customerDao.findAll();
    }
}
