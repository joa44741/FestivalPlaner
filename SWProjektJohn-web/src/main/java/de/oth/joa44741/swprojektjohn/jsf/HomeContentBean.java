/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf;

import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.services.BandService;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("homeContentBean")
@SessionScoped
public class HomeContentBean implements Serializable {

    @Inject
    private FestivalService festivalService;

    @Inject
    private BandService bandService;

    private static final long serialVersionUID = 1L;

    private List<FestivalEntity> festivalsInFuture;
    private List<BandEntity> randomBands;

    @PostConstruct
    public void initFields() {
        festivalsInFuture = festivalService.findAllFestivalsInFutureByStatus(StatusEnum.FREIGEGEBEN, StatusEnum.LOESCHUNG_ANGEFORDERT);
        randomBands = bandService.findRandomBandsByStatus(StatusEnum.FREIGEGEBEN, StatusEnum.LOESCHUNG_ANGEFORDERT);
    }

    public String loadAndShowPage() {
        initFields();
        return PageNames.INDEX;
    }

    public List<FestivalEntity> getUpcomingFestivals(int numberOfFestivals) {
        return festivalsInFuture.stream().limit(numberOfFestivals).collect(Collectors.toList());
    }

    public List<BandEntity> getRandomBands(int numberOfBands) {
        return randomBands.stream().limit(numberOfBands).collect(Collectors.toList());
    }
}
