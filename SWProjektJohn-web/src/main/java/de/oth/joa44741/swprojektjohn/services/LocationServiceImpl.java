/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import de.oth.joa44741.swprojektjohn.repository.LocationRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Johnny
 */
@RequestScoped
public class LocationServiceImpl implements LocationService {

    @Inject
    private LocationRepository locationRepository;

    @Override
    public LocationEntity retrieveLocationById(Long id) {
        return locationRepository.retrieveLocationById(id);
    }

    @Override
    public List<LocationEntity> findAllLocations() {
        return locationRepository.findAllLocations();
    }

    @Override
    public LocationEntity retrieveLocationByFestivalId(Long festivalId) {
        return locationRepository.retrieveLocationByFestivalId(festivalId);
    }

}
