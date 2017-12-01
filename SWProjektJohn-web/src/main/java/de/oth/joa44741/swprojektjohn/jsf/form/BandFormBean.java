/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf.form;

import de.oth.joa44741.swprojektjohn.core.UpdateEvent;
import de.oth.joa44741.swprojektjohn.core.enums.GenreEnum;
import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.jsf.LoginBean;
import de.oth.joa44741.swprojektjohn.jsf.PageNames;
import de.oth.joa44741.swprojektjohn.services.BandService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Johnny
 */
@Named("bandFormBean")
@SessionScoped
public class BandFormBean implements Serializable {

    @Inject
    private Event<UpdateEvent> events;

    @Inject
    private BandService bandService;

    @Inject
    private LoginBean loginBean;

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
        final StatusEnum newStatus = loginBean.isAdminLoggedIn() ? StatusEnum.FREIGEGEBEN : StatusEnum.ERSTELLT;
        transientBand.setStatus(newStatus);
        final BandEntity persistedBand = bandService.persistBand(transientBand);
        transientAddedBands.add(persistedBand);
        events.fire(new UpdateEvent(persistedBand.getClass()));
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
        // remove band from database
        tryToFindBandById(bandId).ifPresent(band -> {
            bandService.removeBand(bandId);
        });
        BandEntity bandToDelete = transientAddedBands.stream().filter(band -> band.getId().equals(bandId)).findFirst().get();
        final FacesMessage msg = new FacesMessage("Die Band " + bandToDelete.getName() + " wurde gelöscht.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        transientAddedBands.remove(bandToDelete);
        return PageNames.CURRENT_PAGE;
    }

    /**
     * Band could be deleted by administrator in the meantime
     *
     * @return
     */
    private Optional<BandEntity> tryToFindBandById(Long bandId) {
        try {
            final BandEntity band = bandService.retrieveBandById(bandId);
            return Optional.of(band);
        } catch (EntityNotFoundException ex) {
            return Optional.empty();
        }
    }

}
