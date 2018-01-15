package de.oth.joa44741.swprojektjohn.jsf.form.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author Andreas John
 */
@Qualifier
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface VonBisDatumValidatorQualifier {

}
