/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.web.jsf.util.PageNames;
import de.oth.joa44741.swprojektjohn.bservice.BandBusinessService;
import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("bandDetailsBean")
@SessionScoped
public class BandDetailsBean implements Serializable {

    @Inject
    private BandBusinessService bandBusinessService;

    @Inject
    private FestivalBusinessService festivalBusinessService;

    private BandEntity band;

    private final Map<Long, FestivalEntity> lineupIdToFestivalMap = new HashMap<>();

    private static final long serialVersionUID = 1L;

    public String showBandDetails(Long id) {
        this.band = bandBusinessService.retrieveBandByIdIncludingDetails(id);
        this.band.getLineupDates().forEach(lineup -> lineupIdToFestivalMap.put(lineup.getId(), festivalBusinessService.retrieveFestivalByLineupDateId(lineup.getId())));
        return PageNames.BAND_DETAILS;
    }

    public BandEntity getBand() {
        return band;
    }

    public FestivalEntity getFestivalByLineupDateId(Long id) {
        return lineupIdToFestivalMap.get(id);
    }

}
