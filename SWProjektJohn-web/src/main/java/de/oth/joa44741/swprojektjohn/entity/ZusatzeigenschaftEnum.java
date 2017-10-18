/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

/**
 *
 * @author Andi
 */
public enum ZusatzeigenschaftEnum {

    MUELLPFAND(true, "Müllpfand"),
    ESSENSSTAENDE(true, "Essensstände"),
    SANITAERE_ANLAGEN(true, "Sanitäre Anlagen"),
    UNTER_18(false, "Unter 18"),
    AUFSICHTSZETTEL(true, "Aufsichtszettel"),
    PARKMOEGLICHKEITEN(true, "Parkmöglichkeiten");

    private final boolean zusatzinfoErlaubt;
    private final String text;

    private ZusatzeigenschaftEnum(boolean zusatzinfoErlaubt, String text) {
        this.zusatzinfoErlaubt = zusatzinfoErlaubt;
        this.text = text;
    }

    public boolean getZusatzinfoErlaubt() {
        return this.zusatzinfoErlaubt;
    }

    public String getText() {
        return text;
    }
}
