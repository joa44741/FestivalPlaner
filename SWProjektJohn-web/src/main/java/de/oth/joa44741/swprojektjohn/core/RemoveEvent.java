/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.core;

/**
 *
 * @author Johnny
 */
public class RemoveEvent {

    private final Class clazz;
    private final Long id;

    public RemoveEvent(Long id, Class clazz) {
        this.id = id;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public Long getId() {
        return id;
    }
}
