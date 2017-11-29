/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf;

import de.oth.joa44741.swprojektjohn.core.util.LineupDateUtils;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;

@Named("lineupBean")
public class LineupBean implements Serializable {

    private static final int BOOTSTRAP_MAX_COL_SIZE = 12;

    private FestivalEntity festival;

    @Inject
    private FestivalService festivalService;

    protected LineupBean() {

    }

    public List<Date> getTageOfLineupDatesByBuehne(Long buehnenId) {
        final BuehneEntity buehne = festivalService.retrieveBuehneById(buehnenId);
        final List<Date> tage = buehne.getLineupDates()
                .stream()
                .map(l -> l.getTag())
                .distinct()
                .sorted(LineupDateUtils.NULL_SAFE_DATE_COMPARATOR)
                .collect(Collectors.toList());
        return tage;
    }

    public List<LineupDateEntity> getLineupDatesByBuehneAndTag(Long buehnenId, Date tag) {
        final BuehneEntity buehne = festivalService.retrieveBuehneById(buehnenId);
        final List<LineupDateEntity> lineupDates = buehne.getLineupDates()
                .stream()
                .filter(l -> (l.getTag() == null && tag == null) || (l.getTag() != null) && l.getTag().equals(tag))
                .sorted(LineupDateUtils.LINEUP_DATE_TIME_COMPARATOR)
                .collect(Collectors.toList());
        return lineupDates;
    }

}
