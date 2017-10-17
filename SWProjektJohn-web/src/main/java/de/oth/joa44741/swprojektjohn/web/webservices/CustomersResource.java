/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.webservices;

import de.oth.joa44741.swprojektjohn.bservice.CustomerBusinessService;
import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Andi
 */
@Stateless
@Path("/customers")
public class CustomersResource {

    @Inject
    private CustomerBusinessService customerBusinessService;

    @GET
    @Path("{id}")
    @Produces({"application/json", "application/xml"})
    public CustomerEntity retrieveCustomerById(@PathParam("id") Long id) {
        final CustomerEntity customerEntity = customerBusinessService.retrieveCustomerById(id);
        return customerEntity;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerEntity entityToPersist, @Context UriInfo info) {
        final CustomerEntity persistedEntity = customerBusinessService.persistCustomer(entityToPersist);
        URI uri = info.getAbsolutePathBuilder().path(String.valueOf(persistedEntity.getId())).build();
        return Response.created(uri).build();
    }
}
