/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.core.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andi
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FestivalDto extends AbstractBaseDto {

    private LocationDto location;

    private FestivalDefinitionDto festivalDefinition;

    private Integer ticketKontingent;

    private Integer verkaufteTickets;

    private FestivalDto() {
        this.location = null;
        this.festivalDefinition = null;
    }

    public FestivalDto(Long id, String creationDate, LocationDto location, FestivalDefinitionDto festivalDefinition) {
        this.location = location;
        this.festivalDefinition = festivalDefinition;
    }

    private Integer dauer;

    private String lageplan;

    private Date datumVon;

    private Date datumBis;

    private final List<FestivalZusatzeigenschaftDto> zusatzeigenschaften = new ArrayList<>();

    private final List<CampingVarianteDto> campingVarianten = new ArrayList<>();

    private final List<BuehneDto> buehnen = new ArrayList<>();

    public FestivalDefinitionDto getFestivalDefinition() {
        return festivalDefinition;
    }

    public void setFestivalDefinition(FestivalDefinitionDto festivalDefinition) {
        this.festivalDefinition = festivalDefinition;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public Integer getTicketKontingent() {
        return ticketKontingent;
    }

    public void setTicketKontingent(Integer ticketKontingent) {
        this.ticketKontingent = ticketKontingent;
    }

    public Integer getVerkaufteTickets() {
        return verkaufteTickets;
    }

    public void setVerkaufteTickets(Integer verkaufteTickets) {
        this.verkaufteTickets = verkaufteTickets;
    }

    public Integer getDauer() {
        return dauer;
    }

    public void setDauer(Integer dauer) {
        this.dauer = dauer;
    }

    public Date getDatumVon() {
        return datumVon;
    }

    public void setDatumVon(Date datumVon) {
        this.datumVon = datumVon;
    }

    public Date getDatumBis() {
        return datumBis;
    }

    public void setDatumBis(Date datumBis) {
        this.datumBis = datumBis;
    }

    public String getLageplan() {
        return lageplan;
    }

    public void setLageplan(String lageplan) {
        this.lageplan = lageplan;
    }

//    public List<FestivalZusatzeigenschaftEntity> getZusatzeigenschaften() {
//        return Collections.unmodifiableList(zusatzeigenschaften);
//    }
//
//    public void addZusatzeigenschaft(FestivalZusatzeigenschaftEntity zusatzeigenschaft) {
//        this.zusatzeigenschaften.add(zusatzeigenschaft);
//    }
//
//    public boolean removeZusatzeigenschaft(FestivalZusatzeigenschaftEntity zusatzeigenschaft) {
//        return this.zusatzeigenschaften.remove(zusatzeigenschaft);
//    }
//
//    public void clearZusatzeigenschaften() {
//        this.zusatzeigenschaften.clear();
//    }
//
//    public List<CampingVarianteEntity> getCampingVarianten() {
//        return Collections.unmodifiableList(campingVarianten);
//    }
//
//    public void addCampingVariante(CampingVarianteEntity campingVariante) {
//        this.campingVarianten.add(campingVariante);
//    }
//
//    public boolean removeCampingVariante(CampingVarianteEntity campingVariante) {
//        return this.campingVarianten.remove(campingVariante);
//    }
//
//    public void clearCampingVarianten() {
//        this.campingVarianten.clear();
//    }
//
//    public List<BuehneEntity> getBuehnen() {
//        return Collections.unmodifiableList(buehnen);
//    }
//
//    public void addBuehne(BuehneEntity buehne) {
//        this.buehnen.add(buehne);
//    }
//
//    public boolean removeBuehne(BuehneEntity buehne) {
//        return this.buehnen.remove(buehne);
//    }
//
//    public void clearBuehnen() {
//        this.buehnen.clear();
//    }
}
