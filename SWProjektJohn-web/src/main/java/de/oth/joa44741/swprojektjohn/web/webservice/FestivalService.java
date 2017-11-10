/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.webservice;

import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.core.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
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
public class FestivalService {

    private static final Logger LOG = Logger.getLogger(FestivalService.class);

    @Inject
    private FestivalBusinessService festivalBusinessService;

    public FestivalEntity retrieveFestivalById(Long id) {
        final FestivalEntity festivalEntity = festivalBusinessService.retrieveFestivalByIdIncludingDetails(id);
        return festivalEntity;
    }

    // TODO: not published
    public FestivalEntity retrieveFestivalByLineupDateId(Long lineupDateId) {
        return festivalBusinessService.retrieveFestivalByLineupDateId(lineupDateId);
    }

    public List<FestivalEntity> findFestivals() {
        final List<FestivalEntity> festivalEntities = festivalBusinessService.findAllFestivals();
        return festivalEntities;
    }

    // TODO: not published
    public List<FestivalEntity> findFestivalsByStatus(StatusEnum... status) {
        final List<FestivalEntity> festivalEntities = festivalBusinessService.findAllFestivalsByStatus(status);
        return festivalEntities;
    }

    public List<FestivalEntity> findAllFestivalsInFutureByStatus(StatusEnum... status) {
        final List<FestivalEntity> festivalEntities = festivalBusinessService.findAllFestivalsInFutureByStatus(status);
        return festivalEntities;
    }

    public Long createFestival(FestivalEntity entityToPersist) {
        LOG.log(Logger.Level.INFO, "createFestival() called");
        final FestivalEntity persistedEntity = festivalBusinessService.persistFestival(entityToPersist);
        return persistedEntity.getId();
    }

    public Long addBuehneToFestival(Long festivalId, BuehneEntity entityToPersist) {
        LOG.log(Logger.Level.INFO, "addBuehneToFestival() called");
        FestivalEntity persistedFestival = festivalBusinessService.addBuehne(festivalId, entityToPersist);
        return persistedFestival.getId();
    }

    // not published
    public void removeFestival(Long id) {
        festivalBusinessService.removeFestival(id);
    }

    public FestivalEntity updateFestival(FestivalEntity festival) {
        return festivalBusinessService.updateFestival(festival);
    }
}
