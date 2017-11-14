/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.repository.BandRepository;
import java.util.Collections;
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
public class BandServiceImpl implements BandService {

    @Inject
    private BandRepository bandRepository;

    @Override
    public BandEntity retrieveBandById(Long id) {
        return bandRepository.retrieveBandById(id);
    }

    @Override
    public BandEntity retrieveBandByIdIncludingDetails(Long id) {
        return bandRepository.retrieveBandByIdIncludingDetails(id);
    }

    @Override
    public List<BandEntity> findAllBands() {
        return bandRepository.findAllBands();
    }

    @Override
    public List<BandEntity> findRandomBands() {
        final List<BandEntity> allBands = findAllBands();
        Collections.shuffle(allBands);
        return allBands;
    }

    @Override
    public BandEntity persistBand(BandEntity band) {
        return bandRepository.persistBand(band);
    }

    @Override
    public void removeBand(BandEntity band) {
        bandRepository.removeBand(band);
    }

}
