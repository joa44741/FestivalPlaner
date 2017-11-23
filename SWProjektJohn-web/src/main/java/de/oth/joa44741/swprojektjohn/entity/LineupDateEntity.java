/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "LineupDates")
public class LineupDateEntity extends AbstractLongEntity {

    @Column
    @Temporal(TemporalType.DATE)
    private Date tag;

    @Column
    @Temporal(TemporalType.TIME)
    private Date uhrzeitVon;

    @Column
    @Temporal(TemporalType.TIME)
    private Date uhrzeitBis;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bandId", referencedColumnName = "id", nullable = false)
    private BandEntity band;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "buehneId", referencedColumnName = "id")
    private BuehneEntity buehne;

    public Date getTag() {
        return tag;
    }

    public void setTag(Date tag) {
        this.tag = tag;
    }

    public Date getUhrzeitVon() {
        return uhrzeitVon;
    }

    public void setUhrzeitVon(Date uhrzeitVon) {
        this.uhrzeitVon = uhrzeitVon;
    }

    public Date getUhrzeitBis() {
        return uhrzeitBis;
    }

    public void setUhrzeitBis(Date uhrzeitBis) {
        this.uhrzeitBis = uhrzeitBis;
    }

    public BandEntity getBand() {
        return band;
    }

    public void setBand(BandEntity band) {
        this.band = band;
    }

    public BuehneEntity getBuehne() {
        return buehne;
    }

    public void setBuehne(BuehneEntity buehne) {
        this.buehne = buehne;
    }
}
