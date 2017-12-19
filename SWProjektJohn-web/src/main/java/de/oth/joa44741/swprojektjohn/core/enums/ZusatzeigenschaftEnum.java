package de.oth.joa44741.swprojektjohn.core.enums;

/**
 *
 * @author Andreas John
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
