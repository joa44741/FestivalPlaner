/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf.form;

import de.oth.joa44741.swprojektjohn.bservice.BandBusinessService;
import de.oth.joa44741.swprojektjohn.core.GenreEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.web.jsf.util.PageNames;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Johnny
 */
@Named("bandFormBean")
@SessionScoped
public class BandFormBean implements Serializable {

    @Inject
    private BandBusinessService bandBusinessService;

    private List<GenreEnum> selectedGenres;

    private BandEntity transientBand;

    private final List<BandEntity> transientAddedBands = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    @PostConstruct
    private void initFields() {
        selectedGenres = new ArrayList<>();
        transientBand = new BandEntity();
    }

    public String persistBand() {
        selectedGenres.forEach(genre -> transientBand.addGenre(genre));
        final BandEntity persistedBand = bandBusinessService.persistBand(transientBand);
        transientAddedBands.add(persistedBand);
        final FacesMessage msg = new FacesMessage("Die Band " + transientBand.getName() + " wurde angelegt");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initFields();
        return PageNames.CURRENT_PAGE;
    }

    public BandEntity getTransientBand() {
        return transientBand;
    }

    public List<BandEntity> getTransientAddedBands() {
        return transientAddedBands;
    }

    public List<GenreEnum> getSelectedGenres() {
        return selectedGenres;
    }

    public void setSelectedGenres(List<GenreEnum> selectedGenres) {
        this.selectedGenres = selectedGenres;
    }

    public String removeBand(Long bandId) {
        final BandEntity bandToDelete = transientAddedBands.stream().filter(band -> band.getId().equals(bandId)).findFirst().get();
        transientAddedBands.remove(bandToDelete);
        bandBusinessService.removeBand(bandId);
        return PageNames.CURRENT_PAGE;
    }

    // call as listener
    public String insertionFinished() {
        this.transientAddedBands.clear();
        return PageNames.INDEX;
    }

}
