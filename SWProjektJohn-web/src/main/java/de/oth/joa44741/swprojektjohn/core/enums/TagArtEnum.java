package de.oth.joa44741.swprojektjohn.core.enums;

/**
 *
 * @author Andreas John
 */
public enum TagArtEnum {
    KOMPLETT_TICKET("Komplett-Ticket"),
    TAGESTICKET("Tagesticket"),
    ZWEI_TAGE_TICKET("2-Tage-Ticket"),
    DREI_TAGE_TICKET("3-Tage-Ticket"),
    VIER_TAGE_TICKET("4-Tage-Ticket"),
    FUENF_TAGE_TICKET("5-Tage-Ticket");

    private final String text;

    private TagArtEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
