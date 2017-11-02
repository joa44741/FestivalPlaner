/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@RequestScoped
@Transactional
public class FestivalBusinessServiceImpl extends BusinessServiceBaseImpl<FestivalEntity> implements FestivalBusinessService {

    private static final Logger LOG = Logger.getLogger(FestivalBusinessServiceImpl.class);

    @Override
    public FestivalEntity retrieveFestivalById(Long id) {
        return retrieveById(id);
    }

    @Override
    public FestivalEntity retrieveFestivalByIdIncludingDetails(Long id) {
        final String statement = "SELECT t FROM FestivalEntity t "
                + "JOIN FETCH t.location l "
                + "LEFT JOIN FETCH t.zusatzeigenschaften z "
                + "LEFT JOIN FETCH t.ticketArten ta "
                + "LEFT JOIN FETCH t.buehnen "
                + "LEFT JOIN FETCH t.campingVarianten c "
                + "WHERE t.id = :id";
        final TypedQuery<FestivalEntity> query = getEntityManager().createQuery(statement, FestivalEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public FestivalEntity persistFestival(FestivalEntity entity) {
        if (entity.getLocation().getId() != null) {
            final LocationEntity mergedLocationEntity = getEntityManager().merge(entity.getLocation());
            entity.setLocation(mergedLocationEntity);
        }
        return persistEntity(entity);
    }

    @Override
    public List<FestivalEntity> findAllFestivals() {
        return findAll();
    }

    @Override
    public FestivalEntity addTicketArt(Long festivalId, TicketArtEntity ticketArt) {
        final FestivalEntity festival = retrieveById(festivalId);
        festival.addTicketArt(ticketArt);
        return festival;
    }

    @Override
    public FestivalEntity removeTicketArt(Long festivalId, Long ticketId) {
        final FestivalEntity festival = retrieveById(festivalId);
        Optional<TicketArtEntity> optionalTicket = festival.getTicketArten().stream().filter(ticket -> ticket.getId() == ticketId).findFirst();
        festival.removeTicketArt(optionalTicket.get());
        return festival;
    }

    @Override
    public FestivalEntity addCampingVariante(Long festivalId, CampingVarianteEntity campingVariante) {
        final FestivalEntity festival = retrieveById(festivalId);
        festival.addCampingVariante(campingVariante);
        return festival;
    }

    @Override
    public FestivalEntity removeCampingVariante(Long festivalId, Long campingId) {
        final FestivalEntity festival = retrieveById(festivalId);
        Optional<CampingVarianteEntity> optionalTicket = festival.getCampingVarianten().stream().filter(ticket -> ticket.getId() == campingId).findFirst();
        festival.removeCampingVariante(optionalTicket.get());
        return festival;
    }

    @Override
    public FestivalEntity addLineupDate(Long buehnenId, LineupDateEntity lineupDate) {
        final BuehneEntity buehne = getEntityManager().find(BuehneEntity.class, buehnenId);
        if (lineupDate.getBand().getId() != null) {
            final BandEntity mergedBandEntity = getEntityManager().merge(lineupDate.getBand());
            lineupDate.setBand(mergedBandEntity);
        }
        buehne.addLineupDate(lineupDate);
        return buehne.getFestival();
    }

    @Override
    public FestivalEntity removeLineupDate(Long buehnenId, Long lineupDateId) {
        final BuehneEntity buehne = getEntityManager().find(BuehneEntity.class, buehnenId);
        Optional<LineupDateEntity> optLineupDate = buehne.getLineupDates().stream().filter(lineup -> lineup.getId() == lineupDateId).findFirst();
        buehne.removeLineupDate(optLineupDate.get());
        return buehne.getFestival();
    }

    @Override
    public FestivalEntity addBuehne(Long festivalId, BuehneEntity buehne) {
        final FestivalEntity festival = retrieveById(festivalId);
        // bidirectional
        buehne.setFestival(festival);
        festival.addBuehne(buehne);
        return festival;
    }

    @Override
    public FestivalEntity removeBuehne(Long festivalId, Long buehnenId) {
        final FestivalEntity festival = retrieveById(festivalId);
        Optional<BuehneEntity> optionalBuehne = festival.getBuehnen().stream().filter(buehne -> buehne.getId() == buehnenId).findFirst();
        festival.removeBuehne(optionalBuehne.get());
        return festival;
    }

}
