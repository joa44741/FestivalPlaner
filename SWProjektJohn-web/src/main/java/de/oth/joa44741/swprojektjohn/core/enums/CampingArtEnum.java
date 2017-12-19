package de.oth.joa44741.swprojektjohn.core.enums;

/**
 *
 * @author Andreas John
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
