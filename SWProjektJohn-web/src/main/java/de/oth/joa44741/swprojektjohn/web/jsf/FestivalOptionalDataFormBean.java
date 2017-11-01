/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.core.CampingArtEnum;
import de.oth.joa44741.swprojektjohn.core.TagArtEnum;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivalOptionalDataFormBean")
@SessionScoped
public class FestivalOptionalDataFormBean implements Serializable {

    @Inject
    private FestivalBusinessService festivalBusinessService;

    private static final long serialVersionUID = 1L;

    private FestivalEntity persistedFestival;

    private TicketArtEntity transientTicketArt;

    private CampingVarianteEntity transientCampingVariante;

//    @PostConstruct
//    public void initFields() {
//        transientFestival = new FestivalEntity();
//        transientTicketArt = new TicketArtEntity();
//        transientFestival.setLocation(new LocationEntity());
//    }
    public String loadAndShowPage(Long id) {
        initFields(id);
        return PageNames.INSERT_OPTIONAL_FESTIVAL_DATA;
    }

    private void initFields(Long id) {
        persistedFestival = festivalBusinessService.retrieveFestivalByIdIncludingDetails(id);
        transientTicketArt = new TicketArtEntity();
        transientCampingVariante = new CampingVarianteEntity();
    }

    public TicketArtEntity getTransientTicketArt() {
        return transientTicketArt;
    }

    public CampingVarianteEntity getTransientCampingVariante() {
        return transientCampingVariante;
    }

    public String persistTicketArt() {
        persistedFestival = festivalBusinessService.addTicketArt(persistedFestival.getId(), transientTicketArt);
        initFields(persistedFestival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String persistCampingVariante() {
        persistedFestival = festivalBusinessService.addCampingVariante(persistedFestival.getId(), transientCampingVariante);
        initFields(persistedFestival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteTicketArt(Long ticketId) {
        persistedFestival = festivalBusinessService.removeTicketArt(persistedFestival.getId(), ticketId);
        final FacesMessage msg = new FacesMessage("Die Ticketart wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage("ticketForm", msg);
        initFields(persistedFestival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteCampingVariante(Long campingId) {
        persistedFestival = festivalBusinessService.removeCampingVariante(persistedFestival.getId(), campingId);
        final FacesMessage msg = new FacesMessage("Die Campingvariante wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initFields(persistedFestival.getId());
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
}
