/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import javax.validation.ConstraintViolationException;
import org.junit.Test;

/**
 *
 * @author Andi
 */
public class LocationBusinessServiceImplTest extends BusinessServiceImplTestBase {

    @Test
    public void testPersistEntity_success() throws Exception {
        begin();
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setBundesland(BundeslandEnum.BAYERN);
        locationEntity.setName("Zeppelinfeld");
        locationEntity.setOrt("Nürnberg");
        locationEntity.setPlz("75309");
        locationEntity.setStrasse("Abc-Str. 3");
        persist(locationEntity);
        commit();
    }

    @Test(expected = ConstraintViolationException.class)
    public void testPersistEntity_fail_noOrt() throws Exception {
        begin();
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setBundesland(BundeslandEnum.BAYERN);
        locationEntity.setName("Zeppelinfeld");
        locationEntity.setPlz("75309");
        locationEntity.setOrt(null);
        locationEntity.setStrasse("Abc-Str. 3");
        persist(locationEntity);
        commit();
    }

}
