/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

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
public interface FestivalBusinessService {

    FestivalEntity retrieveFestivalById(Long id);

    FestivalEntity retrieveFestivalByIdIncludingDetails(Long id);

    FestivalEntity persistFestival(FestivalEntity entity);

    List<FestivalEntity> findAllFestivals();

    List<FestivalEntity> findAllFestivalsWithStatusFreigegeben();

    FestivalEntity addTicketArt(Long festivalId, TicketArtEntity ticketArt);

    FestivalEntity addCampingVariante(Long festivalId, CampingVarianteEntity campingVariante);

    FestivalEntity addBuehne(Long festivalId, BuehneEntity buehne);

    FestivalEntity removeTicketArt(Long festivalId, Long ticketId);

    FestivalEntity removeCampingVariante(Long festivalId, Long campingId);

    FestivalEntity removeBuehne(Long festivalId, Long buehnenId);

    FestivalEntity updateFestival(FestivalEntity entity);

    // buehnenId instead of festivalid required
    FestivalEntity addLineupDate(Long buehnenId, LineupDateEntity lineupDate);

    FestivalEntity removeLineupDate(Long buehnenId, Long lineupDateId);

    Optional<FestivalEntity> findFestivalByName(String name);

    FestivalEntity retrieveFestivalByLineupDateId(Long lineupDateId);

}
