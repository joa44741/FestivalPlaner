/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import java.util.List;

public interface BandService {

    BandEntity retrieveBandById(Long id);

    BandEntity retrieveBandByIdIncludingDetails(Long id);

    List<BandEntity> findAllBands();

    List<BandEntity> findRandomBands();

    BandEntity persistBand(BandEntity band);

    void removeBand(Long Id);

}
