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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivaldetailsBean")
@SessionScoped
public class FestivalDetailsBean extends FestivalBeanBase {

    @Inject
    private FestivalService festivalService;

    private static final long serialVersionUID = 1L;

    public String showFestivalDetails(Long id) {
        this.festival = festivalService.retrieveFestivalByIdIncludingDetails(id);
        return PageNames.FESTIVAL_DETAIL;
    }

    public FestivalEntity getFestival() {
        return festival;
    }

    public String setStatusOfFestivalToLoeschungAngefordert() {
        festival.setStatus(StatusEnum.LOESCHUNG_ANGEFORDERT);
        festivalService.updateFestival(festival);
        final FacesMessage msg = new FacesMessage("Löschung für Festival " + festival.getName() + " wurde beantragt");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.INDEX;
    }

}
