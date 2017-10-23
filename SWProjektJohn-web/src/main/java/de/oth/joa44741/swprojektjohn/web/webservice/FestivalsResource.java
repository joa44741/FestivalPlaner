/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.webservice;

import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
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
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@Stateless
@Path("/festivals")
public class FestivalsResource {

    private static final Logger LOG = Logger.getLogger(FestivalsResource.class);

    @Inject
    private FestivalBusinessService festivalBusinessService;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response retrieveFestivalById(@PathParam("id") Long id) {
        try {
            final FestivalEntity festivalEntity = festivalBusinessService.retrieveFestivalById(id);
            // avoid lazy loading exception --> traverse buehnen Collection
            festivalEntity.getBuehnen().forEach(b -> LOG.debug("Buehne mit ID " + b.getId() + " geladen"));
            return Response.ok(festivalEntity).build();
        } catch (RuntimeException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity("Kein Festival mit Id: " + id + " gefunden.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFestival(FestivalEntity entityToPersist, @Context UriInfo info) {
        LOG.log(Logger.Level.INFO, "createFestival() called");
        final FestivalEntity persistedEntity = festivalBusinessService.persistFestival(entityToPersist);
        final URI uri = info.getAbsolutePathBuilder().path(String.valueOf(persistedEntity.getId())).build();
        return Response.created(uri).build();
    }
}
