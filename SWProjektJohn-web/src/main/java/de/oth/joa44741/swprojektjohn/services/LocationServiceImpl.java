/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.core.logging.DoLogging;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import de.oth.joa44741.swprojektjohn.repository.LocationRepository;
import static de.oth.joa44741.swprojektjohn.repository.QueryParam.with;
import de.oth.joa44741.swprojektjohn.repository.Repository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Johnny
 */
@RequestScoped
@DoLogging
public class LocationServiceImpl implements LocationService {

    @Inject
    private LocationRepository locationRepository;

    @Override
    public LocationEntity retrieveLocationById(Long id) {
        return locationRepository.retrieveById(id);
    }

    @Override
    public List<LocationEntity> findAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public LocationEntity retrieveLocationByFestivalId(Long festivalId) {
        final List<LocationEntity> locations = this.locationRepository
                .query(LocationEntity.QUERY_NAME_RETRIEVE_LOCATION_BY_FESTIVAL_ID,
                        with("id", festivalId).parameters(), Repository.SINGLE_RESULT);
        return locations.get(0);
    }

}
