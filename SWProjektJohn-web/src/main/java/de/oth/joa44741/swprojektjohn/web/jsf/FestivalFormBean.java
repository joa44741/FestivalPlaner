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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivalFormBean")
@ViewScoped
public class FestivalFormBean implements Serializable {

    @Inject
    private FestivalBusinessService festivalBusinessService;

    @Inject
    private LocationBusinessService locationBusinessService;

    private static final long serialVersionUID = 1L;

    private FestivalEntity transientFestival;
    private List<ZusatzeigenschaftEnum> selectedZusatzeigenschaftenList;

    private List<TicketArtEntity> transientTicketArten;

    private TagArtEnum selectedTagArt;

    private TicketArtEntity transientTicketArt;

    private List<LocationEntity> availableLocations;

    @PostConstruct
    public void initFields() {
        transientFestival = new FestivalEntity();
        transientTicketArten = new ArrayList<>();
        transientTicketArt = new TicketArtEntity();
        availableLocations = locationBusinessService.findAllLocations();
        transientFestival.setLocation(new LocationEntity());
    }

    public FestivalEntity getTransientFestival() {
        return transientFestival;
    }

    public void persist() {
        selectedZusatzeigenschaftenList.stream().forEach(z -> transientFestival.addZusatzeigenschaft(z));
        System.out.println(transientFestival);
        transientTicketArten.forEach(ticket -> transientFestival.addTicketArt(ticket));
        festivalBusinessService.persistFestival(transientFestival);
        final FacesMessage msg = new FacesMessage("Festival " + transientFestival.getName() + " erfolgreich angelegt");
        FacesContext.getCurrentInstance().addMessage("newFormular", msg);
        initFields();
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

    public List<TagArtEnum> getTagArtenAsList() {
        return Arrays.asList(TagArtEnum.values());
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

    public String addTransientTicketToFestival() {
        festivalBusinessService.test();
        final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ticket Art konnte nicht angelegt werden", "test");
        FacesContext.getCurrentInstance().addMessage("newFestival", msg);
        if (transientTicketArt.getPreis() != null) {
            this.transientTicketArten.add(transientTicketArt);
            System.out.println("added TicketArt: " + transientTicketArt);
            transientTicketArt = new TicketArtEntity();
        }
        return PageNames.CURRENT_PAGE;
    }

    public List<TicketArtEntity> getTransientTicketArten() {
        return transientTicketArten;
    }

    public List<LocationEntity> getAvailableLocations() {
        return availableLocations;
    }

}
