package de.oth.joa44741.swprojektjohn.jsf.form;

import de.oth.joa44741.swprojektjohn.core.UpdateEvent;
import de.oth.joa44741.swprojektjohn.core.logging.DoLogging;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.jsf.PageNames;
import de.oth.joa44741.swprojektjohn.services.BandService;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Andreas John
 */
@Named("lineupFormBean")
@SessionScoped
public class LineupFormBean implements Serializable {

    private FestivalEntity festival;

    @Inject
    private FestivalService festivalService;

    @Inject
    private BandService bandService;

    private static final long serialVersionUID = 1L;

    private List<BandEntity> availableBands;

    private BuehneEntity transientBuehne;

    private LineupDateEntity transientLineupDate;

    private Long selectedBuehnenId;

    private Long selectedBandId;

    public String loadAndShowLineupPage(Long id) {
        initFields(id);
        return PageNames.INSERT_LINEUP;
    }

    @PostConstruct
    private void initTransientFields() {
        transientBuehne = new BuehneEntity();
        transientLineupDate = new LineupDateEntity();
        selectedBandId = null;
        selectedBuehnenId = null;
    }

    private void initFields(Long id) {
        festival = festivalService.retrieveFestivalByIdIncludingDetails(id);
        availableBands = bandService.findAllBands();
        initTransientFields();
    }

    public String persistLineupDate() {
        final BandEntity band = bandService.retrieveBandById(selectedBandId);
        final Optional<BuehneEntity> optBuehne = festival.getBuehnen().stream().filter(b -> b.getId().equals(selectedBuehnenId)).findFirst();
        transientLineupDate.setBand(band);
        optBuehne.ifPresent(b -> transientLineupDate.setBuehne(b));
        festival = festivalService.addLineupDate(festival.getId(), transientLineupDate);
        final FacesMessage msg = new FacesMessage("Der Lineup Eintrag wurde gespeichert");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteLineupDate(Long lineupId) {
        festival = festivalService.removeLineupDate(festival.getId(), lineupId);
        final FacesMessage msg = new FacesMessage("Der Lineup Eintrag wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String persistBuehne() {
        festival = festivalService.addBuehne(festival.getId(), transientBuehne);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public String deleteBuehne(Long buehneId) {
        festival = festivalService.removeBuehne(festival.getId(), buehneId);
        final FacesMessage msg = new FacesMessage("Die Bühne wurde gelöscht");
        FacesContext.getCurrentInstance().addMessage("buehnenForm", msg);
        initFields(festival.getId());
        return PageNames.CURRENT_PAGE;
    }

    public FestivalEntity getPersistedFestival() {
        return festival;
    }

    public LineupDateEntity getTransientLineupDate() {
        return transientLineupDate;
    }

    public List<BandEntity> getAvailableBands() {
        return availableBands;
    }

    public Long getSelectedBuehnenId() {
        return selectedBuehnenId;
    }

    public void setSelectedBuehnenId(Long selectedBuehnenId) {
        this.selectedBuehnenId = selectedBuehnenId;
    }

    public BuehneEntity getTransientBuehne() {
        return transientBuehne;
    }

    public Long getSelectedBandId() {
        return selectedBandId;
    }

    public void setSelectedBandId(Long selectedBandId) {
        this.selectedBandId = selectedBandId;
    }

    @DoLogging
    public void listenToUpdate(@Observes UpdateEvent updateEvent) {
        if (updateEvent.getClazz() == BandEntity.class) {
            availableBands = bandService.findAllBands();
        }
    }
}
