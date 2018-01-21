/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.core.FestivalWithDetailsDto;
import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.core.logging.DoLogging;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import de.oth.joa44741.swprojektjohn.repository.BuehneRepository;
import de.oth.joa44741.swprojektjohn.repository.FestivalRepository;
import static de.oth.joa44741.swprojektjohn.repository.QueryParam.with;
import de.oth.joa44741.swprojektjohn.repository.Repository;
import de.oth.joa44741.swprojektjohn.services.weatherservice.WeatherSoapServiceClient;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;

/**
 * This is the only class that is accessed by other applications via SOAP
 * webservices
 *
 * There are only 2 methods that can be called from outside:
 *
 * - findFreigegebeneFestivals()
 *
 * - retrieveFestivalDtoByIdIncludingDetails()
 *
 * @author Andreas John
 */
@RequestScoped
@WebService(serviceName = "FestivalService", portName = "FestivalPort")
@DoLogging
public class FestivalServiceImpl implements FestivalService {

    private static final Logger LOG = Logger.getLogger(FestivalServiceImpl.class);

    @Inject
    private FestivalRepository festivalRepository;

    @Inject
    private BuehneRepository buehneRepository;

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

    /**
     * This method can be called from another application via a SOAP webservice
     * call. It searches for festivals with status 'FREIGEGEBEN' or
     * 'LOESCHUNG_ANGEFORDERT'. The returned festivals haven't got all data. For
     * example the lineup isn't included because it isn't always needed and
     * would cause a higher data traffic. In order to see detailed data of a
     * festival the method retrieveFestivalDtoByIdIncludingDetails has to be
     * called.
     *
     * @return a list of all festivals that have been approved by the
     * administrator and have not been fully deleted yet
     */
    @Override
    public List<FestivalEntity> findFreigegebeneFestivals() {
        final List<FestivalEntity> festivals = findFestivalsByStatus(StatusEnum.FREIGEGEBEN, StatusEnum.LOESCHUNG_ANGEFORDERT);
        return festivals;
    }

    /**
     * This method returns all available data of a festival, for example lineup,
     * band details and ticket variations. It also calls the webservice of the
     * Wetterdienst application provided by Max Fries and adds this data to the
     * result.
     *
     * @param festivalId - the id of the festival
     * @return a data transfer object containing all the detail information of a
     * festival
     */
    @Override
    public FestivalWithDetailsDto retrieveFestivalDtoByIdIncludingDetails(@WebParam(name = "festivalId") Long festivalId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        final Optional<WeatherSoapServiceClient.WetterDto> optWetterDto = weatherClient.getWeather(festival);
        final FestivalWithDetailsDto dto = new FestivalWithDetailsDto(festival, optWetterDto.orElse(null));
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
        try {
            final List<FestivalEntity> festivalByName = this.festivalRepository.
                    query(FestivalEntity.QUERY_NAME_FIND_FESTIVAL_BY_NAME,
                            with("name", name).
                                    parameters(), Repository.SINGLE_RESULT);
            return Optional.of(festivalByName.get(0));
        } catch (EntityNotFoundException ex) {
            return Optional.empty();
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

    /**
     * This method is declared as transactional because otherwise removing
     * didn't work. The problem was that after calling the remove method via the
     * repository class, the entity wasn't deleted in the database. I think the
     * bidirectional binding between LineupDateEntity and BuehneEntity was a
     * problem here, maybe in addition to the second relation in the
     * FestivalEntity.
     *
     * @param festivalId - the id of the related festival
     * @param lineupDateId - the id of the festival that should be deleted
     * @return the updated festival without the lineup entry
     */
    @WebMethod(exclude = true)
    @Override
    @Transactional
    public FestivalEntity removeLineupDate(Long festivalId, Long lineupDateId) {
        final FestivalEntity festival = retrieveFestivalByIdIncludingDetails(festivalId);
        final Optional<LineupDateEntity> optLineupDate = festival.getLineupDates().stream().filter(lineup -> lineup.getId().equals(lineupDateId)).findFirst();
        final Optional<BuehneEntity> optBuehne = Optional.ofNullable(optLineupDate.get().getBuehne());
        optBuehne.ifPresent(b -> b.removeLineupDate(optLineupDate.get()));
        festival.removeLineupDate(optLineupDate.get());
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

    /**
     * The method is transactional because of the same problem that is described
     * in the JavaDoc of the removeLineupDate method. The bidirectional binding
     * caused some problems.
     *
     * @param festivalId - the id of the related festival
     * @param buehnenId - the id of the stage (Buehne) that should be deleted
     * @return the updated festival without the buehne entry
     */
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
