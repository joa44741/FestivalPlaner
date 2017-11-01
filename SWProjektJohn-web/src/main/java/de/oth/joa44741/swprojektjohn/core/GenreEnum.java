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
public enum GenreEnum {
    ALTERNATIVE("Alternative"),
    BLUES("Blues"),
    KLASSIK("Klassik"),
    COUNTRY("Country"),
    ELECTRONIC("Electronic (Techno, House,...)"),
    HIPHOP_RAP("Hip-Hop/Rap"),
    INDIE_POP("Indie Pop"),
    INSTRUMENTAL("Instrumental"),
    JAZZ("Jazz"),
    LATIN("Latin"),
    POP("Pop"),
    R_AND_B("R&B/Soul"),
    REGGAE("Reggae"),
    ROCK("Rock"),
    SINGER_SONGWRITER("Singer/Songwriter");

    private final String text;

    private GenreEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
