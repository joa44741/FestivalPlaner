/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import java.util.List;
import javax.ejb.Stateless;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@Stateless
public class BandBusinessServiceImpl extends AbstractBusinessServiceBase<BandEntity> implements BandBusinessService {

    private static final Logger LOG = Logger.getLogger(BandBusinessServiceImpl.class);

    @Override
    public BandEntity retrieveBandById(Long id) {
        return retrieveById(id);
    }

    @Override
    public List<BandEntity> findAllBands() {
        return findAll();
    }

    @Override
    public BandEntity persistBand(BandEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeBand(Long id) {
        final BandEntity band = retrieveById(id);
        getEntityManager().remove(band);
    }

}
