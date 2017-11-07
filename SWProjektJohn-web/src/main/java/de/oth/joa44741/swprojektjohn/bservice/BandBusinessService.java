/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import java.util.List;

/**
 *
 * @author Andi
 */
public interface BandBusinessService {

    BandEntity retrieveBandById(Long id);

    BandEntity retrieveBandByIdIncludingDetails(Long id);

    List<BandEntity> findAllBands();

    BandEntity persistBand(BandEntity entity);

    void removeBand(Long id);

}
