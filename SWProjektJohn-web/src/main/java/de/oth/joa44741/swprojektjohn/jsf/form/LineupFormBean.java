/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf.form;

import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.jsf.util.PageNames;
import de.oth.joa44741.swprojektjohn.services.BandService;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

// TODO: refactor! own Bean for Band, Lineup, Ticket, Camping
@Named("lineupFormBean")
@SessionScoped
public class LineupFormBean implements Serializable {

    private FestivalEntity festival;

    @Inject
    private FestivalService festivalService;

    @Inject
    private BandService bandService;

    private static final long serialVersionUID = 1L;

    private List<BandEntity> availableBands;

    private BuehneEntity transientBuehne;

    private LineupDateEntity transientLineupDate;

    private Long selectedBuehnenId;

    private Long selectedBandId;

    public String loadAndShowLineupPage(Long id) {
        initFields(id);
        return PageNames.INSERT_LINEUP;
    }

    @PostConstruct
    private void initTransientFields() {
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

    public String persistLineupDate() {
        final BandEntity band = bandService.retrieveBandById(selectedBandId);
        final Optional<BuehneEntity> optBuehne = festival.getBuehnen().stream().filter(b -> b.getId().equals(selectedBuehnenId)).findFirst();
        transientLineupDate.setBand(band);
        optBuehne.ifPresent(b -> transientLineupDate.setBuehne(b));
        festival = festivalService.addLineupDate(festival.getId(), transientLineupDate);
        final FacesMessage msg = new FacesMessage("Der Lineup Eintrag wurde gespeichert");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteLineupDate(Long lineupId) {
        festival = festivalService.removeLineupDate(festival.getId(), lineupId);
        final FacesMessage msg = new FacesMessage("Der Lineup Eintrag wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String persistBuehne() {
        festival = festivalService.addBuehne(festival.getId(), transientBuehne);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteBuehne(Long buehneId) {
        festival = festivalService.removeBuehne(festival.getId(), buehneId);
        final FacesMessage msg = new FacesMessage("Die Bühne wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage("buehnenForm", msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public FestivalEntity getPersistedFestival() {
        return festival;
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
}
