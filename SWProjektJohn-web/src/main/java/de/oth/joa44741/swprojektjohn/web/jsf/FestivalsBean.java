/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.core.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.web.webservice.BandService;
import de.oth.joa44741.swprojektjohn.web.webservice.FestivalService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivalsBean")
@RequestScoped
// TODO: andere Benennung
public class FestivalsBean implements Serializable {

    @Inject
    private FestivalService festivalService;

    @Inject
    private BandService bandService;

    private static final long serialVersionUID = 1L;

    private List<FestivalEntity> festivalsInFuture;
    private List<BandEntity> bands;

    @PostConstruct
    public void initFields() {
        festivalsInFuture = festivalService.findAllFestivalsInFutureByStatus(StatusEnum.FREIGEGEBEN, StatusEnum.LOESCHUNG_ANGEFORDERT);
        bands = bandService.findAllBands();
    }

    public List<FestivalEntity> getUpcomingFestivals(int numberOfFestivals) {
        return festivalsInFuture.stream().limit(numberOfFestivals).collect(Collectors.toList());
    }

    public List<BandEntity> getRandomBands(int numberOfBands) {
//        Collections.shuffle(bands);
        return bands.stream().limit(numberOfBands).collect(Collectors.toList());
    }
}
