/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.core.FestivalWithDetailsDto;
import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.core.logging.DoLogging;
import de.oth.joa44741.swprojektjohn.core.qualifier.BuehneRepository;
import de.oth.joa44741.swprojektjohn.core.qualifier.FestivalRepository;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import static de.oth.joa44741.swprojektjohn.repository.QueryParam.with;
import de.oth.joa44741.swprojektjohn.repository.Repository;
import de.oth.joa44741.swprojektjohn.services.weatherservice.WeatherSoapServiceClient;
import java.util.Arrays;
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
@DoLogging
public class FestivalServiceImpl implements FestivalService {

    private static final Logger LOG = Logger.getLogger(FestivalServiceImpl.class);

    @Inject
    @FestivalRepository
    private Repository<FestivalEntity> festivalRepository;

    @Inject
    @BuehneRepository
    private Repository<BuehneEntity> buehneRepository;

    @Inject
    private WeatherSoapServiceClient weatherClient;

    @WebMethod(exclude = true)
    @Override
    public List<FestivalEntity> findErstellteFestivals() {
        return findFestivalsByStatus(StatusEnum.ERSTELLT);
    }

    @WebMethod(exclude = true)
    @Override
    public List<FestivalEntity> findZuLoeschendeFestivals() {
        return findFestivalsByStatus(StatusEnum.LOESCHUNG_ANGEFORDERT);
    }

    @Override
    public List<FestivalEntity> findFreigegebeneFestivals() {
        final List<FestivalEntity> festivals = findFestivalsByStatus(StatusEnum.FREIGEGEBEN, StatusEnum.LOESCHUNG_ANGEFORDERT);
        return festivals;
    }

    @Override
    public FestivalWithDetailsDto retrieveFestivalDtoByIdIncludingDetails(Long festivalId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        final WeatherSoapServiceClient.WetterDto wetterDto = weatherClient.getWeather(festival);
        final FestivalWithDetailsDto dto = new FestivalWithDetailsDto(festival, wetterDto);
        return dto;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity retrieveFestivalById(Long id) {
        final FestivalEntity festivalEntity = festivalRepository.retrieveById(id);
        return festivalEntity;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity retrieveFestivalByIdIncludingDetails(Long festivalId) {
        final List<FestivalEntity> festival = this.festivalRepository
                .query(FestivalEntity.QUERY_NAME_RETRIEVE_FESTIVAL_BY_ID_INCLUDING_DETAILS,
                        with("id", festivalId).parameters(), Repository.SINGLE_RESULT);
        return festival.get(0);
    }

    @WebMethod(exclude = true)
    @Override
    public Optional<FestivalEntity> findFestivalByName(String name) {
        final List<FestivalEntity> festivalByName = this.festivalRepository.
                query(FestivalEntity.QUERY_NAME_FIND_FESTIVAL_BY_NAME,
                        with("name", name).
                                parameters(), Repository.SINGLE_RESULT);
        if (festivalByName.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(festivalByName.get(0));
        }
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity retrieveFestivalByLineupDateId(Long lineupDateId) {
        final List<FestivalEntity> festivals = this.festivalRepository
                .query(FestivalEntity.QUERY_NAME_RETRIEVE_FESTIVAL_BY_LINEUP_DATE_ID,
                        with("lineupDateId", lineupDateId).parameters(), Repository.SINGLE_RESULT);
        return festivals.get(0);
    }

    @WebMethod(exclude = true)
    @Override
    public List<FestivalEntity> findFestivals() {
        return festivalRepository.findAll();
    }

    private List<FestivalEntity> findFestivalsByStatus(StatusEnum... status) {
        final List<FestivalEntity> festivals = this.festivalRepository
                .query(FestivalEntity.QUERY_NAME_FIND_FESTIVALS_BY_STATUS,
                        with("status", Arrays.asList(status)).parameters(), Repository.NO_LIMIT);
        return festivals;
    }

    @WebMethod(exclude = true)
    @Override
    public List<FestivalEntity> findAllFestivalsInFutureByStatus(StatusEnum... status) {
        final List<FestivalEntity> festivals = this.festivalRepository
                .query(FestivalEntity.QUERY_NAME_FIND_FESTIVALS_IN_FUTURE_BY_STATUS,
                        with("status", Arrays.asList(status)).parameters(), Repository.NO_LIMIT);
        return festivals;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity createFestival(FestivalEntity entityToPersist) {
        LOG.log(Logger.Level.INFO, "createFestival() called");
        final FestivalEntity persistedEntity = festivalRepository.persistEntity(entityToPersist);
        return persistedEntity;
    }

    @WebMethod(exclude = true)
    @Override
    public void removeFestival(Long id) {
        festivalRepository.remove(id);
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity updateFestival(FestivalEntity festival) {
        return festivalRepository.update(festival);
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity addTicketArt(Long festivalId, TicketArtEntity ticketArt) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        festival.addTicketArt(ticketArt);
        return updateFestival(festival);
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity removeTicketArt(Long festivalId, Long ticketId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        final Optional<TicketArtEntity> optionalTicket = festival.getTicketArten().stream().filter(ticket -> ticket.getId().equals(ticketId)).findFirst();
        festival.removeTicketArt(optionalTicket.get());
        return updateFestival(festival);
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity addCampingVariante(Long festivalId, CampingVarianteEntity campingVariante) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        festival.addCampingVariante(campingVariante);
        return updateFestival(festival);
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity removeCampingVariante(Long festivalId, Long campingId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        final Optional<CampingVarianteEntity> optionalTicket = festival.getCampingVarianten().stream().filter(camping -> camping.getId().equals(campingId)).findFirst();
        festival.removeCampingVariante(optionalTicket.get());
        return updateFestival(festival);
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity addLineupDate(Long festivalId, LineupDateEntity lineupDate) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        festival.addLineupDate(lineupDate);
        return updateFestival(festival);
    }

    @WebMethod(exclude = true)
    @Override
    @Transactional
    public FestivalEntity removeLineupDate(Long festivalId, Long lineupDateId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        final Optional<LineupDateEntity> optLineupDate = festival.getLineupDates().stream().filter(lineup -> lineup.getId().equals(lineupDateId)).findFirst();
        final Optional<BuehneEntity> optBuehne = Optional.ofNullable(optLineupDate.get().getBuehne());
        optBuehne.ifPresent(b -> b.removeLineupDate(optLineupDate.get()));
        festival.removeLineupDate(optLineupDate.get());
//                return updateFestival(festival);
        return festival;
    }

    @WebMethod(exclude = true)
    @Override
    public FestivalEntity addBuehne(Long festivalId, BuehneEntity buehne) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        // bidirectional
        buehne.setFestival(festival);
        festival.addBuehne(buehne);
        return updateFestival(festival);
    }

    @WebMethod(exclude = true)
    @Override
    @Transactional
    public FestivalEntity removeBuehne(Long festivalId, Long buehnenId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        final Optional<BuehneEntity> optionalBuehne = festival.getBuehnen().stream().filter(buehne -> buehne.getId().equals(buehnenId)).findFirst();
        festival.removeBuehne(optionalBuehne.get());
        return updateFestival(festival);
    }

    @WebMethod(exclude = true)
    @Override
    public BuehneEntity retrieveBuehneById(Long buehneId) {
        return buehneRepository.retrieveById(buehneId);
    }

}
