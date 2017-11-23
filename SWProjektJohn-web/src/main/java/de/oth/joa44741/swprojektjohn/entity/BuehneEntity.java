/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = BuehneEntity.QUERY_NAME_FIND_BUEHNEN_BY_FESTIVAL_ID, query = "SELECT b FROM FestivalEntity f join f.lineupDates l join l.buehne b where f.id = :festivalId"),})
@Entity
@Table(name = "Buehnen")
public class BuehneEntity extends AbstractLongEntity {

    public static final String QUERY_NAME_FIND_BUEHNEN_BY_FESTIVAL_ID = "findBuehnenByFestivalId";

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "festivalId", referencedColumnName = "id", nullable = false)
    @XmlTransient
    private FestivalEntity festival;

    @OneToMany(mappedBy = "buehne", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @XmlTransient
    private final Set<LineupDateEntity> lineupDates = new HashSet();

    @Column
    private Boolean ueberdacht;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getUeberdacht() {
        return ueberdacht;
    }

    public void setUeberdacht(Boolean ueberdacht) {
        this.ueberdacht = ueberdacht;
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

    public FestivalEntity getFestival() {
        return festival;
    }

    public void setFestival(FestivalEntity festival) {
        this.festival = festival;
    }
}
