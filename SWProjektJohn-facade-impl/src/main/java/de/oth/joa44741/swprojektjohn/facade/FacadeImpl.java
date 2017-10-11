/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.facade;

import de.oth.joa44741.swprojektjohn.bservice.CustomerBusinessService;
import de.oth.joa44741.swprojektjohn.core.dto.CustomerDto;
import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import de.oth.joa44741.swprojektjohn.facade.entitymapper.CustomerEntityToDtoMapper;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andi
 */
@Stateless
@Local(value = Facade.class)
public class FacadeImpl implements Facade {

    @Inject
    private CustomerEntityToDtoMapper customerEntityToDtoMapper;

    @Inject
    private CustomerBusinessService customerBusinessService;

    @Override
    public CustomerDto retrieveCustomerById(Long id) {
        final CustomerEntity customerEntity = customerBusinessService.retrieveCustomerById(id);
        final CustomerDto customerDto = customerEntityToDtoMapper.mapToDto(customerEntity);
        return customerDto;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {
        final CustomerEntity entityToPersist = customerEntityToDtoMapper.mapToEntity(dto);
        final CustomerEntity persistedEntity = customerBusinessService.persistCustomer(entityToPersist);
        final CustomerDto persistedCustomerDto = customerEntityToDtoMapper.mapToDto(persistedEntity);
        return persistedCustomerDto;
    }

}
