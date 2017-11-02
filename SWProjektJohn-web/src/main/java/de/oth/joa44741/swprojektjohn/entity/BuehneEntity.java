/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "Buehnen")
public class BuehneEntity extends AbstractLongEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "buehneId", referencedColumnName = "id", nullable = false)
    private final List<LineupDateEntity> lineupDates = new ArrayList<>();

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "festivalId", referencedColumnName = "id", nullable = false)
    private FestivalEntity festival;

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

    public List<LineupDateEntity> getLineupDates() {
        return Collections.unmodifiableList(lineupDates);
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
