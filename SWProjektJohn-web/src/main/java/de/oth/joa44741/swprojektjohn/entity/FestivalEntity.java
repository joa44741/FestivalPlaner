/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.oth.joa44741.swprojektjohn.core.RegexPattern;
import de.oth.joa44741.swprojektjohn.core.ZusatzeigenschaftEnum;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @Column(nullable = false, unique = true)
    @Basic(optional = false)
    @NotNull
    private String name;

    @Column
    private String veranstalter;

    @Column(unique = true)
    @Pattern(regexp = RegexPattern.REGEX_URL)
    private String webseite;

    @Column(unique = true)
    @Pattern(regexp = RegexPattern.REGEX_URL)
    private String logoUrl;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "locationId", referencedColumnName = "id", nullable = false)
    private LocationEntity location;

//    @NotNull
//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "festivalDefinitionId", referencedColumnName = "id", nullable = false)
//    private FestivalDefinitionEntity festivalDefinition;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "FestivalsZusatzeigenschaften", joinColumns = @JoinColumn(
            name = "festivalId"))
    @Column(name = "zusatzeigenschaft", nullable = false)
    @Enumerated(EnumType.STRING)
    private final Set<ZusatzeigenschaftEnum> zusatzeigenschaften = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "festivalId", referencedColumnName = "id", nullable = false)
    private final Set<CampingVarianteEntity> campingVarianten = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "festivalId", referencedColumnName = "id", nullable = false)
    private final Set<TicketArtenEntity> ticketArten = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "festivalId", referencedColumnName = "id", nullable = false)
    private final Set<BuehneEntity> buehnen = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVeranstalter() {
        return veranstalter;
    }

    public void setVeranstalter(String veranstalter) {
        this.veranstalter = veranstalter;
    }

    public String getWebseite() {
        return webseite;
    }

    public void setWebseite(String webseite) {
        this.webseite = webseite;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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

    public Set<ZusatzeigenschaftEnum> getZusatzeigenschaften() {
        return Collections.unmodifiableSet(zusatzeigenschaften);
    }

    public void addZusatzeigenschaft(ZusatzeigenschaftEnum zusatzeigenschaft) {
        this.zusatzeigenschaften.add(zusatzeigenschaft);
    }

    public boolean removeZusatzeigenschaft(ZusatzeigenschaftEnum zusatzeigenschaft) {
        return this.zusatzeigenschaften.remove(zusatzeigenschaft);
    }

    public void clearZusatzeigenschaften() {
        this.zusatzeigenschaften.clear();
    }

    public Set<CampingVarianteEntity> getCampingVarianten() {
        return Collections.unmodifiableSet(campingVarianten);
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

    public Set<TicketArtenEntity> getTicketArten() {
        return Collections.unmodifiableSet(ticketArten);
    }

    public void addTicketArt(TicketArtenEntity ticketArt) {
        this.ticketArten.add(ticketArt);
    }

    public boolean removeTicketArt(TicketArtenEntity ticketArt) {
        return this.ticketArten.remove(ticketArt);
    }

    public void clearTicketArten() {
        this.ticketArten.clear();
    }

    public Set<BuehneEntity> getBuehnen() {
        return Collections.unmodifiableSet(buehnen);
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

    public Integer getProzentAusverkauft() {
        final BigDecimal verkaufteTicketsAsBigDecimal = BigDecimal.valueOf(verkaufteTickets);
        final BigDecimal ticketKontingentAsBigDecimal = BigDecimal.valueOf(ticketKontingent);
        return verkaufteTicketsAsBigDecimal.
                divide(ticketKontingentAsBigDecimal).
                multiply(BigDecimal.valueOf(100)).
                intValue();
    }
}
