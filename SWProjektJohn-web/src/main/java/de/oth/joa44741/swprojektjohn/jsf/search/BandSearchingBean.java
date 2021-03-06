package de.oth.joa44741.swprojektjohn.jsf.search;

import de.oth.joa44741.swprojektjohn.core.enums.GenreEnum;
import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import de.oth.joa44741.swprojektjohn.services.BandService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Andreas John
 */
@Named("bandSearchingBean")
@ViewScoped
public class BandSearchingBean implements Serializable {

    @Inject
    private BandService bandService;

    private String filterName;

    private List<GenreEnum> selectedGenres;

    private static final long serialVersionUID = 1L;

    private List<BandEntity> allBands;

    private List<BandEntity> matchingBands;

    @PostConstruct
    public void initFields() {
        allBands = bandService.findBandsByStatus(StatusEnum.FREIGEGEBEN, StatusEnum.LOESCHUNG_ANGEFORDERT);
        matchingBands = new ArrayList<>(allBands);
    }

    public List<BandEntity> getMatchingBands() {
        return matchingBands;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public List<GenreEnum> getSelectedGenres() {
        return selectedGenres;
    }

    public void setSelectedGenres(List<GenreEnum> selectedGenres) {
        this.selectedGenres = selectedGenres;
    }

    public void updateMatchingBands() {
        if (StringUtils.isEmpty(filterName)) {
            matchingBands = new ArrayList<>(allBands);
        } else {
            matchingBands = allBands.stream().filter(band -> StringUtils.containsIgnoreCase(band.getName(), filterName)).collect(Collectors.toList());
        }
        if (!selectedGenres.isEmpty()) {
            matchingBands = matchingBands.stream().filter(band -> band.getGenres().containsAll(selectedGenres)).collect(Collectors.toList());
        }
    }
}
