package de.oth.joa44741.swprojektjohn.jsf.form.validator;

import de.oth.joa44741.swprojektjohn.services.FestivalService;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author Andreas John
 */
@RequestScoped
@FestivalNameValidatorQualifier
public class FestivalNameFormValidator implements FormValidator {

    @Inject
    private FestivalService festivalService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        final String festivalName = (String) value;
        festivalService.findFestivalByName(festivalName).ifPresent(festival -> {
            final String errorMessage = "Das Festival mit dem Namen " + festivalName + " exisitiert bereits. Das Festival hat den Status " + festival.getStatus() + " und muss gegebenfalls erst vom Administrator gelöscht werden.";
            final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            throw new ValidatorException(msg);
        });
    }

}
