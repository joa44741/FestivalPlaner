/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.bservice.AdminBusinessService;
import de.oth.joa44741.swprojektjohn.core.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.web.jsf.util.PageNames;
import de.oth.joa44741.swprojektjohn.web.webservice.FestivalService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("adminBean")
@SessionScoped
public class AdminBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<FestivalEntity> festivalsWaitingForStatusFreigegeben;
    private List<FestivalEntity> festivalsWithStatusLoeschungAngefordert;

    @Inject
    private AdminBusinessService adminBusinessService;

    @Inject
    private FestivalService festivalService;

    @PostConstruct
    private void initFields() {
        festivalsWaitingForStatusFreigegeben = festivalService.findFestivalsByStatus(StatusEnum.ERSTELLT, StatusEnum.AENDERUNG_ANGEFORDERT);
        festivalsWithStatusLoeschungAngefordert = festivalService.findFestivalsByStatus(StatusEnum.LOESCHUNG_ANGEFORDERT);
    }

    public String loadAndShowPage() {
        initFields();
        return PageNames.ADMIN_DATA;
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
