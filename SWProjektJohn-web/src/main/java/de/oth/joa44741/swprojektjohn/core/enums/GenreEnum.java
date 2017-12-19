package de.oth.joa44741.swprojektjohn.core.enums;

/**
 *
 * @author Andreas John
 */
public enum GenreEnum {
    ALTERNATIVE("Alternative"),
    BLUES("Blues"),
    KLASSIK("Klassik"),
    COUNTRY("Country"),
    DIVERSE("Diverse"),
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
    SCHLAGER("Schlager"),
    SINGER_SONGWRITER("Singer/Songwriter");

    private final String text;

    private GenreEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
