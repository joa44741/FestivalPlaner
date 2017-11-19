/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.core.FestivalWithDetailsDto;
import de.oth.joa44741.swprojektjohn.core.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import de.oth.joa44741.swprojektjohn.repository.BandRepository;
import de.oth.joa44741.swprojektjohn.repository.BuehneRepository;
import de.oth.joa44741.swprojektjohn.repository.FestivalRepository;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@RequestScoped
@WebService
public class FestivalServiceImpl implements FestivalService {

    private static final Logger LOG = Logger.getLogger(FestivalServiceImpl.class);

    @Inject
    private FestivalRepository festivalRepository;

    @Inject
    private BuehneRepository buehneRepository;

    @Inject
    private BandRepository bandRepository;

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity retrieveFestivalById(Long id) {
        final FestivalEntity festivalEntity = festivalRepository.retrieveFestivalById(id);
        return festivalEntity;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity retrieveFestivalByIdIncludingDetails(Long festivalId) {
        return festivalRepository.retrieveFestivalByIdIncludingDetails(festivalId);
    }

    @Override
    public FestivalWithDetailsDto retrieveFestivalDtoByIdIncludingDetails(Long festivalId) {
        FestivalEntity entity = festivalRepository.retrieveFestivalByIdIncludingDetails(festivalId);
        FestivalWithDetailsDto dto = new FestivalWithDetailsDto(entity);
        return dto;
    }

    @WebMethod(exclude = true)
    @Override
    public Optional<FestivalEntity> findFestivalByName(String name) {
        return festivalRepository.findFestivalByName(name);
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity retrieveFestivalByLineupDateId(Long lineupDateId) {
        return festivalRepository.retrieveFestivalByLineupDateId(lineupDateId);
    }

    @WebMethod(exclude = true)
    @Override
    public List<FestivalEntity> findFestivals() {
        return festivalRepository.findAllFestivals();
    }

    @Override
    @Transactional
    public List<FestivalEntity> findFreigegebeneFestivals() {
        final List<FestivalEntity> festivals = findFestivalsByStatus(StatusEnum.FREIGEGEBEN);
//        festivals.forEach(f -> handleLazyLoadingFields(f));
        return festivals;
    }

    private void handleLazyLoadingFields(FestivalEntity festival) {
        festivalRepository.detachFestival(festival);
        festival.clearBuehnen();
        festival.clearCampingVarianten();
        festival.clearTicketArten();
    }

    @WebMethod(exclude = true)
    @Override
    public List<FestivalEntity> findFestivalsByStatus(StatusEnum... status) {
        final List<FestivalEntity> festivalEntities = festivalRepository.findAllFestivalsByStatus(status);
        return festivalEntities;
    }

    @WebMethod(exclude = true)
    @Override
    public List<FestivalEntity> findAllFestivalsInFutureByStatus(StatusEnum... status) {
        final List<FestivalEntity> festivalEntities = festivalRepository.findAllFestivalsInFutureByStatus(status);
        return festivalEntities;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity createFestival(FestivalEntity entityToPersist) {
        LOG.log(Logger.Level.INFO, "createFestival() called");
        final FestivalEntity persistedEntity = festivalRepository.persistFestival(entityToPersist);
        return persistedEntity;
    }

    @WebMethod(exclude = true)
    @Override
    public void removeFestival(Long id) {
        final FestivalEntity festivalToDelete = festivalRepository.retrieveFestivalById(id);
        festivalRepository.removeFestival(festivalToDelete);
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity updateFestival(FestivalEntity festival) {
        return festivalRepository.updateFestival(festival);
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity addTicketArt(Long festivalId, TicketArtEntity ticketArt) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        festival.addTicketArt(ticketArt);
        festivalRepository.updateFestival(festival);
        return festival;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity removeTicketArt(Long festivalId, Long ticketId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        final Optional<TicketArtEntity> optionalTicket = festival.getTicketArten().stream().filter(ticket -> ticket.getId().equals(ticketId)).findFirst();
        festival.removeTicketArt(optionalTicket.get());
        festivalRepository.updateFestival(festival);
        return festival;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity addCampingVariante(Long festivalId, CampingVarianteEntity campingVariante) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        festival.addCampingVariante(campingVariante);
        festivalRepository.updateFestival(festival);
        return festival;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity removeCampingVariante(Long festivalId, Long campingId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        final Optional<CampingVarianteEntity> optionalTicket = festival.getCampingVarianten().stream().filter(camping -> camping.getId().equals(campingId)).findFirst();
        festival.removeCampingVariante(optionalTicket.get());
        festivalRepository.updateFestival(festival);
        return festival;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity addLineupDateToBuehne(Long buehnenId, LineupDateEntity lineupDate) {
        BuehneEntity buehne = buehneRepository.retrieveBuehneById(buehnenId);
        if (lineupDate.getBand().getId() != null) {
            final BandEntity mergedBandEntity = bandRepository.retrieveBandById(lineupDate.getBand().getId());
            lineupDate.setBand(mergedBandEntity);
        }
        buehne.addLineupDate(lineupDate);
        buehne = buehneRepository.updateBuehne(buehne);
        return buehne.getFestival();
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity removeLineupDateFromBuehne(Long buehnenId, Long lineupDateId) {
        final BuehneEntity buehne = buehneRepository.retrieveBuehneById(buehnenId);
        final Optional<LineupDateEntity> optLineupDate = buehne.getLineupDates().stream().filter(lineup -> lineup.getId().equals(lineupDateId)).findFirst();
        buehne.removeLineupDate(optLineupDate.get());
        final BuehneEntity updatedBuehne = buehneRepository.updateBuehne(buehne);
        return updatedBuehne.getFestival();
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity addBuehne(Long festivalId, BuehneEntity buehne) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        // bidirectional
        buehne.setFestival(festival);
        festival.addBuehne(buehne);
        final FestivalEntity updatedFestival = festivalRepository.updateFestival(festival);
        return updatedFestival;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity removeBuehne(Long festivalId, Long buehnenId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        Optional<BuehneEntity> optionalBuehne = festival.getBuehnen().stream().filter(buehne -> buehne.getId().equals(buehnenId)).findFirst();
        festival.removeBuehne(optionalBuehne.get());
        final FestivalEntity updatedFestival = festivalRepository.updateFestival(festival);
        return updatedFestival;
    }

}
