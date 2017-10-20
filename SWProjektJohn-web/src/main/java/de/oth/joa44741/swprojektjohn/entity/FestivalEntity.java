/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andi
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Festivals")
public class FestivalEntity extends AbstractLongEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "locationId", referencedColumnName = "id", nullable = false)
    private LocationEntity location;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "festivalDefinitionId", referencedColumnName = "id", nullable = false)
    private FestivalDefinitionEntity festivalDefinition;

    @Column
    @Min(1)
    private Integer ticketKontingent;

    // evtl. von Eventim holen
    @Column
    @Min(0)
    private Integer verkaufteTickets;

    // in Tage
    @Column
    @Min(1)
    private Integer dauer;

    // pdf... als file oder link
    @Column
    private String lageplan;

    @Column
    @Temporal(TemporalType.DATE)
    private Date datumVon;

    @Column
    @Temporal(TemporalType.DATE)
    private Date datumBis;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "zusatzeigenschaftId", referencedColumnName = "id", nullable = false)
    private final List<FestivalZusatzeigenschaftEntity> zusatzeigenschaften = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "campingvarianteId", referencedColumnName = "id", nullable = false)
    private final List<CampingVarianteEntity> campingVarianten = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "buehneId", referencedColumnName = "id", nullable = false)
    private final List<BuehneEntity> buehnen = new ArrayList<>();

    /*
    @Enumerated(EnumType.STRING)
    @CollectionTable
    private List<ZusatzeigenschaftEnum> zusatzeigenschaften;
     */
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "festivals_zusatzeigenschaften", joinColumns = @JoinColumn(
//            name = "ngaipbasisnetzmultidtypmitvarsubnetzgroesse_id"))
//    @Column(name = "ngadiensttyp", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private final List<ZusatzeigenschaftEnum> zusatzeigenschaften = new ArrayList();
    public FestivalDefinitionEntity getFestivalDefinition() {
        return festivalDefinition;
    }

    public void setFestivalDefinition(FestivalDefinitionEntity festivalDefinition) {
        this.festivalDefinition = festivalDefinition;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
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

    public List<FestivalZusatzeigenschaftEntity> getZusatzeigenschaften() {
        return Collections.unmodifiableList(zusatzeigenschaften);
    }

    public void addZusatzeigenschaft(FestivalZusatzeigenschaftEntity zusatzeigenschaft) {
        this.zusatzeigenschaften.add(zusatzeigenschaft);
    }

    public boolean removeZusatzeigenschaft(FestivalZusatzeigenschaftEntity zusatzeigenschaft) {
        return this.zusatzeigenschaften.remove(zusatzeigenschaft);
    }

    public void clearZusatzeigenschaften() {
        this.zusatzeigenschaften.clear();
    }

    public List<CampingVarianteEntity> getCampingVarianten() {
        return Collections.unmodifiableList(campingVarianten);
    }

    public void addCampingVariante(CampingVarianteEntity campingVariante) {
        this.campingVarianten.add(campingVariante);
    }

    public boolean removeCampingVariante(CampingVarianteEntity campingVariante) {
        return this.campingVarianten.remove(campingVariante);
    }

    public void clearCampingVarianten() {
        this.campingVarianten.clear();
    }

    public List<BuehneEntity> getBuehnen() {
        return Collections.unmodifiableList(buehnen);
    }

    public void addBuehne(BuehneEntity buehne) {
        this.buehnen.add(buehne);
    }

    public boolean removeBuehne(BuehneEntity buehne) {
        return this.buehnen.remove(buehne);
    }

    public void clearBuehnen() {
        this.buehnen.clear();
    }
}
