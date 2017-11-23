/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf.form;

import de.oth.joa44741.swprojektjohn.core.enums.CampingArtEnum;
import de.oth.joa44741.swprojektjohn.core.enums.TagArtEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import de.oth.joa44741.swprojektjohn.jsf.FestivalBeanBase;
import de.oth.joa44741.swprojektjohn.jsf.util.PageNames;
import de.oth.joa44741.swprojektjohn.services.BandService;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

// TODO: refactor! own Bean for Band, Lineup, Ticket, Camping
@Named("festivalOptionalDataFormBean")
@SessionScoped
public class FestivalOptionalDataFormBean extends FestivalBeanBase {

    @Inject
    private FestivalService festivalService;

    @Inject
    private BandService bandService;

    private static final long serialVersionUID = 1L;

    private TicketArtEntity transientTicketArt;

    private CampingVarianteEntity transientCampingVariante;

    private List<BandEntity> availableBands;

    private BuehneEntity transientBuehne;

    private LineupDateEntity transientLineupDate;

    private Long selectedBuehnenId;

    private Long selectedBandId;

    public String loadAndShowTicketsAndCampingPage(Long id) {
        initFields(id);
        return PageNames.INSERT_OPTIONAL_FESTIVAL_DATA_TICKETS_AND_CAMPING;
    }

    public String loadAndShowLineupPage(Long id) {
        initFields(id);
        return PageNames.INSERT_OPTIONAL_FESTIVAL_DATA_LINEUP;
    }

    @PostConstruct
    private void initTransientFields() {
        transientTicketArt = new TicketArtEntity();
        transientCampingVariante = new CampingVarianteEntity();
        transientBuehne = new BuehneEntity();
        transientLineupDate = new LineupDateEntity();
        selectedBandId = null;
        selectedBuehnenId = null;
    }

    private void initFields(Long id) {
        festival = festivalService.retrieveFestivalByIdIncludingDetails(id);
        availableBands = bandService.findAllBands();
        initTransientFields();
    }

    public TicketArtEntity getTransientTicketArt() {
        return transientTicketArt;
    }

    public CampingVarianteEntity getTransientCampingVariante() {
        return transientCampingVariante;
    }

    public String persistTicketArt() {
        festival = festivalService.addTicketArt(festival.getId(), transientTicketArt);
        final FacesMessage msg = new FacesMessage("Die Ticketart wurde angelegt");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String persistLineupDate() {
        final BandEntity band = bandService.retrieveBandById(selectedBandId);
        final BuehneEntity buehne = festival.getBuehnen().stream().filter(b -> b.getId().equals(selectedBuehnenId)).findFirst().get();
        transientLineupDate.setBand(band);
        transientLineupDate.setBuehne(buehne);
        festival = festivalService.addLineupDate(festival.getId(), transientLineupDate);
        final FacesMessage msg = new FacesMessage("Der Lineup Eintrag wurde gespeichert");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String persistCampingVariante() {
        festival = festivalService.addCampingVariante(festival.getId(), transientCampingVariante);
        final FacesMessage msg = new FacesMessage("Die Campingvariante wurde angelegt");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String deleteTicketArt(Long ticketId) {
        festival = festivalService.removeTicketArt(festival.getId(), ticketId);
        final FacesMessage msg = new FacesMessage("Die Ticketart wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String deleteCampingVariante(Long campingId) {
        festival = festivalService.removeCampingVariante(festival.getId(), campingId);
        final FacesMessage msg = new FacesMessage("Die Campingvariante wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String deleteLineupDate(Long lineupId) {
        festival = festivalService.removeLineupDate(festival.getId(), lineupId);
        final FacesMessage msg = new FacesMessage("Der Lineup Eintrag wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String persistBuehne() {
        festival = festivalService.addBuehne(festival.getId(), transientBuehne);
        return PageNames.CURRENT_PAGE;
    }

    public String deleteBuehne(Long buehneId) {
        festival = festivalService.removeBuehne(festival.getId(), buehneId);
        final FacesMessage msg = new FacesMessage("Die Bühne wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage("buehnenForm", msg);
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

    public Long getSelectedBandId() {
        return selectedBandId;
    }

    public void setSelectedBandId(Long selectedBandId) {
        this.selectedBandId = selectedBandId;
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

    public void handleEvent(ComponentSystemEvent event) {
        initFields(festival.getId());
    }
}
