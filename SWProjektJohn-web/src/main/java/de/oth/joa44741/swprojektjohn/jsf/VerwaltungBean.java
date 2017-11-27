/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf;

import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.jsf.util.PageNames;
import de.oth.joa44741.swprojektjohn.services.BandService;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("verwaltungBean")
@SessionScoped
public class VerwaltungBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<FestivalEntity> festivalsWaitingForStatusFreigegeben;
    private List<FestivalEntity> festivalsWithStatusLoeschungAngefordert;
    private List<BandEntity> bandsWaitingForStatusFreigegeben;
    private List<BandEntity> bandsWithStatusLoeschungAngefordert;

    @Inject
    private FestivalService festivalService;

    @Inject
    private BandService bandService;

    @PostConstruct
    private void initFields() {
        festivalsWaitingForStatusFreigegeben = festivalService.findErstellteFestivals();
        festivalsWithStatusLoeschungAngefordert = festivalService.findZuLoeschendeFestivals();
        bandsWaitingForStatusFreigegeben = bandService.findBandsByStatus(StatusEnum.ERSTELLT);
        bandsWithStatusLoeschungAngefordert = bandService.findBandsByStatus(StatusEnum.LOESCHUNG_ANGEFORDERT);
    }

    public String loadAndShowPage() {
        initFields();
        return PageNames.VERWALTUNG;
    }

    public String removeFestival(Long id) {
        festivalService.removeFestival(id);
        initFields();
        final FacesMessage msg = new FacesMessage("Festival wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String setStatusFreigegebenOfFestival(Long id) {
        final FestivalEntity festival = festivalService.retrieveFestivalById(id);
        festival.setStatus(StatusEnum.FREIGEGEBEN);
        festivalService.updateFestival(festival);
        initFields();
        final FacesMessage msg = new FacesMessage("Festival wurde freigegeben");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String setStatusFreigegebenOfBand(Long id) {
        final BandEntity band = bandService.retrieveBandById(id);
        band.setStatus(StatusEnum.FREIGEGEBEN);
        bandService.updateBand(band);
        initFields();
        final FacesMessage msg = new FacesMessage("Band wurde freigegeben");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public String removeBand(Long id) {
        bandService.removeBand(id);
        initFields();
        final FacesMessage msg = new FacesMessage("Band wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.CURRENT_PAGE;
    }

    public List<FestivalEntity> getFestivalsWithStatusLoeschungAngefordert() {
        return festivalsWithStatusLoeschungAngefordert;
    }

    public List<FestivalEntity> getFestivalsWaitingForStatusFreigegeben() {
        return festivalsWaitingForStatusFreigegeben;
    }

    public List<BandEntity> getBandsWaitingForStatusFreigegeben() {
        return bandsWaitingForStatusFreigegeben;
    }

    public List<BandEntity> getBandsWithStatusLoeschungAngefordert() {
        return bandsWithStatusLoeschungAngefordert;
    }
}
