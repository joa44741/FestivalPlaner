/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import de.oth.joa44741.swprojektjohn.core.RegexPattern;
import de.oth.joa44741.swprojektjohn.core.enums.GenreEnum;
import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andi
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = BandEntity.QUERY_NAME_RETRIEVE_BAND_BY_ID_INCLUDING_DETAILS, query = "SELECT t FROM BandEntity t "
            + "LEFT JOIN FETCH t.lineupDates l "
            + "WHERE t.id = :id")
    ,
    @NamedQuery(name = BandEntity.QUERY_NAME_FIND_BANDS_BY_STATUS, query = "SELECT t FROM BandEntity t where t.status IN :status")
})
@Entity
@Table(name = "Bands")
public class BandEntity extends AbstractLongEntity {

    public static final String QUERY_NAME_RETRIEVE_BAND_BY_ID_INCLUDING_DETAILS = "retrieveBandByIdIncludingDetails";
    public static final String QUERY_NAME_FIND_BANDS_BY_STATUS = "findBandsByStatus";

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column
    private String beschreibung;

    @Column
    @Pattern(regexp = RegexPattern.REGEX_URL)
    private String webseite;

    @Column
    @Pattern(regexp = RegexPattern.REGEX_URL)
    private String facebookseite;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "BandsGenres", joinColumns = @JoinColumn(
            name = "bandId"))
    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private final List<GenreEnum> genres = new ArrayList<>();

    @OneToMany(mappedBy = "band", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getWebseite() {
        return webseite;
    }

    public void setWebseite(String webseite) {
        this.webseite = webseite;
    }

    public String getFacebookseite() {
        return facebookseite;
    }

    public void setFacebookseite(String facebookseite) {
        this.facebookseite = facebookseite;
    }

    public List<GenreEnum> getGenres() {
        return Collections.unmodifiableList(genres);
    }

    public void addGenre(GenreEnum genre) {
        this.genres.add(genre);
    }

    public boolean removeGenre(GenreEnum genre) {
        return this.genres.remove(genre);
    }

    public void clearGenres() {
        this.genres.clear();
    }

    public String getGenresAsString() {
        final String resultString = this.genres.stream()
                .map(genre -> genre.getText())
                .collect(Collectors.joining(", "));
        return resultString;
    }

    public Set<LineupDateEntity> getLineupDates() {
        return Collections.unmodifiableSet(lineupDates);
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

}
