/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.bservice.CustomerBusinessService;
import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("customerBean")
@RequestScoped
public class CustomerBean implements Serializable {

    @Inject
    private CustomerBusinessService customerBusinessService;

    private static final long serialVersionUID = 1L;

    private List<CustomerEntity> customers;

    @PostConstruct
    public void initFields() {
        customers = customerBusinessService.findAllCustomers();
    }

    public List<CustomerEntity> getCustomers() {
        return customers;
    }

}
