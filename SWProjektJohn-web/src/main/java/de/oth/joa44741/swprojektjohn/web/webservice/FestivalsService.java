/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.webservice;

import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@RequestScoped
@WebService
public class FestivalsService {

    private static final Logger LOG = Logger.getLogger(FestivalsService.class);

    @Inject
    private FestivalBusinessService festivalBusinessService;

    public FestivalEntity retrieveFestivalById(Long id) {
        final FestivalEntity festivalEntity = festivalBusinessService.retrieveFestivalByIdIncludingDetails(id);
        return festivalEntity;
    }

    public List<FestivalEntity> retrieveFestivals() {
        final List<FestivalEntity> festivalEntities = festivalBusinessService.findAllFestivals();
        return festivalEntities;
    }

    public Long createFestival(FestivalEntity entityToPersist) {
        LOG.log(Logger.Level.INFO, "createFestival() called");
        final FestivalEntity persistedEntity = festivalBusinessService.persistFestival(entityToPersist);
        return persistedEntity.getId();
    }
}
