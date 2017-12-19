package de.oth.joa44741.swprojektjohn.core.enums;

/**
 *
 * @author Andreas John
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
