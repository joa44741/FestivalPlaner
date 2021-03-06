package de.oth.joa44741.swprojektjohn.services;

import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import java.util.List;

/**
 *
 * @author Andreas John
 */
public interface BandService {

    BandEntity retrieveBandById(Long id);

    BandEntity retrieveBandByIdIncludingDetails(Long id);

    List<BandEntity> findAllBands();

    List<BandEntity> findBandsByStatus(StatusEnum... status);

    List<BandEntity> findRandomBandsByStatus(StatusEnum... status);

    BandEntity persistBand(BandEntity band);

    void removeBand(Long Id);

    BandEntity updateBand(BandEntity band);

}
