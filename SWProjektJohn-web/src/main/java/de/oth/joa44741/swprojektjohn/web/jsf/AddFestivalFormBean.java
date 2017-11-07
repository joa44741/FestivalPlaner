/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.core.BundeslandEnum;
import de.oth.joa44741.swprojektjohn.core.StatusEnum;
import de.oth.joa44741.swprojektjohn.core.ZusatzeigenschaftEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@Named("addFestivalFormBean")
@SessionScoped
public class AddFestivalFormBean extends FestivalFormBeanBase {

    @Inject
    private FestivalOptionalDataFormBean festivalOptionalDataFormBean;

    private static final long serialVersionUID = 1L;

    private FestivalEntity transientFestival;
    private List<ZusatzeigenschaftEnum> selectedZusatzeigenschaftenList;

    private List<LocationEntity> availableLocations;

    private Long selectedLocationId;

    @PostConstruct
    @Override
    public void initFields() {
        super.initFields();
        transientFestival = new FestivalEntity();
        selectedLocationId = null;
        availableLocations = locationBusinessService.findAllLocations();
        transientFestival.setLocation(new LocationEntity());
        selectedZusatzeigenschaftenList = new ArrayList<>();
    }

    public FestivalEntity getTransientFestival() {
        return transientFestival;
    }

    public String persistMainFestivalData() {
        selectedZusatzeigenschaftenList.stream().forEach(z -> transientFestival.addZusatzeigenschaft(z));
        // TODO: if Admin --> FREIGEGEBEN
        transientFestival.setStatus(StatusEnum.ERSTELLT);
        final FestivalEntity persistedFestival = festivalBusinessService.persistFestival(transientFestival);
        final FacesMessage msg = new FacesMessage("Festival " + persistedFestival.getName() + " erfolgreich angelegt");
        FacesContext.getCurrentInstance().addMessage("newFormular", msg);
        initFields();
        return festivalOptionalDataFormBean.loadAndShowPage(persistedFestival.getId());
    }

    public void validateFestivalName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        final String festivalName = (String) value;
        festivalBusinessService.findFestivalByName(festivalName).ifPresent(festival -> {
            final String errorMessage = "Das Festival mit dem Namen " + festivalName + " exisitiert bereits. Das Festival hat den Status " + festival.getStatus() + " und muss gegebenfalls erst vom Administrator gelöscht werden.";
            final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            throw new ValidatorException(msg);
        });
    }

    public List<BundeslandEnum> getBundeslaenderAsList() {
        return Arrays.asList(BundeslandEnum.values());
    }

    public List<LocationEntity> getAvailableLocations() {
        return availableLocations;
    }

    public Long getSelectedLocationId() {
        return selectedLocationId;
    }

    public void setSelectedLocationId(Long existingLocationId) {
        this.selectedLocationId = existingLocationId;
    }

    public void updateLocationFields() {
        if (selectedLocationId == null) {
            this.transientFestival.setLocation(new LocationEntity());
        } else {
            final LocationEntity existingLocation = this.availableLocations.stream().filter(loc -> loc.getId().equals(selectedLocationId)).findAny().get();
            this.transientFestival.setLocation(existingLocation);
        }
    }

    public void validateVonBisDatum(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        final UIInput vonDatumComponent = (UIInput) component.getAttributes().get("datumVonComponent");
        final Date vonDatum = (Date) vonDatumComponent.getValue();
        final Date bisDatum = (Date) value;
        if (bisDatum.before(vonDatum)) {
            String msg = "Von Datum muss zeitlich vor dem Bis Datum liegen";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }

}
