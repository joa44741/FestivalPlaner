/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import de.oth.joa44741.swprojektjohn.core.InfoArtEnum;
import de.oth.joa44741.swprojektjohn.core.ZusatzeigenschaftEnum;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "FestivalZusatzeigenschaften")
public class FestivalZusatzeigenschaftEntity extends AbstractLongEntity {

    @Column
    private String info;

    @Column
    @Enumerated(EnumType.STRING)
    private InfoArtEnum infoArt;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private ZusatzeigenschaftEnum zusatzeigenschaft;

    // validator für Zusatzeigenschaft infoErlaubt
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public InfoArtEnum getInfoArt() {
        return infoArt;
    }

    public void setInfoArt(InfoArtEnum infoArt) {
        this.infoArt = infoArt;
    }

    public ZusatzeigenschaftEnum getZusatzeigenschaft() {
        return zusatzeigenschaft;
    }

    public void setZusatzeigenschaft(ZusatzeigenschaftEnum zusatzeigenschaft) {
        this.zusatzeigenschaft = zusatzeigenschaft;
    }
}
