/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.BandEntity;
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
public class BandRepositoryImpl extends RepositoryBaseImpl<BandEntity> implements BandRepository {

    private static final Logger LOG = Logger.getLogger(BandRepositoryImpl.class);

    @Override
    public BandEntity retrieveBandById(Long id) {
        return retrieveById(id);
    }

    @Override
    public BandEntity retrieveBandByIdIncludingDetails(Long id) {
        final String statement = "SELECT t FROM BandEntity t "
                + "LEFT JOIN FETCH t.lineupDates l "
                + "WHERE t.id = :id";
        final TypedQuery<BandEntity> query = getEntityManager().createQuery(statement, BandEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<BandEntity> findAllBands() {
        return findAll();
    }

    @Override
    public BandEntity persistBand(BandEntity entity) {
        return persistEntity(entity);
    }

    @Override
    public void removeBand(BandEntity band) {
        getEntityManager().remove(band);
    }

}
