/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 *
 * @author Andi
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AbstractBaseDto {

    private final Long id;

    private final String creationDate;

    protected AbstractBaseDto() {
        this.id = null;
        this.creationDate = null;
    }

    protected AbstractBaseDto(Long id, String creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
