/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@RequestScoped
@Transactional
public class BuehneRepositoryImpl extends RepositoryBaseImpl<BuehneEntity> implements BuehneRepository {

    private static final Logger LOG = Logger.getLogger(BuehneRepositoryImpl.class);

    @Override
    public BuehneEntity retrieveBuehneById(Long id) {
        return retrieveById(id);
    }

    @Override
    public BuehneEntity updateBuehne(BuehneEntity buehne) {
        BuehneEntity updatedEntity = getEntityManager().merge(buehne);
        return persistEntity(updatedEntity);
    }

}
