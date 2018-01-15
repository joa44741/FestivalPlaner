package de.oth.joa44741.swprojektjohn.jsf.form.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andreas John
 */
public interface FormValidator {

    void validate(FacesContext context, UIComponent component, Object value);

}
