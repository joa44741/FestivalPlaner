/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web;

import de.oth.joa44741.swprojektjohn.core.dto.CustomerDto;
import de.oth.joa44741.swprojektjohn.facade.Facade;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Path("/{id}")
    public Response retrieveCustomerById(@PathParam("id") Long id) {
        final CustomerDto customerDto = facade.retrieveCustomerById(id);
        /**
         * ResteasyClient client = new ResteasyClientBuilder().build(); String
         * url = "http://second:8080/SimpleRestAppTwo-1.0-SNAPSHOT/second/call";
         * ResteasyWebTarget target = client.target(url); String response =
         * target.request().get().readEntity(String.class);
         */
        return Response.status(200).entity(customerDto.toString()).build();
    }

    @POST
    @Path("/dummy")
    public Response retrieveCustomerById() {
        final CustomerDto customerDto = new CustomerDto("John", "Doe");
        CustomerDto persistedCustomerDto = facade.createCustomer(customerDto);
        return Response.status(201).entity(persistedCustomerDto.toString()).build();
    }
}
