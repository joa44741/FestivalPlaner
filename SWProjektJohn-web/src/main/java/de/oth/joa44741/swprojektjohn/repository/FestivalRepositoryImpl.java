/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.core.qualifier.FestivalRepository;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@RequestScoped
@Transactional
@FestivalRepository
public class FestivalRepositoryImpl extends RepositoryBaseImpl<FestivalEntity> {

    private static final Logger LOG = Logger.getLogger(FestivalRepositoryImpl.class);

    @Override
    public FestivalEntity persistEntity(FestivalEntity entity) {
        if (entity.getLocation().getId() != null) {
            final LocationEntity mergedLocationEntity = getEntityManager().find(LocationEntity.class, entity.getLocation().getId());
            entity.setLocation(mergedLocationEntity);
        }
        return super.persistEntity(entity);
    }

}
