package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

/**
 *
 * @author Andreas John
 */
@RequestScoped
@Transactional
public class LocationRepositoryImpl extends RepositoryBaseImpl<LocationEntity> implements LocationRepository {

}
