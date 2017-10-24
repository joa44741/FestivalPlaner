/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@Stateless
public class FestivalBusinessServiceImpl extends AbstractBusinessServiceBase<FestivalEntity> implements FestivalBusinessService {

    private static final Logger LOG = Logger.getLogger(FestivalBusinessServiceImpl.class);

    @Override
    public FestivalEntity retrieveFestivalById(Long id) {
        return retrieveById(id);
    }

    @Override
    public FestivalEntity retrieveFestivalByIdIncludingDetails(Long id) {
        final String statement = "SELECT t FROM " + FestivalEntity.class.getSimpleName() + " t JOIN FETCH t.location l LEFT JOIN FETCH t.buehnen WHERE t.id = :id";
        final TypedQuery<FestivalEntity> query = getEntityManager().createQuery(statement, FestivalEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public FestivalEntity persistFestival(FestivalEntity entity) {
        return persistEntity(entity);
    }

    @Override
    public List<FestivalEntity> findAllFestivals() {
        return findAll();
    }
}
