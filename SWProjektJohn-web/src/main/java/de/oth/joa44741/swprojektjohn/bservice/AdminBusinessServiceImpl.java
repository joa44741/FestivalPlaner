/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;

/**
 *
 * @author Andi
 */
@RequestScoped
@Transactional
public class AdminBusinessServiceImpl extends BusinessServiceBaseImpl<BandEntity> implements AdminBusinessService {

    private static final Logger LOG = Logger.getLogger(AdminBusinessServiceImpl.class);

    @Override
    public boolean isAdmin(String username, String password) {
        return true;
    }

}
