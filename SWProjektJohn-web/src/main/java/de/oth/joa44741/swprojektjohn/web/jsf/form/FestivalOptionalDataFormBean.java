/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf.form;

import de.oth.joa44741.swprojektjohn.bservice.BandBusinessService;
import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.core.CampingArtEnum;
import de.oth.joa44741.swprojektjohn.core.GenreEnum;
import de.oth.joa44741.swprojektjohn.core.TagArtEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import de.oth.joa44741.swprojektjohn.web.jsf.FestivalBeanBase;
import de.oth.joa44741.swprojektjohn.web.jsf.util.PageNames;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

// TODO: refactor!
@Named("festivalOptionalDataFormBean")
@SessionScoped
public class FestivalOptionalDataFormBean extends FestivalBeanBase {

    public void test() {
        System.out.println("TEST");
    }
    @Inject
    private FestivalBusinessService festivalBusinessService;

    @Inject
    private BandBusinessService bandBusinessService;

    private static final long serialVersionUID = 1L;

    private TicketArtEntity transientTicketArt;

    private CampingVarianteEntity transientCampingVariante;

    private List<BandEntity> availableBands;

    private BandEntity transientBand;

    private BuehneEntity transientBuehne;

    private LineupDateEntity transientLineupDate;

    private Long selectedBuehnenId;

    private Long selectedBandId;

    private final List<BandEntity> transientAddedBands = new ArrayList<>();

    private List<GenreEnum> selectedGenres;

    public String loadAndShowPage(Long id) {
        initFields(id);
        return PageNames.INSERT_OPTIONAL_FESTIVAL_DATA_TICKETS_AND_CAMPING;
    }

    private void initFields(Long id) {
        festival = festivalBusinessService.retrieveFestivalByIdIncludingDetails(id);
        availableBands = bandBusinessService.findAllBands();
        transientTicketArt = new TicketArtEntity();
        transientCampingVariante = new CampingVarianteEntity();
        transientBuehne = new BuehneEntity();
        selectedGenres = new ArrayList<>();
        transientBand = new BandEntity();
        transientLineupDate = new LineupDateEntity();
        selectedBandId = null;
        selectedBuehnenId = null;
    }

    public TicketArtEntity getTransientTicketArt() {
        return transientTicketArt;
    }

    public CampingVarianteEntity getTransientCampingVariante() {
        return transientCampingVariante;
    }

    public String persistTicketArt() {
        festival = festivalBusinessService.addTicketArt(festival.getId(), transientTicketArt);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String persistLineupDate() {
        final BandEntity band = bandBusinessService.retrieveBandById(selectedBandId);
        transientLineupDate.setBand(band);
        festival = festivalBusinessService.addLineupDate(selectedBuehnenId, transientLineupDate);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String persistBand() {
        selectedGenres.forEach(genre -> transientBand.addGenre(genre));
        final BandEntity persistedBand = bandBusinessService.persistBand(transientBand);
        transientAddedBands.add(persistedBand);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String persistCampingVariante() {
        festival = festivalBusinessService.addCampingVariante(festival.getId(), transientCampingVariante);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteTicketArt(Long ticketId) {
        festival = festivalBusinessService.removeTicketArt(festival.getId(), ticketId);
        final FacesMessage msg = new FacesMessage("Die Ticketart wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage("ticketForm", msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteCampingVariante(Long campingId) {
        festival = festivalBusinessService.removeCampingVariante(festival.getId(), campingId);
        final FacesMessage msg = new FacesMessage("Die Campingvariante wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteLineupDate(Long buehnenId, Long lineupId) {
        festival = festivalBusinessService.removeLineupDate(buehnenId, lineupId);
        final FacesMessage msg = new FacesMessage("Der Lineup Eintrag wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String persistBuehne() {
        festival = festivalBusinessService.addBuehne(festival.getId(), transientBuehne);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteBuehne(Long buehneId) {
        festival = festivalBusinessService.removeBuehne(festival.getId(), buehneId);
        final FacesMessage msg = new FacesMessage("Die Bühne wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage("buehnenForm", msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public FestivalEntity getPersistedFestival() {
        return festival;
    }

    public List<TagArtEnum> getTagArtenAsList() {
        return Arrays.asList(TagArtEnum.values());
    }

    public List<CampingArtEnum> getCampingArtenAsList() {
        return Arrays.asList(CampingArtEnum.values());
    }

    public LineupDateEntity getTransientLineupDate() {
        return transientLineupDate;
    }

    public List<BandEntity> getAvailableBands() {
        return availableBands;
    }

    public Long getSelectedBuehnenId() {
        return selectedBuehnenId;
    }

    public void setSelectedBuehnenId(Long selectedBuehnenId) {
        this.selectedBuehnenId = selectedBuehnenId;
    }

    public BuehneEntity getTransientBuehne() {
        return transientBuehne;
    }

    public BandEntity getTransientBand() {
        return transientBand;
    }

    public List<BandEntity> getTransientAddedBands() {
        return transientAddedBands;
    }

    public List<GenreEnum> getAvailableGenres() {
        return Arrays.asList(GenreEnum.values());
    }

    public List<GenreEnum> getSelectedGenres() {
        return selectedGenres;
    }

    public void setSelectedGenres(List<GenreEnum> selectedGenres) {
        this.selectedGenres = selectedGenres;
    }

    // TODO: delete or remove... not both
    public String deleteBand(Long bandId) {
        BandEntity bandToDelete = availableBands.stream().filter(band -> band.getId() == bandId).findFirst().get();
        transientAddedBands.remove(bandToDelete);
        bandBusinessService.removeBand(bandId);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public Long getSelectedBandId() {
        return selectedBandId;
    }

    public void setSelectedBandId(Long selectedBandId) {
        this.selectedBandId = selectedBandId;
    }

    public String insertionFinished() {
        this.transientAddedBands.clear();
        return PageNames.INDEX;
    }

    public List<Date> getPossibleTagesticketDates() {
        final List<Date> possibleDates = new ArrayList<>();
        Date datumVon = this.festival.getDatumVon();
        Date datumBis = this.festival.getDatumBis();
        Calendar cal = Calendar.getInstance();
        cal.setTime(datumVon);
        while (!cal.getTime().equals(datumBis)) {
            possibleDates.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        possibleDates.add(cal.getTime());
        return possibleDates;
    }

    public String getDateAsString(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

}
