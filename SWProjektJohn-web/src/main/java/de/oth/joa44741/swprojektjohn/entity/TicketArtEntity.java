/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import de.oth.joa44741.swprojektjohn.core.enums.TagArtEnum;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "TicketArten")
public class TicketArtEntity extends AbstractLongEntity {

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TagArtEnum tagArt;

    @Column
    @Temporal(TemporalType.DATE)
    private Date tag;

    @NotNull
    @Column(nullable = false)
    @DecimalMin("0.00")
    private BigDecimal preis;

    public TagArtEnum getTagArt() {
        return tagArt;
    }

    public void setTagArt(TagArtEnum tagArt) {
        this.tagArt = tagArt;
    }

    public Date getTag() {
        return tag;
    }

    public void setTag(Date tag) {
        this.tag = tag;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }
}
