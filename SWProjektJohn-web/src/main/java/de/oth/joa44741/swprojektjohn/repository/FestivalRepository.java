/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.core.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andi
 */
public interface FestivalRepository {

    // TODO: try to minimize methods by only using update instead of addTicketArt and so on...
    FestivalEntity retrieveFestivalById(Long id);

    FestivalEntity retrieveFestivalByIdIncludingDetails(Long id);

    FestivalEntity persistFestival(FestivalEntity entity);

    List<FestivalEntity> findAllFestivals();

    List<FestivalEntity> findAllFestivalsByStatus(StatusEnum... status);

    List<FestivalEntity> findAllFestivalsInFutureByStatus(StatusEnum... status);

    FestivalEntity updateFestival(FestivalEntity entity);

    Optional<FestivalEntity> findFestivalByName(String name);

    FestivalEntity retrieveFestivalByLineupDateId(Long lineupDateId);

    void removeFestival(FestivalEntity festival);

    void detachFestival(FestivalEntity festival);

}
