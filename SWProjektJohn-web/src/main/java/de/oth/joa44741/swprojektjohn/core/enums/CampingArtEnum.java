/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.core.enums;

/**
 *
 * @author Johnny
 */
public enum CampingArtEnum {
    NORMAL("Normal"),
    PREMIUM("Premium"),
    GREENCAMPING("Greencamping"),
    WOHNMOBIL("Wohnmobil");

    private final String text;

    private CampingArtEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
