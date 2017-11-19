/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.core.qualifier.BandRepository;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import static de.oth.joa44741.swprojektjohn.repository.QueryParam.with;
import de.oth.joa44741.swprojektjohn.repository.Repository;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Johnny
 */
@RequestScoped
public class BandServiceImpl implements BandService {

    @Inject
    @BandRepository
    private Repository<BandEntity> bandRepository;

    @Override
    public BandEntity retrieveBandById(Long id) {
        return bandRepository.retrieveById(id);
    }

    @Override
    public BandEntity retrieveBandByIdIncludingDetails(Long id) {
        final List<BandEntity> bands = this.bandRepository
                .query(BandEntity.QUERY_NAME_RETRIEVE_BAND_BY_ID_INCLUDING_DETAILS,
                        with("id", id).parameters(), Repository.SINGLE_RESULT);
        return bands.get(0);
    }

    @Override
    public List<BandEntity> findAllBands() {
        return bandRepository.findAll();
    }

    @Override
    public List<BandEntity> findRandomBands() {
        final List<BandEntity> allBands = findAllBands();
        Collections.shuffle(allBands);
        return allBands;
    }

    @Override
    public BandEntity persistBand(BandEntity band) {
        return bandRepository.persistEntity(band);
    }

    @Override
    public void removeBand(Long id) {
        bandRepository.remove(id);
    }

}
