/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.webservice;

import de.oth.joa44741.swprojektjohn.bservice.BandBusinessService;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebService;

/**
 *
 * @author Johnny
 */
@RequestScoped
@WebService
public class BandService {

    @Inject
    private BandBusinessService bandBusinessService;

    public BandEntity retrieveBandByIdIncludingDetails(Long id) {
        return bandBusinessService.retrieveBandByIdIncludingDetails(id);
    }

    public List<BandEntity> findAllBands() {
        return bandBusinessService.findAllBands();
    }

}
