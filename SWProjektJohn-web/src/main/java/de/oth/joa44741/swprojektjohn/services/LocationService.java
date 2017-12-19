package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.List;

/**
 *
 * @author Andreas John
 */
public interface LocationService {

    LocationEntity retrieveLocationById(Long id);

    List<LocationEntity> findAllLocations();

    LocationEntity retrieveLocationByFestivalId(Long festivalId);
}
