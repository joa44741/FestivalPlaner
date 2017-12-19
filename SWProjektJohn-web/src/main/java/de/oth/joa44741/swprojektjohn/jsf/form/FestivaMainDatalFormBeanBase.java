package de.oth.joa44741.swprojektjohn.jsf.form;

import de.oth.joa44741.swprojektjohn.core.enums.ZusatzeigenschaftEnum;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import de.oth.joa44741.swprojektjohn.services.LocationService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
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
public class FestivaMainDatalFormBeanBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected FestivalService festivalService;

    @Inject
    protected LocationService LocationService;

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
        final List<FacesMessage> errorMessages = new ArrayList<>();
        final Long verkaufteTickets = (Long) value;
        final UIInput ticketkontingentComponent = (UIInput) component.getAttributes().get("ticketkontingentComponent");
        final Long ticketKontingent = (Long) ticketkontingentComponent.getValue();

        validateVerkaufteTicketAnzahl(verkaufteTickets).ifPresent(msg -> errorMessages.add(msg));
        validateTicketKontingent(ticketKontingent).ifPresent(msg -> errorMessages.add(msg));
        validateVerkaufteTicketsNotGreaterThanTicketKontingent(verkaufteTickets, ticketKontingent).ifPresent(msg -> errorMessages.add(msg));

        if (!errorMessages.isEmpty()) {
            throw new ValidatorException(errorMessages);
        }
    }

    private Optional<FacesMessage> validateVerkaufteTicketAnzahl(Long verkaufteTickets) {
        if (verkaufteTickets != null && verkaufteTickets < 0) {
            final String messageText = "Nur Zahlen größer gleich 0 als Anzahl verkaufter Tickets angeben";
            final FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageText, messageText);
            return Optional.of(errorMessage);
        }
        return Optional.empty();
    }

    private Optional<FacesMessage> validateTicketKontingent(Long ticketKontingent) {
        if (ticketKontingent != null && ticketKontingent < 1) {
            final String messageText = "Nur Zahlen größer 0 als Ticketkontingent angeben";
            final FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageText, messageText);
            return Optional.of(errorMessage);
        }
        return Optional.empty();
    }

    private Optional<FacesMessage> validateVerkaufteTicketsNotGreaterThanTicketKontingent(Long verkaufteTickets, Long ticketKontingent) {
        boolean areBothValuesNotNull = verkaufteTickets != null && ticketKontingent != null;
        if (areBothValuesNotNull) {
            return doValidateVerkaufteTicketsNotGreaterThanTicketKontingent(verkaufteTickets, ticketKontingent);
        }
        return Optional.empty();
    }

    private Optional<FacesMessage> doValidateVerkaufteTicketsNotGreaterThanTicketKontingent(Long verkaufteTickets, Long ticketKontingent) {
        if (verkaufteTickets > ticketKontingent) {
            final String messageText = "Anzahl verkaufte Tickets darf nicht größer als Ticketkontingent sein";
            final FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageText, messageText);
            return Optional.of(errorMessage);
        }
        return Optional.empty();
    }

}
