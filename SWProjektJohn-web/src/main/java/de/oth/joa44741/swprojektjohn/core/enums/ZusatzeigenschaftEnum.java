/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.core.enums;

/**
 *
 * @author Andi
 */
public enum ZusatzeigenschaftEnum {

    MUELLPFAND("M�llpfand"),
    ESSENSSTAENDE("Essensst�nde"),
    SANITAERE_ANLAGEN("Sanit�re Anlagen"),
    UNTER_18("Unter 18"),
    PARKMOEGLICHKEITEN("Parkm�glichkeiten");

    private final String text;

    private ZusatzeigenschaftEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
