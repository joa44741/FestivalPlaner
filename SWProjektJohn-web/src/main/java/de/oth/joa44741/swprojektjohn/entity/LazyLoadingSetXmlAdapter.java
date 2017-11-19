/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.entity;

import java.util.Set;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.hibernate.collection.internal.PersistentSet;

/**
 *
 * @author Johnny
 */
public class LazyLoadingSetXmlAdapter extends XmlAdapter<Set<? super AbstractLongEntity>, Set<? super AbstractLongEntity>> {

    @Override
    public Set<? super AbstractLongEntity> marshal(Set<? super AbstractLongEntity> value) throws Exception {
//        JAXBContext jaxbContext = JAXBContext.newInstance(value.getClass());
//        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//        StringWriter writer = new StringWriter();
//        // output pretty printed
//        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        if (value instanceof PersistentSet) {
            PersistentSet hibernateCollection = (PersistentSet) value;
            if (hibernateCollection.wasInitialized()) {
//                jaxbContext = JAXBContext.newInstance(MyMappedSet.class);
//                jaxbMarshaller = jaxbContext.createMarshaller();
//                MyMappedSet mySet = new MyMappedSet(value);
//                jaxbMarshaller.marshal(mySet, writer);
                return value;
            } else {
                return null;
            }
        } else {
            return value;
        }
    }

    @Override
    public Set<? super AbstractLongEntity> unmarshal(Set<? super AbstractLongEntity> value) throws Exception {
        return value;
//        JAXBContext jaxbContext = JAXBContext.newInstance(value.getClass());
//        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//        StringWriter writer = new StringWriter();
//        StringReader reader = new StringReader(value);
//
//        Set<? super AbstractLongEntity> unmarshal = (Set<? super AbstractLongEntity>) jaxbUnmarshaller.unmarshal(reader);
//        return unmarshal;
//        Set<? super AbstractLongEntity> lazySet = new HashSet<>();
//
//        for (Object entry : value.getMappedSet()) {
//            MySetEntry mySetEntry = (MySetEntry) entry;
//            lazySet.add(mySetEntry.getEntity());
//        }
//        return lazySet;
    }

}
