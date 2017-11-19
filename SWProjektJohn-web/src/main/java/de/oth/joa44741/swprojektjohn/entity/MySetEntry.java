/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Johnny
 */
public class MySetEntry<T extends AbstractLongEntity> {

    @XmlAttribute
    private final T entity;

    private final String output;

    public MySetEntry(T entity) {
        this.entity = entity;
        this.output = entity.toString();
    }

    public T getEntity() {
        return entity;
    }

}
