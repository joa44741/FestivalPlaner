/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.webservices;

import de.oth.joa44741.swprojektjohn.bservice.CustomerBusinessService;
import de.oth.joa44741.swprojektjohn.core.dto.CustomerDto;
import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import de.oth.joa44741.swprojektjohn.facade.entitymapper.CustomerEntityToDtoMapper;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andi
 */
@Path("/customer")
public class CustomerResource {

    @EJB
    private CustomerBusinessService customerBusinessService;

    @Inject
    private CustomerEntityToDtoMapper customerEntityToDtoMapper;

    @GET
    @Path("{id}")
    public Response retrieveCustomerById(@PathParam("id") Long id) {
        final CustomerEntity customerEntity = customerBusinessService.retrieveCustomerById(id);
        final CustomerDto customerDto = customerEntityToDtoMapper.mapToDto(customerEntity);
        return Response.status(Response.Status.OK).entity(customerDto.toString()).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVeranstaltung(CustomerDto dto) {
        final CustomerEntity entityToPersist = customerEntityToDtoMapper.mapToEntity(dto);
        final CustomerEntity persistedEntity = customerBusinessService.persistCustomer(entityToPersist);
        final CustomerDto persistedDto = customerEntityToDtoMapper.mapToDto(persistedEntity);
        return Response.status(Response.Status.CREATED).entity(persistedDto.toString()).build();
    }
}
