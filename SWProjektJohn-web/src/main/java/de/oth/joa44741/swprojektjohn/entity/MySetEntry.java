package de.oth.joa44741.swprojektjohn.entity;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Andreas John
 */
public class MySetEntry<T extends AbstractLongEntity> {

    @XmlAttribute
    private final T entity;

    private final String output;

    public MySetEntry(T entity) {
        this.entity = entity;
        this.output = entity.toString();
    }

    public T getEntity() {
        return entity;
    }

}
