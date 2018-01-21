package de.oth.joa44741.swprojektjohn.jsf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * This converter is only needed because it didn't work to pass a timeZone to
 * the <f:convertDateTime>-Tag in the xhtml file for a selectOneMenu widget.
 *
 * @author Andreas John
 *
 */
@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter {

    private static final String TEXT_FOR_NULL_VALUE = "-- select --";

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return TEXT_FOR_NULL_VALUE;
        } else if (value instanceof Date) {
            return new SimpleDateFormat("dd.MM.yyyy").format(value);
        } else {
            throw new ConverterException(new FacesMessage("Value is not a date: " + value.getClass()));
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (TEXT_FOR_NULL_VALUE.equals(value)) {
            return null;
        }
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(value);
        } catch (ParseException e) {
            throw new ConverterException(new FacesMessage("Value is not a date: " + value));
        }
    }

}
