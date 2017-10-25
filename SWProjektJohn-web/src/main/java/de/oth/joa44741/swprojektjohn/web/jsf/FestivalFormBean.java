/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.core.TagArtEnum;
import de.oth.joa44741.swprojektjohn.core.ZusatzeigenschaftEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("festivalFormBean")
@RequestScoped
public class FestivalFormBean implements Serializable {

    @Inject
    private FestivalBusinessService festivalBusinessService;

    private static final long serialVersionUID = 1L;

    private FestivalEntity transientFestival;
    private List<ZusatzeigenschaftEnum> selectedZusatzeigenschaftenList;

    private TagArtEnum selectedTagArt;

    private TicketArtEntity transientTicketArt;

    @PostConstruct
    public void initFields() {
        transientFestival = new FestivalEntity();
        transientTicketArt = new TicketArtEntity();
    }

    public FestivalEntity getTransientFestival() {
        return transientFestival;
    }

    public void persist() {
        selectedZusatzeigenschaftenList.stream().forEach(z -> transientFestival.addZusatzeigenschaft(z));
        System.out.println(transientFestival);
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
        this.transientFestival.addTicketArt(transientTicketArt);
        System.out.println("added TicketArt: " + transientTicketArt);
        transientFestival = null;
        return PageNames.CURRENT_PAGE;
    }
}
