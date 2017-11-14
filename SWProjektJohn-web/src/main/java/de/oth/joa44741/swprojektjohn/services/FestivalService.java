/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.core.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andi
 */
public interface FestivalService {

    FestivalEntity retrieveFestivalById(Long id);

    FestivalEntity retrieveFestivalByIdIncludingDetails(Long festivalId);

    Optional<FestivalEntity> findFestivalByName(String name);

    FestivalEntity retrieveFestivalByLineupDateId(Long lineupDateId);

    List<FestivalEntity> findFestivals();

    // TODO: not published
    List<FestivalEntity> findFestivalsByStatus(StatusEnum... status);

    List<FestivalEntity> findAllFestivalsInFutureByStatus(StatusEnum... status);

    FestivalEntity createFestival(FestivalEntity entityToPersist);

    // not published
    void removeFestival(Long id);

    FestivalEntity updateFestival(FestivalEntity festival);

    FestivalEntity addTicketArt(Long festivalId, TicketArtEntity ticketArt);

    FestivalEntity addCampingVariante(Long festivalId, CampingVarianteEntity campingVariante);

    FestivalEntity addBuehne(Long festivalId, BuehneEntity buehne);

    FestivalEntity removeTicketArt(Long festivalId, Long ticketId);

    FestivalEntity removeCampingVariante(Long festivalId, Long campingId);

    FestivalEntity removeBuehne(Long festivalId, Long buehnenId);

    // buehnenId instead of festivalid required
    FestivalEntity addLineupDate(Long buehnenId, LineupDateEntity lineupDate);

    FestivalEntity removeLineupDate(Long buehnenId, Long lineupDateId);
}
