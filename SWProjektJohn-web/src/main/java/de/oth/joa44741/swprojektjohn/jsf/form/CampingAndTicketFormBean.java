/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf.form;

import de.oth.joa44741.swprojektjohn.core.enums.CampingArtEnum;
import de.oth.joa44741.swprojektjohn.core.enums.TagArtEnum;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import de.oth.joa44741.swprojektjohn.jsf.PageNames;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("campingAndTicketFormBean")
@SessionScoped
public class CampingAndTicketFormBean implements Serializable {

    @Inject
    private FestivalService festivalService;

    private static final long serialVersionUID = 1L;

    private TicketArtEntity transientTicketArt;

    private CampingVarianteEntity transientCampingVariante;

    private FestivalEntity persistedFestival;

    public String loadAndShowTicketsAndCampingPage(Long id) {
        initFields(id);
        return PageNames.INSERT_CAMPING_AND_TICKETS;
    }

    @PostConstruct
    private void initTransientFields() {
        transientTicketArt = new TicketArtEntity();
        transientCampingVariante = new CampingVarianteEntity();
    }

    private void initFields(Long id) {
        persistedFestival = festivalService.retrieveFestivalByIdIncludingDetails(id);
        initTransientFields();
    }

    public TicketArtEntity getTransientTicketArt() {
        return transientTicketArt;
    }

    public CampingVarianteEntity getTransientCampingVariante() {
        return transientCampingVariante;
    }

    public String persistTicketArt() {
        persistedFestival = festivalService.addTicketArt(persistedFestival.getId(), transientTicketArt);
        final FacesMessage msg = new FacesMessage("Die Ticketart wurde angelegt");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initTransientFields();
        return PageNames.CURRENT_PAGE;
    }

    public String persistCampingVariante() {
        persistedFestival = festivalService.addCampingVariante(persistedFestival.getId(), transientCampingVariante);
        final FacesMessage msg = new FacesMessage("Die Campingvariante wurde angelegt");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initTransientFields();
        return PageNames.CURRENT_PAGE;
    }

    public String deleteTicketArt(Long ticketId) {
        persistedFestival = festivalService.removeTicketArt(persistedFestival.getId(), ticketId);
        final FacesMessage msg = new FacesMessage("Die Ticketart wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String deleteCampingVariante(Long campingId) {
        persistedFestival = festivalService.removeCampingVariante(persistedFestival.getId(), campingId);
        final FacesMessage msg = new FacesMessage("Die Campingvariante wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public FestivalEntity getPersistedFestival() {
        return persistedFestival;
    }

    public List<TagArtEnum> getTagArtenAsList() {
        return Arrays.asList(TagArtEnum.values());
    }

    public List<CampingArtEnum> getCampingArtenAsList() {
        return Arrays.asList(CampingArtEnum.values());
    }

    public List<Date> getPossibleTagesticketDates() {
        final List<Date> possibleDates = new ArrayList<>();
        Date datumVon = this.persistedFestival.getDatumVon();
        Date datumBis = this.persistedFestival.getDatumBis();
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
