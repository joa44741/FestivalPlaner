/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.List;

/**
 *
 * @author Andi
 */
public interface LocationBusinessService {

    LocationEntity retrieveLocationById(Long id);

    List<LocationEntity> findAllLocations();

    LocationEntity retrieveLocationByFestival(FestivalEntity festival);

}
