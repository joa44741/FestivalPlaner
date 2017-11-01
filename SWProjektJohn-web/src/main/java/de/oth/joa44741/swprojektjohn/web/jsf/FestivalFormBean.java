/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.bservice.LocationBusinessService;
import de.oth.joa44741.swprojektjohn.core.BundeslandEnum;
import de.oth.joa44741.swprojektjohn.core.TagArtEnum;
import de.oth.joa44741.swprojektjohn.core.ZusatzeigenschaftEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivalFormBean")
@SessionScoped
public class FestivalFormBean implements Serializable {

    @Inject
    private FestivalBusinessService festivalBusinessService;

    @Inject
    private LocationBusinessService locationBusinessService;

    @Inject
    private FestivalOptionalDataFormBean festivalOptionalDataFormBean;

    private static final long serialVersionUID = 1L;

    private FestivalEntity transientFestival;
    private List<ZusatzeigenschaftEnum> selectedZusatzeigenschaftenList;

    private TagArtEnum selectedTagArt;

    private TicketArtEntity transientTicketArt;

    private List<LocationEntity> availableLocations;

    private Long existingLocationId;

    @PostConstruct
    public void initFields() {
        transientFestival = new FestivalEntity();
        existingLocationId = null;
        transientTicketArt = new TicketArtEntity();
        availableLocations = locationBusinessService.findAllLocations();
        transientFestival.setLocation(new LocationEntity());
        selectedZusatzeigenschaftenList = new ArrayList<>();
    }

    public FestivalEntity getTransientFestival() {
        return transientFestival;
    }

    public String persistMainFestivalData() {
        selectedZusatzeigenschaftenList.stream().forEach(z -> transientFestival.addZusatzeigenschaft(z));
        System.out.println(transientFestival);
        FestivalEntity persistedFestival = festivalBusinessService.persistFestival(transientFestival);
        final FacesMessage msg = new FacesMessage("Festival " + persistedFestival.getName() + " erfolgreich angelegt");
        FacesContext.getCurrentInstance().addMessage("newFormular", msg);
        initFields();
        return festivalOptionalDataFormBean.loadAndShowPage(persistedFestival.getId());
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

    public List<BundeslandEnum> getBundeslaenderAsList() {
        return Arrays.asList(BundeslandEnum.values());
    }

    public TagArtEnum getSelectedTagArt() {
        return selectedTagArt;
    }

    public void setSelectedTagArt(TagArtEnum selectedTagArt) {
        this.selectedTagArt = selectedTagArt;
    }

    public TicketArtEntity getTransientTicketArt() {
        return transientTicketArt;
    }

    public String persistTicketArt() {
        if (transientTicketArt.getPreis() != null) {
            this.transientFestival.addTicketArt(transientTicketArt);
            festivalBusinessService.addTicketArt(transientFestival.getId(), transientTicketArt);
            System.out.println("added TicketArt: " + transientTicketArt);
            transientTicketArt = new TicketArtEntity();
        }
        return PageNames.CURRENT_PAGE;
    }

    public List<LocationEntity> getAvailableLocations() {
        return availableLocations;
    }

    public Long getExistingLocationId() {
        return existingLocationId;
    }

    public void setExistingLocationId(Long existingLocationId) {
        System.out.println("setting " + existingLocationId);
        this.existingLocationId = existingLocationId;
    }

    public void updateLocationFields() {
        if (existingLocationId == null) {
            this.transientFestival.setLocation(new LocationEntity());
        } else {
            final LocationEntity existingLocation = this.availableLocations.stream().filter(loc -> loc.getId() == existingLocationId).findAny().get();
            this.transientFestival.setLocation(existingLocation);
        }
    }

    public void validateVonBisDatum(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        final UIInput vonDatumComponent = (UIInput) component.getAttributes().get("datumVonComponent");
        final Date vonDatum = (Date) vonDatumComponent.getValue();
        final Date bisDatum = (Date) value;
        if (bisDatum.before(vonDatum)) {
            String msg = "Von Datum muss zeitlich vor dem Bis Datum liegen";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
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
