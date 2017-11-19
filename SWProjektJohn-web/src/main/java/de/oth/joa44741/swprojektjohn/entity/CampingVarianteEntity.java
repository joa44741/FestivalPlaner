/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import de.oth.joa44741.swprojektjohn.core.enums.CampingArtEnum;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
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
@Table(name = "CampingVarianten")
public class CampingVarianteEntity extends AbstractLongEntity {

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CampingArtEnum campingArt;

    @NotNull
    @Column(nullable = false)
    @DecimalMin("0.00")
    private BigDecimal extrakosten;

    @Column
    private Boolean grillenErlaubt;

    @Column
    private Boolean glasflaschenErlaubt;

    public CampingArtEnum getCampingArt() {
        return campingArt;
    }

    public void setCampingArt(CampingArtEnum campingArt) {
        this.campingArt = campingArt;
    }

    public BigDecimal getExtrakosten() {
        return extrakosten;
    }

    public void setExtrakosten(BigDecimal extrakosten) {
        this.extrakosten = extrakosten;
    }

    public Boolean getGrillenErlaubt() {
        return grillenErlaubt;
    }

    public void setGrillenErlaubt(Boolean grillenErlaubt) {
        this.grillenErlaubt = grillenErlaubt;
    }

    public Boolean getGlasflaschenErlaubt() {
        return glasflaschenErlaubt;
    }

    public void setGlasflaschenErlaubt(Boolean glasflaschenErlaubt) {
        this.glasflaschenErlaubt = glasflaschenErlaubt;
    }

}
