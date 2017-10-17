/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import de.oth.joa44741.swprojektjohn.core.RegexPattern;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Table(name = "FestivalDefinition")
public class FestivalDefinitionEntity extends AbstractLongEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @NotNull
    private String name;

//    @Column(nullable = true)
    private String veranstalter;

//    @Column(nullable = true)
    @Pattern(regexp = RegexPattern.REGEX_URL)
    private String webseite;

//    @Column(nullable = true)
    @Pattern(regexp = RegexPattern.REGEX_URL)
    private String logoUrl;

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
}
