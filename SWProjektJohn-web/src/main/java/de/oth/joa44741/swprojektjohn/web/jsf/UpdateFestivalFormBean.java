/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.core.ZusatzeigenschaftEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("updateFestivalFormBean")
@SessionScoped
public class UpdateFestivalFormBean extends FestivalFormBeanBase {

    private static final long serialVersionUID = 1L;

    private List<ZusatzeigenschaftEnum> selectedZusatzeigenschaftenList;

    private Long selectedFestivalId;
    private FestivalEntity selectedFestival;

    private List<FestivalEntity> existingFestivals;

    // only read access
    private LocationEntity locationOfFestivalToUpdate;

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
            this.locationOfFestivalToUpdate = this.locationBusinessService.retrieveLocationByFestival(selectedFestival);
        }
    }

    public FestivalEntity getSelectedFestival() {
        return selectedFestival;
    }

    public LocationEntity getLocationOfFestivalToUpdate() {
        return locationOfFestivalToUpdate;
    }

    public String updateMainFestivalData() {
        FestivalEntity persistedFestival = festivalBusinessService.persistFestival(selectedFestival);
        final FacesMessage msg = new FacesMessage("Festival " + persistedFestival.getName() + " erfolgreich upgedated");
        FacesContext.getCurrentInstance().addMessage("updateFormular", msg);
        return PageNames.CURRENT_PAGE;
    }
}
