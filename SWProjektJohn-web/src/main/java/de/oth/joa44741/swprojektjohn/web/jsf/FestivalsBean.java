/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivalsBean")
@RequestScoped
public class FestivalsBean implements Serializable {

    @Inject
    private FestivalBusinessService festivalBusinessService;

    private static final long serialVersionUID = 1L;

    private List<FestivalEntity> festivals;

    @PostConstruct
    public void initFields() {
        festivals = festivalBusinessService.findAllFestivals();
    }

    public List<FestivalEntity> getFestivals() {
        return festivals;
    }
}
