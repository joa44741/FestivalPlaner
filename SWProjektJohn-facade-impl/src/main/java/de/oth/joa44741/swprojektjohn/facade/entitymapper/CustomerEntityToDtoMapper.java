/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.facade.entitymapper;

import de.oth.joa44741.swprojektjohn.core.dto.CustomerDto;
import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Andi
 */
@ApplicationScoped
public class CustomerEntityToDtoMapper implements EntityToDtoMapper<CustomerDto, CustomerEntity> {

    @Override
    public CustomerDto mapToDto(CustomerEntity entity) {
        final CustomerDto customerDto = new CustomerDto(entity.getId(), entity.getVorname(), entity.getNachname());
        return customerDto;
    }

    @Override
    public CustomerEntity mapToEntity(CustomerDto dto) {
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setVorname(dto.getVorname());
        customerEntity.setNachname(dto.getNachname());
        return customerEntity;
    }

}
