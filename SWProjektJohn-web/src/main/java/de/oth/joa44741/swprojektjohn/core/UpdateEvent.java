package de.oth.joa44741.swprojektjohn.core;

/**
 *
 * @author Andreas John
 */
public class UpdateEvent {

    private final Class clazz;

    public UpdateEvent(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
