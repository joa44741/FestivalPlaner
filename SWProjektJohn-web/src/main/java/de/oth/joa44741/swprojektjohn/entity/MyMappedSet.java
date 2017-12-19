package de.oth.joa44741.swprojektjohn.entity;

import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andreas John
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MyMappedSet<T extends AbstractLongEntity> {

//    private final Set<MySetEntry<T>> mappedSet = new HashSet<>();
    private Set<T> mappedSet;

    public MyMappedSet() {

    }

    public MyMappedSet(Set<T> entities) {
        mappedSet = entities;
//        entities.forEach(entry -> mappedSet.add(new MySetEntry(entry)));
    }

//    public Set<MySetEntry<T>> getMappedSet() {
//        return mappedSet;
//    }
    public Set<T> getMappedSet() {
        return mappedSet;
    }

}
