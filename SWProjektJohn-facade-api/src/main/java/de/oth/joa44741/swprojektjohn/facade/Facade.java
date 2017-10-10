/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.facade;

import de.oth.joa44741.swprojektjohn.core.dto.CustomerDto;

/**
 *
 * @author Andi
 */
public interface Facade {

    CustomerDto retrieveCustomerById(Long id);

    CustomerDto createCustomer(CustomerDto dto);
}