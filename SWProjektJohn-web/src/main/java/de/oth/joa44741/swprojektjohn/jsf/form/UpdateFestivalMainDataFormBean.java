package de.oth.joa44741.swprojektjohn.jsf.form;

import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import de.oth.joa44741.swprojektjohn.jsf.PageNames;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Andreas John
 */
@Named("updateFestivalFormBean")
@SessionScoped
public class UpdateFestivalMainDataFormBean extends FestivaMainDatalFormBeanBase {

    private static final long serialVersionUID = 1L;

    private Long selectedFestivalId;
    private FestivalEntity selectedFestival;

    private List<FestivalEntity> existingFestivals;

    // only read access
    private LocationEntity locationOfFestivalToUpdate;

    @Inject
    private CampingAndTicketFormBean campingAndTicketFormBean;

    @Inject
    private LineupFormBean lineupFormBean;

    @PostConstruct
    @Override
    public void initFields() {
        super.initFields();
        existingFestivals = festivalService.findFestivals();
        selectedFestival = new FestivalEntity();
        selectedFestivalId = null;
        locationOfFestivalToUpdate = new LocationEntity();
    }

    public String loadAndShowPage() {
        initFields();
        return PageNames.EDIT_MAIN_FESTIVAL_DATA;
    }

    public List<FestivalEntity> getExistingFestivals() {
        return existingFestivals;
    }

    public Long getSelectedFestivalId() {
        return selectedFestivalId;
    }

    public void setSelectedFestivalId(Long selectedFestivalId) {
        this.selectedFestivalId = selectedFestivalId;
    }

    public void updateFestivalFields() {
        if (selectedFestivalId == null) {
            this.selectedFestival = new FestivalEntity();
            this.locationOfFestivalToUpdate = new LocationEntity();
            setSelectedZusatzeigenschaftenList(Collections.EMPTY_LIST);
        } else {
            this.selectedFestival = this.existingFestivals.stream().filter(f -> f.getId().equals(selectedFestivalId)).findFirst().get();
            // needed because of lazy implementation
            this.locationOfFestivalToUpdate = this.LocationService.retrieveLocationByFestivalId(selectedFestival.getId());
            setSelectedZusatzeigenschaftenList(new ArrayList(this.selectedFestival.getZusatzeigenschaften()));
        }
    }

    public FestivalEntity getSelectedFestival() {
        return selectedFestival;
    }

    public LocationEntity getLocationOfFestivalToUpdate() {
        return locationOfFestivalToUpdate;
    }

    public String updateMainFestivalData() {
        selectedFestival.clearZusatzeigenschaften();
        getSelectedZusatzeigenschaftenList().stream().forEach(z -> selectedFestival.addZusatzeigenschaft(z));

        FestivalEntity persistedFestival = festivalService.updateFestival(selectedFestival);
        final FacesMessage msg = new FacesMessage("Festival " + persistedFestival.getName() + " erfolgreich upgedated");
        FacesContext.getCurrentInstance().addMessage("updateFormular", msg);
        return PageNames.CURRENT_PAGE;
    }

    public String loadAndShowTicketAndCampingPage() {
        return campingAndTicketFormBean.loadAndShowTicketsAndCampingPage(selectedFestival.getId());
    }

    public String loadAndShowLineupPage() {
        return lineupFormBean.loadAndShowLineupPage(selectedFestival.getId());
    }

    public String setStatusOfFestivalToLoeschungAngefordert() {
        final FestivalEntity festivalToDelete = this.existingFestivals.stream().filter(f -> f.getId().equals(selectedFestivalId)).findFirst().get();
        festivalToDelete.setStatus(StatusEnum.LOESCHUNG_ANGEFORDERT);
        festivalService.updateFestival(festivalToDelete);
        final FacesMessage msg = new FacesMessage("Löschung für Festival " + festivalToDelete.getName() + " wurde beantragt");
        FacesContext.getCurrentInstance().addMessage("updateFormular", msg);
        return PageNames.INDEX;
    }
}
