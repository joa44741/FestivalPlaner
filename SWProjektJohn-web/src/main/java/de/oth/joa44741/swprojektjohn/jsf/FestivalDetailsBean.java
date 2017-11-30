/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf;

import de.oth.joa44741.swprojektjohn.core.FestivalWithDetailsDto;
import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivaldetailsBean")
@SessionScoped
public class FestivalDetailsBean implements Serializable {

    @Inject
    private FestivalService festivalService;

    private FestivalWithDetailsDto festivalDto;

    private static final long serialVersionUID = 1L;

    public String showFestivalDetails(Long id) {
        this.festivalDto = festivalService.retrieveFestivalDtoByIdIncludingDetails(id);
        return PageNames.FESTIVAL_DETAIL;
    }

    public FestivalWithDetailsDto getFestival() {
        return festivalDto;
    }

    public String setStatusOfFestivalToLoeschungAngefordert() {
        final FestivalEntity festival = festivalService.retrieveFestivalById(festivalDto.getId());
        festival.setStatus(StatusEnum.LOESCHUNG_ANGEFORDERT);
        festivalService.updateFestival(festival);
        final FacesMessage msg = new FacesMessage("Löschung für Festival " + festivalDto.getName() + " wurde beantragt");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.INDEX;
    }

}
