/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.core.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
public class FestivalRepositoryImpl extends RepositoryBaseImpl<FestivalEntity> implements FestivalRepository {

    private static final Logger LOG = Logger.getLogger(FestivalRepositoryImpl.class);

    @Override
    public FestivalEntity retrieveFestivalById(Long id) {
        return retrieveById(id);
    }

    @Override
    public Optional<FestivalEntity> findFestivalByName(String name) {
        final TypedQuery query = getEntityManager().createNamedQuery(FestivalEntity.QUERY_NAME_FIND_FESTIVAL_BY_NAME, FestivalEntity.class);
        query.setParameter("name", name);
        return getOptionalSingleResult(query);
    }

    @Override
    public FestivalEntity retrieveFestivalByIdIncludingDetails(Long id) {
        final TypedQuery<FestivalEntity> query = getEntityManager()
                .createNamedQuery(FestivalEntity.QUERY_NAME_RETRIEVE_FESTIVAL_BY_ID_INCLUDING_DETAILS, FestivalEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public FestivalEntity persistFestival(FestivalEntity entity) {
        if (entity.getLocation().getId() != null) {
            final LocationEntity mergedLocationEntity = getEntityManager().merge(entity.getLocation());
            entity.setLocation(mergedLocationEntity);
        }
        return persistEntity(entity);
    }

    @Override
    public FestivalEntity updateFestival(FestivalEntity entity) {
        FestivalEntity updatedEntity = getEntityManager().merge(entity);
        return persistEntity(updatedEntity);
    }

    @Override
    public List<FestivalEntity> findAllFestivals() {
        return findAll();
    }

    @Override
    public List<FestivalEntity> findAllFestivalsByStatus(StatusEnum... status) {
        final TypedQuery query = getEntityManager().createNamedQuery(FestivalEntity.QUERY_NAME_FIND_FESTIVALS_BY_STATUS, FestivalEntity.class);
        query.setParameter("status", Arrays.asList(status));
        return query.getResultList();
    }

    @Override
    public List<FestivalEntity> findAllFestivalsInFutureByStatus(StatusEnum... status) {
        final TypedQuery query = getEntityManager().createNamedQuery(FestivalEntity.QUERY_NAME_FIND_FESTIVALS_IN_FUTURE_BY_STATUS, FestivalEntity.class);
        query.setParameter("status", Arrays.asList(status));
        return query.getResultList();
    }

    @Override
    public FestivalEntity retrieveFestivalByLineupDateId(Long lineupDateId) {
        final TypedQuery<FestivalEntity> query = getEntityManager()
                .createNamedQuery(FestivalEntity.QUERY_NAME_RETRIEVE_FESTIVAL_BY_LINEUP_DATE_ID, FestivalEntity.class);
        query.setParameter("lineupDateId", lineupDateId);
        return query.getSingleResult();
    }

    @Override
    public void removeFestival(FestivalEntity festival) {
        getEntityManager().remove(festival);
    }

}
