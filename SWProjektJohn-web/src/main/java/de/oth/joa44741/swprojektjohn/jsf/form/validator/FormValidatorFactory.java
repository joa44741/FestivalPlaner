package de.oth.joa44741.swprojektjohn.jsf.form.validator;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Andreas John
 */
@RequestScoped
public class FormValidatorFactory {

    public enum ValidatorType {
        VON_BIS_DATUM_VALIDATOR,
        FESTIVAL_NAME_VALIDATOR,
        VERKAUFTE_TICKETS_AND_KONTINGENT_VALIDATOR;
    }

    @Inject
    @VonBisDatumValidatorQualifier
    private FormValidator vonBisDatumFormValidator;

    @Inject
    @FestivalNameValidatorQualifier
    private FormValidator festivalNameFormValidator;

    @Inject
    @VerkaufteTicketsAndKontingentValidatorQualifier
    private FormValidator verkaufteTicketsAndKontingentFormValidator;

    private Map<ValidatorType, FormValidator> qualifierToValidatorMap;

    @PostConstruct
    private void initFactoryEntries() {
        qualifierToValidatorMap = new HashMap<>();
        qualifierToValidatorMap.put(ValidatorType.VON_BIS_DATUM_VALIDATOR, vonBisDatumFormValidator);
        qualifierToValidatorMap.put(ValidatorType.FESTIVAL_NAME_VALIDATOR, festivalNameFormValidator);
        qualifierToValidatorMap.put(ValidatorType.VERKAUFTE_TICKETS_AND_KONTINGENT_VALIDATOR, verkaufteTicketsAndKontingentFormValidator);
    }

    public FormValidator getValidator(ValidatorType validatorType) {
        return qualifierToValidatorMap.get(validatorType);
    }
}
