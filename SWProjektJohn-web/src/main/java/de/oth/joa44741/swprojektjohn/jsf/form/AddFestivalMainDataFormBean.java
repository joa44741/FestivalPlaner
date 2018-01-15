package de.oth.joa44741.swprojektjohn.jsf.form;

import de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum;
import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import de.oth.joa44741.swprojektjohn.jsf.LoginBean;
import de.oth.joa44741.swprojektjohn.jsf.form.validator.FestivalNameValidatorQualifier;
import de.oth.joa44741.swprojektjohn.jsf.form.validator.VonBisDatumValidatorQualifier;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Andreas John
 */
@Named("addFestivalFormBean")
@SessionScoped
public class AddFestivalMainDataFormBean extends FestivaMainDataFormBeanBase {

    @Inject
    private CampingAndTicketFormBean campingAndTicketFormBean;

    @Inject
    private LoginBean loginBean;

    private static final long serialVersionUID = 1L;

    private FestivalEntity transientFestival;

    private List<LocationEntity> availableLocations;

    private Long selectedLocationId;

    @PostConstruct
    @Override
    public void initFields() {
        super.initFields();
        transientFestival = new FestivalEntity();
        selectedLocationId = null;
        availableLocations = LocationService.findAllLocations();
        transientFestival.setLocation(new LocationEntity());
    }

    public FestivalEntity getTransientFestival() {
        return transientFestival;
    }

    public String persistMainFestivalData() {
        getSelectedZusatzeigenschaftenList().stream().forEach(z -> transientFestival.addZusatzeigenschaft(z));
        final StatusEnum newStatus = loginBean.isAdminLoggedIn() ? StatusEnum.FREIGEGEBEN : StatusEnum.ERSTELLT;
        transientFestival.setStatus(newStatus);
        final FestivalEntity persistedFestival = festivalService.createFestival(transientFestival);
        final FacesMessage msg = new FacesMessage("Festival " + persistedFestival.getName() + " erfolgreich angelegt");
        FacesContext.getCurrentInstance().addMessage("newFormular", msg);
        initFields();
        return campingAndTicketFormBean.loadAndShowTicketsAndCampingPage(persistedFestival.getId());
    }

    public void validateFestivalName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        formValidatorFactory.getValidator(FestivalNameValidatorQualifier.class).validate(context, component, value);
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
        formValidatorFactory.getValidator(VonBisDatumValidatorQualifier.class).validate(context, component, value);
    }

}
