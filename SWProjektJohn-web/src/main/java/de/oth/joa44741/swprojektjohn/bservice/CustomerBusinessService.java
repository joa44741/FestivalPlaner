/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import java.util.List;

/**
 *
 * @author Andi
 */
public interface CustomerBusinessService {

    CustomerEntity retrieveCustomerById(Long id);

    CustomerEntity persistCustomer(CustomerEntity entity);

    List<CustomerEntity> findAllCustomers();
}
