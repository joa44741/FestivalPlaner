/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.List;

public interface LocationService {

    LocationEntity retrieveLocationById(Long id);

    List<LocationEntity> findAllLocations();

    LocationEntity retrieveLocationByFestivalId(Long festivalId);
}
