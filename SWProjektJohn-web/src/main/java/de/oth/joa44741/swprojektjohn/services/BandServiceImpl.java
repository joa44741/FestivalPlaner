/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.core.qualifier.BandRepository;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import static de.oth.joa44741.swprojektjohn.repository.QueryParam.with;
import de.oth.joa44741.swprojektjohn.repository.Repository;
import java.util.Arrays;
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
    public List<BandEntity> findBandsByStatus(StatusEnum... status) {
        final List<BandEntity> allBands = this.bandRepository
                .query(BandEntity.QUERY_NAME_FIND_BANDS_BY_STATUS,
                        with("status", Arrays.asList(status)).parameters(), Repository.NO_LIMIT);
        return allBands;
    }

    @Override
    public List<BandEntity> findRandomBandsByStatus(StatusEnum... status) {
        final List<BandEntity> allBands = findBandsByStatus(status);
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

    @Override
    public BandEntity updateBand(BandEntity band) {
        return bandRepository.update(band);
    }

}
