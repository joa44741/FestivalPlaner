/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.bservice.FestivalBusinessService;
import de.oth.joa44741.swprojektjohn.core.ZusatzeigenschaftEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import java.io.Serializable;
import java.util.Arrays;
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

    private ZusatzeigenschaftEnum selectedZusatzeigenschaften[];

    @PostConstruct
    public void initFields() {
        transientFestival = new FestivalEntity();
    }

    public FestivalEntity getTransientFestival() {
        return transientFestival;
    }

    public void persist() {
        Arrays.stream(selectedZusatzeigenschaften).forEach(z -> transientFestival.addZusatzeigenschaft(z));
        System.out.println(transientFestival);
        final FacesMessage msg = new FacesMessage("Festival " + transientFestival.getName() + " erfolgreich angelegt");
        FacesContext.getCurrentInstance().addMessage("newFormular", msg);
        transientFestival = new FestivalEntity();
    }

    public Map<String, ZusatzeigenschaftEnum> getZusatzeigenschaftenAsMap() {
        final Map<String, ZusatzeigenschaftEnum> zusatzeigenschaftenMap = Arrays.stream(ZusatzeigenschaftEnum.values())
                .collect(Collectors.toMap(ZusatzeigenschaftEnum::getText, Function.identity()));
        return zusatzeigenschaftenMap;
    }

    public void setSelectedZusatzeigenschaften(ZusatzeigenschaftEnum[] selectedZusatzeigenschaften) {
        this.selectedZusatzeigenschaften = selectedZusatzeigenschaften;
    }

    public ZusatzeigenschaftEnum[] getSelectedZusatzeigenschaften() {
        return selectedZusatzeigenschaften;
    }
}
