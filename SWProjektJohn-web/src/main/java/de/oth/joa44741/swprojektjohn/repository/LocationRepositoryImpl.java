/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@RequestScoped
@Transactional
public class LocationRepositoryImpl extends RepositoryBaseImpl<LocationEntity> implements LocationRepository {

    private static final Logger LOG = Logger.getLogger(LocationRepositoryImpl.class);

    @Override
    public LocationEntity retrieveLocationById(Long id) {
        return retrieveById(id);
    }

    @Override
    public List<LocationEntity> findAllLocations() {
        return findAll();
    }

    @Override
    public LocationEntity retrieveLocationByFestivalId(Long festivalId) {
        final String sqlStatement = "SELECT l FROM FestivalEntity f join f.location l where f.id = :id";
        TypedQuery<LocationEntity> query = getEntityManager().createQuery(sqlStatement, LocationEntity.class);
        query.setParameter("id", festivalId);
        final LocationEntity location = query.getSingleResult();
        return location;
    }

}
