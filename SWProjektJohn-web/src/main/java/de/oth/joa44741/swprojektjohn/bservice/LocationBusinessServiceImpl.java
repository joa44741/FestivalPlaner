/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.List;
import javax.ejb.Stateless;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@Stateless
public class LocationBusinessServiceImpl extends AbstractBusinessServiceBase<LocationEntity> implements LocationBusinessService {

    private static final Logger LOG = Logger.getLogger(LocationBusinessServiceImpl.class);

    @Override
    public LocationEntity retrieveLocationById(Long id) {
        return retrieveById(id);
    }

    @Override
    public List<LocationEntity> findAllLocations() {
        return findAll();
    }

}
