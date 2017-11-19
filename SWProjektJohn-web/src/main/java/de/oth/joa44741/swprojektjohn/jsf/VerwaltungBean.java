/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf;

import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.jsf.util.PageNames;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("verwaltungBean")
@SessionScoped
public class VerwaltungBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<FestivalEntity> festivalsWaitingForStatusFreigegeben;
    private List<FestivalEntity> festivalsWithStatusLoeschungAngefordert;

    @Inject
    private FestivalService festivalService;

    @PostConstruct
    private void initFields() {
        festivalsWaitingForStatusFreigegeben = festivalService.findFestivalsByStatus(StatusEnum.ERSTELLT, StatusEnum.AENDERUNG_ANGEFORDERT);
        festivalsWithStatusLoeschungAngefordert = festivalService.findFestivalsByStatus(StatusEnum.LOESCHUNG_ANGEFORDERT);
    }

    public String loadAndShowPage() {
        initFields();
        return PageNames.VERWALTUNG;
    }

    public String removeFestival(Long id) {
        festivalService.removeFestival(id);
        initFields();
        return PageNames.CURRENT_PAGE;
    }

    public String setStatusFreigegebenOfFestival(Long id) {
        final FestivalEntity festival = festivalService.retrieveFestivalById(id);
        festival.setStatus(StatusEnum.FREIGEGEBEN);
        festivalService.updateFestival(festival);
        initFields();
        return PageNames.CURRENT_PAGE;
    }

    public List<FestivalEntity> getFestivalsWithStatusLoeschungAngefordert() {
        return festivalsWithStatusLoeschungAngefordert;
    }

    public List<FestivalEntity> getFestivalsWaitingForStatusFreigegeben() {
        return festivalsWaitingForStatusFreigegeben;
    }

}
