package de.oth.joa44741.swprojektjohn.entity;

import de.oth.joa44741.swprojektjohn.core.RegexPattern;
import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.core.enums.ZusatzeigenschaftEnum;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andreas John
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Festivals")
@NamedQueries({
    @NamedQuery(name = FestivalEntity.QUERY_NAME_FIND_FESTIVALS_BY_STATUS, query = "SELECT f FROM FestivalEntity f where f.status IN :status")
    ,
    @NamedQuery(name = FestivalEntity.QUERY_NAME_FIND_FESTIVALS_IN_FUTURE_BY_STATUS, query = "SELECT f FROM FestivalEntity f where f.status IN :status and (f.datumVon >= CURRENT_DATE or f.datumVon < CURRENT_DATE and f.datumBis >= CURRENT_DATE) ORDER BY f.datumVon")
    ,
    @NamedQuery(name = FestivalEntity.QUERY_NAME_FIND_FESTIVAL_BY_NAME, query = "SELECT f FROM FestivalEntity f where f.name = :name")
    ,
    @NamedQuery(name = FestivalEntity.QUERY_NAME_RETRIEVE_FESTIVAL_BY_LINEUP_DATE_ID, query = "SELECT f FROM FestivalEntity f join f.lineupDates l where l.id = :lineupDateId")
    ,
    @NamedQuery(name = FestivalEntity.QUERY_NAME_RETRIEVE_FESTIVAL_BY_ID_INCLUDING_DETAILS, query = "SELECT t FROM FestivalEntity t "
            + "JOIN FETCH t.location l "
            + "LEFT JOIN FETCH t.zusatzeigenschaften z "
            + "LEFT JOIN FETCH t.ticketArten ta "
            + "LEFT JOIN FETCH t.buehnen b "
            + "LEFT JOIN FETCH t.lineupDates l "
            + "LEFT JOIN FETCH t.campingVarianten c "
            + "WHERE t.id = :id")
})
public class FestivalEntity extends AbstractLongEntity {

    public static final String QUERY_NAME_FIND_FESTIVALS_BY_STATUS = "findFestivalsByStatus";
    public static final String QUERY_NAME_FIND_FESTIVALS_IN_FUTURE_BY_STATUS = "findFestivalsInFutureByStatus";
    public static final String QUERY_NAME_FIND_FESTIVAL_BY_NAME = "findFestivalByName";
    public static final String QUERY_NAME_RETRIEVE_FESTIVAL_BY_LINEUP_DATE_ID = "retrieveFestivalByLineupDateId";
    public static final String QUERY_NAME_RETRIEVE_FESTIVAL_BY_ID_INCLUDING_DETAILS = "retrieveFestivalByIdIncludingDetails";

    @Column(nullable = false, unique = true)
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
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "locationId", referencedColumnName = "id", nullable = false)
    private LocationEntity location;

    @Column
    @Min(1)
    private Integer ticketKontingent;

    @Column
    @Min(0)
    private Integer verkaufteTickets;

    @Column(unique = true)
    @Pattern(regexp = RegexPattern.REGEX_URL)
    private String lageplan;

    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date datumVon;

    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date datumBis;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "FestivalsZusatzeigenschaften", joinColumns = @JoinColumn(
            name = "festivalId"))
    @Column(name = "zusatzeigenschaft", nullable = false)
    @Enumerated(EnumType.STRING)
    private final Set<ZusatzeigenschaftEnum> zusatzeigenschaften = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "festivalId", referencedColumnName = "id", nullable = false)
    @XmlTransient
    private final Set<CampingVarianteEntity> campingVarianten = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "festivalId", referencedColumnName = "id", nullable = false)
    @XmlTransient
    private final Set<TicketArtEntity> ticketArten = new HashSet<>();

    @OneToMany(mappedBy = "festival", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlTransient
    private final Set<BuehneEntity> buehnen = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "festivalId", referencedColumnName = "id", nullable = false)
    @XmlTransient
    private final Set<LineupDateEntity> lineupDates = new HashSet<>();

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

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

    public Set<TicketArtEntity> getTicketArten() {
        return Collections.unmodifiableSet(ticketArten);
    }

    public void addTicketArt(TicketArtEntity ticketArt) {
        this.ticketArten.add(ticketArt);
    }

    public boolean removeTicketArt(TicketArtEntity ticketArt) {
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
        this.lineupDates.stream().filter(lineup -> buehne.equals(lineup.getBuehne())).forEach(lineup -> lineup.setBuehne(null));
        return this.buehnen.remove(buehne);
    }

    public void clearBuehnen() {
        this.buehnen.clear();
    }

    public Set<LineupDateEntity> getLineupDates() {
        return Collections.unmodifiableSet(lineupDates);
    }

    public void addLineupDate(LineupDateEntity lineupDate) {
        this.lineupDates.add(lineupDate);
    }

    public boolean removeLineupDate(LineupDateEntity lineupDate) {
        return this.lineupDates.remove(lineupDate);
    }

    public void clearLineupDates() {
        this.lineupDates.clear();
    }

    public Integer getProzentAusverkauft() {
        if (verkaufteTickets == null || ticketKontingent == null) {
            return 0;
        }
        final BigDecimal verkaufteTicketsAsBigDecimal = BigDecimal.valueOf(verkaufteTickets);
        final BigDecimal ticketKontingentAsBigDecimal = BigDecimal.valueOf(ticketKontingent);
        return verkaufteTicketsAsBigDecimal.
                divide(ticketKontingentAsBigDecimal, 3, RoundingMode.HALF_UP).
                multiply(BigDecimal.valueOf(100)).
                intValue();
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
