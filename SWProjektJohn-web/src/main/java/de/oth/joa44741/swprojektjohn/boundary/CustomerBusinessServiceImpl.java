/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.boundary;

import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Andi
 */
@Stateless
public class CustomerBusinessServiceImpl extends AbstractBusinessServiceBase<CustomerEntity> implements CustomerBusinessService {

    @Override
    public CustomerEntity retrieveCustomerById(Long id) {
        return retrieveById(id);
    }

    @Override
    public CustomerEntity persistCustomer(CustomerEntity entity) {
        return persistEntity(entity);
    }

    @Override
    public List<CustomerEntity> findAllCustomers() {
        return findAll();
    }
}
