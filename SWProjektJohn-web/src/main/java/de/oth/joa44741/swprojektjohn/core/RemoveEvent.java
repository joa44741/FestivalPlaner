package de.oth.joa44741.swprojektjohn.core;

/**
 *
 * @author Andreas John
 */
public class RemoveEvent {

    private final Class clazz;
    private final Long id;

    public RemoveEvent(Long id, Class clazz) {
        this.id = id;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public Long getId() {
        return id;
    }
}
