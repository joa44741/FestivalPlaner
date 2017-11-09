/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.web.jsf.util.PageNames;
import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivaldetailsBean")
@RequestScoped
public class FestivalDetailsBean extends FestivalBeanBase {

    @Inject
    private FestivalBusinessService festivalBusinessService;

    private static final long serialVersionUID = 1L;

    public String showFestivalDetails(Long id) {
        this.festival = festivalBusinessService.retrieveFestivalByIdIncludingDetails(id);
        return PageNames.FESTIVAL_DETAIL;
    }

    public FestivalEntity getFestival() {
        return festival;
    }

}
