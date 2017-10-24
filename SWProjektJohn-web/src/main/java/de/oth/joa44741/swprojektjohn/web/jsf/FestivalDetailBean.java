/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivaldetailsBean")
@RequestScoped
public class FestivalDetailBean implements Serializable {

    @Inject
    private FestivalBusinessService festivalBusinessService;

    private static final long serialVersionUID = 1L;

    private static final int BOOTSTRAP_MAX_COL_SIZE = 12;

    private FestivalEntity festival;

    public String showFestivalDetails(Long id) {
        this.festival = festivalBusinessService.retrieveFestivalByIdIncludingDetails(id);
        return PageNames.FESTIVAL_DETAIL;
    }

    public FestivalEntity getFestival() {
        return festival;
    }

    public Integer calculateBootstrapColSize() {
        final int size = BOOTSTRAP_MAX_COL_SIZE / festival.getBuehnen().size();
        return size;
    }

    public Map<Date, List<LineupDateEntity>> getLineupDatesPerTagByBuehne(Long buehnenId) {
        final Optional<BuehneEntity> optBuehne = this.festival.getBuehnen().stream().filter(b -> b.getId() == buehnenId).findFirst();
        if (optBuehne.isPresent()) {
            Map<Date, List<LineupDateEntity>> lineupDatesPerTag = optBuehne.get()
                    .getLineupDates()
                    .stream()
                    .collect(Collectors.toMap(LineupDateEntity::getTag, (d) -> d.g);
            return lineupDatesPerTag;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("The message to display in client"));
        }
        return Collections.EMPTY_MAP;
    }
}
