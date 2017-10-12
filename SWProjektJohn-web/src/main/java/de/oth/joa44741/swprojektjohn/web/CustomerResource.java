/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web;

import de.oth.joa44741.swprojektjohn.core.dto.CustomerDto;
import de.oth.joa44741.swprojektjohn.facade.Facade;
import javax.ejb.EJB;
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
    private Facade facade;

    @GET
    @Path("{id}")
    public Response retrieveCustomerById(@PathParam("id") Long id) {
        final CustomerDto customerDto = facade.retrieveCustomerById(id);
        return Response.status(Response.Status.OK).entity(customerDto.toString()).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVeranstaltung(CustomerDto dto) {
        final CustomerDto persistedCustomerDto = facade.createCustomer(dto);
        return Response.status(Response.Status.CREATED).entity(persistedCustomerDto.toString()).build();
    }
}
