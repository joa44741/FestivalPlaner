/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf;

import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.core.util.LineupDateUtils;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.services.BandService;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("bandDetailsBean")
@SessionScoped
public class BandDetailsBean implements Serializable {

    @Inject
    private FestivalDetailsBean festivalDetailsBean;

    @Inject
    private BandService bandService;

    @Inject
    private FestivalService festivalService;

    private BandEntity band;

    private final Map<Long, FestivalEntity> lineupIdToFestivalMap = new HashMap<>();

    private static final long serialVersionUID = 1L;

    public String showBandDetails(Long id) {
        System.out.println("Bandid: " + id);
        this.band = bandService.retrieveBandByIdIncludingDetails(id);
        this.band.getLineupDates().forEach(lineup -> lineupIdToFestivalMap.put(lineup.getId(), festivalService.retrieveFestivalByLineupDateId(lineup.getId())));
        return PageNames.BAND_DETAILS;
    }

    public BandEntity getBand() {
        return band;
    }

    public FestivalEntity getFestivalByLineupDateId(Long id) {
        return lineupIdToFestivalMap.get(id);
    }

    public List<LineupDateEntity> getLineupDatesOrderByDate() {
        return band.getLineupDates()
                .stream()
                .sorted(LineupDateUtils.LINEUP_DATE_TIME_COMPARATOR)
                .collect(Collectors.toList());
    }

    public String loadAndShowFestivalDetailsByLineupDateId(Long lineupId) {
        final FestivalEntity festival = getFestivalByLineupDateId(lineupId);
        return festivalDetailsBean.showFestivalDetails(festival.getId());
    }

    public String setStatusOfBandToLoeschungAngefordert() {
        band.setStatus(StatusEnum.LOESCHUNG_ANGEFORDERT);
        bandService.updateBand(band);
        final FacesMessage msg = new FacesMessage("Löschung für Band " + band.getName() + " wurde beantragt");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return PageNames.INDEX;
    }
}
