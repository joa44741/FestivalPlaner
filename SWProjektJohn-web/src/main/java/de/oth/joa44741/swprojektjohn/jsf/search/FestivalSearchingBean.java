/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf.search;

import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

@Named("festivalSearchingBean")
@ViewScoped
public class FestivalSearchingBean implements Serializable {

    @Inject
    private FestivalService festivalService;

    private String filterName;

    private Date datumAb;
    private Date datumBis;

    private static final long serialVersionUID = 1L;

    private List<FestivalEntity> allFestivals;

    private List<FestivalEntity> matchingFestivals;

    @PostConstruct
    public void initFields() {
        allFestivals = festivalService.findFestivalsByStatus(StatusEnum.FREIGEGEBEN, StatusEnum.LOESCHUNG_ANGEFORDERT);
        matchingFestivals = new ArrayList<>(allFestivals);
    }

    public List<FestivalEntity> getMatchingFestivals() {
        return matchingFestivals;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public Date getDatumAb() {
        return datumAb;
    }

    public void setDatumAb(Date datumAb) {
        this.datumAb = datumAb;
    }

    public Date getDatumBis() {
        return datumBis;
    }

    public void setDatumBis(Date datumBis) {
        this.datumBis = datumBis;
    }

    public void updateMatchingFestivals() {
        if (StringUtils.isEmpty(filterName)) {
            matchingFestivals = new ArrayList<>(allFestivals);
        } else {
            matchingFestivals = allFestivals.stream()
                    .filter(festival -> StringUtils.containsIgnoreCase(festival.getName(), filterName))
                    .collect(Collectors.toList());
        }
        if (datumAb != null) {
            matchingFestivals = matchingFestivals.stream()
                    .filter(festival -> !festival.getDatumVon().before(datumAb))
                    .collect(Collectors.toList());
        }
        if (datumBis != null) {
            matchingFestivals = matchingFestivals.stream()
                    .filter(festival -> !festival.getDatumBis().after(datumBis))
                    .collect(Collectors.toList());
        }
    }
}
