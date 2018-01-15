package de.oth.joa44741.swprojektjohn.jsf.form.validator;

import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author Andreas John
 */
@RequestScoped
@VonBisDatumValidatorQualifier
public class VonBisDatumFormValidator implements FormValidator {

    @Inject
    private FestivalService festivalService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        final UIInput vonDatumComponent = (UIInput) component.getAttributes().get("datumVonComponent");
        final Date vonDatum = (Date) vonDatumComponent.getValue();
        final Date bisDatum = (Date) value;
        if (bisDatum.before(vonDatum)) {
            final String msg = "Von Datum muss zeitlich vor dem Bis Datum liegen";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }

}
