package de.oth.joa44741.swprojektjohn.jsf.form;

import de.oth.joa44741.swprojektjohn.core.enums.ZusatzeigenschaftEnum;
import de.oth.joa44741.swprojektjohn.jsf.form.validator.FormValidatorFactory;
import de.oth.joa44741.swprojektjohn.jsf.form.validator.VerkaufteTicketsAndKontingentValidatorQualifier;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import de.oth.joa44741.swprojektjohn.services.LocationService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author Andreas John
 */
public class FestivaMainDataFormBeanBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected FestivalService festivalService;

    @Inject
    protected LocationService LocationService;

    @Inject
    protected FormValidatorFactory formValidatorFactory;

    private List<ZusatzeigenschaftEnum> selectedZusatzeigenschaftenList;

    public void initFields() {
        selectedZusatzeigenschaftenList = new ArrayList<>();
    }

    public List<ZusatzeigenschaftEnum> getSelectedZusatzeigenschaftenList() {
        return selectedZusatzeigenschaftenList;
    }

    public void setSelectedZusatzeigenschaftenList(List<ZusatzeigenschaftEnum> selectedZusatzeigenschaftenList) {
        this.selectedZusatzeigenschaftenList = selectedZusatzeigenschaftenList;
    }

    public Map<String, ZusatzeigenschaftEnum> getZusatzeigenschaftenAsMap() {
        final Map<String, ZusatzeigenschaftEnum> map = Arrays.stream(ZusatzeigenschaftEnum.values())
                .collect(Collectors.toMap(ZusatzeigenschaftEnum::getText, Function.identity()));
        return map;
    }

    public void validateVerkaufteTicketsAndKontingent(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        formValidatorFactory.getValidator(VerkaufteTicketsAndKontingentValidatorQualifier.class).validate(context, component, value);
    }

}
