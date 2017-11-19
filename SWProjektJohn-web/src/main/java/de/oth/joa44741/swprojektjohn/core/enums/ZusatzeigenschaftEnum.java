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

    MUELLPFAND("Müllpfand"),
    ESSENSSTAENDE("Essensstände"),
    SANITAERE_ANLAGEN("Sanitäre Anlagen"),
    UNTER_18("Unter 18"),
    PARKMOEGLICHKEITEN("Parkmöglichkeiten");

    private final String text;

    private ZusatzeigenschaftEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
