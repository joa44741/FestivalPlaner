/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

/**
 * This class needs the 'extends' keyword with the specific type
 * 'FestivalEntity' for the generic super class. There is only one special case
 * when persisting an entity. That's why this method overrides the method of the
 * super class. The location can already exist therefore it has to be merged to
 * get managed by the entity manager.
 *
 * @author Andreas John
 */
@RequestScoped
@Transactional
public class FestivalRepositoryImpl extends RepositoryBaseImpl<FestivalEntity> implements FestivalRepository {

    @Override
    public FestivalEntity persistEntity(FestivalEntity entity) {
        if (entity.getLocation().getId() != null) {
            final LocationEntity mergedLocationEntity = getEntityManager().find(LocationEntity.class, entity.getLocation().getId());
            entity.setLocation(mergedLocationEntity);
        }
        return super.persistEntity(entity);
    }

}
