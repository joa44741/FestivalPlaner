/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf.form;

import de.oth.joa44741.swprojektjohn.core.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import de.oth.joa44741.swprojektjohn.web.jsf.util.PageNames;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("updateFestivalFormBean")
@SessionScoped
public class UpdateFestivalFormBean extends FestivalFormBeanBase {

    private static final long serialVersionUID = 1L;

    private Long selectedFestivalId;
    private FestivalEntity selectedFestival;

    private List<FestivalEntity> existingFestivals;

    // only read access
    private LocationEntity locationOfFestivalToUpdate;

    @Inject
    private FestivalOptionalDataFormBean festivalOptionalDataFormBean;

    @PostConstruct
    @Override
    public void initFields() {
        super.initFields();
        existingFestivals = festivalBusinessService.findAllFestivals();
        selectedFestival = new FestivalEntity();
        locationOfFestivalToUpdate = new LocationEntity();
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
        } else {
            this.selectedFestival = this.existingFestivals.stream().filter(f -> f.getId().equals(selectedFestivalId)).findFirst().get();
            // needed because of lazy implementation
            this.locationOfFestivalToUpdate = this.locationBusinessService.retrieveLocationByFestivalId(selectedFestival.getId());
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

        FestivalEntity persistedFestival = festivalBusinessService.updateFestival(selectedFestival);
        final FacesMessage msg = new FacesMessage("Festival " + persistedFestival.getName() + " erfolgreich upgedated");
        FacesContext.getCurrentInstance().addMessage("updateFormular", msg);
        return PageNames.CURRENT_PAGE;
    }

    public String loadAndShowTicketAndCampingPage() {
        return festivalOptionalDataFormBean.loadAndShowTicketsAndCampingPage(selectedFestival.getId());
    }

    public String loadAndShowLineupPage() {
        return festivalOptionalDataFormBean.loadAndShowLineupPage(selectedFestival.getId());
    }

    public String setStatusOfFestivalToLoeschungAngefordert() {
        final FestivalEntity festivalToDelete = this.existingFestivals.stream().filter(f -> f.getId().equals(selectedFestivalId)).findFirst().get();
        festivalToDelete.setStatus(StatusEnum.LOESCHUNG_ANGEFORDERT);
        festivalBusinessService.updateFestival(festivalToDelete);
        final FacesMessage msg = new FacesMessage("L�schung f�r Festival " + festivalToDelete.getName() + " wurde beantragt");
        FacesContext.getCurrentInstance().addMessage("updateFormular", msg);
        return PageNames.INDEX;
    }
}
