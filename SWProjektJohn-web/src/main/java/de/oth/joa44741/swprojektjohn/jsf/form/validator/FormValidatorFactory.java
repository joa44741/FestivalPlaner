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

    @Inject
    @VonBisDatumValidatorQualifier
    private FormValidator vonBisDatumFormValidator;

    @Inject
    @FestivalNameValidatorQualifier
    private FormValidator festivalNameFormValidator;

    @Inject
    @VerkaufteTicketsAndKontingentValidatorQualifier
    private FormValidator verkaufteTicketsAndKontingentFormValidator;

    private Map<Class, FormValidator> qualifierToValidatorMap;

    @PostConstruct
    private void initFactoryEntries() {
        qualifierToValidatorMap = new HashMap<>();
        qualifierToValidatorMap.put(VonBisDatumValidatorQualifier.class, vonBisDatumFormValidator);
        qualifierToValidatorMap.put(FestivalNameValidatorQualifier.class, festivalNameFormValidator);
        qualifierToValidatorMap.put(VerkaufteTicketsAndKontingentValidatorQualifier.class, verkaufteTicketsAndKontingentFormValidator);
    }

    public FormValidator getValidator(Class validatorQualifierClass) {
        return qualifierToValidatorMap.get(validatorQualifierClass);
    }
}
