/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.bservice.CustomerBusinessService;
import de.oth.joa44741.swprojektjohn.core.dto.CustomerDto;
import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import de.oth.joa44741.swprojektjohn.facade.entitymapper.CustomerEntityToDtoMapper;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("customerBean")
@RequestScoped
public class CustomerBean implements Serializable {

    @EJB
    private CustomerBusinessService customerBusinessService;

    @Inject
    private CustomerEntityToDtoMapper customerEntityToDtoMapper;

    private static final long serialVersionUID = 1L;

    private List<CustomerDto> customers;

    @PostConstruct
    public void initFields() {
        final List<CustomerEntity> customerEntities = customerBusinessService.findAllCustomers();
        customers = customerEntities.stream()
                .map(c -> customerEntityToDtoMapper.mapToDto(c))
                .collect(Collectors.toList());
    }

    public List<CustomerDto> getCustomers() {
        return customers;
    }

}
