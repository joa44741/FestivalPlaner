/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.core;

/**
 *
 * @author Andi
 */
public enum BundeslandEnum {
    BADEN_WUERTTEMBERG("Baden-Württemberg"),
    BAYERN("Bayern"),
    BERLIN("Berlin"),
    BRANDENBURG("Brandenburg"),
    BREMEN("Bremen"),
    HAMBURG("Hamburg"),
    HESSEN("Hessen"),
    MECKLENBURG_VORPOMMERN("Mecklenburg-Vorpommern"),
    NIEDERSACHSEN("Niedersachsen"),
    NORDRHEIN_WESTFALEN("Nordrhein-Westfalen"),
    RHEINLAND_PFALZ("Rheinland-Pfalz"),
    SAARLAND("Saarland"),
    SACHSEN("Sachsen"),
    SACHSEN_ANHALT("Sachsen-Anhalt"),
    SCHLESWIG_HOLSTEIN("Schleswig-Holstein"),
    THUERINGEN("Thüringen");

    private final String text;

    private BundeslandEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
