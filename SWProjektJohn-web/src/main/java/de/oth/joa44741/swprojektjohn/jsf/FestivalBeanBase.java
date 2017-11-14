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
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO is it really a base??
public class FestivalBeanBase implements Serializable {

    private static final int BOOTSTRAP_MAX_COL_SIZE = 12;

    protected FestivalEntity festival;

    protected FestivalBeanBase() {

    }

    public List<Date> getTageOfLineupDatesByBuehne(Long buehnenId) {
        final Optional<BuehneEntity> optBuehne = festival.getBuehnen().stream().filter(b -> b.getId().equals(buehnenId)).findFirst();
        final List<Date> tage = optBuehne.get().getLineupDates().stream().map(l -> l.getTag()).distinct().sorted(LineupDateUtils.NULL_SAFE_DATE_COMPARATOR).collect(Collectors.toList());
        return tage;
    }

    public List<LineupDateEntity> getLineupDatesByBuehneAndTag(Long buehnenId, Date tag) {
        final Optional<BuehneEntity> optBuehne = this.festival.getBuehnen().stream().filter(b -> b.getId().equals(buehnenId)).findFirst();
        final List<LineupDateEntity> lineupDates = optBuehne.get().getLineupDates().stream()
                .filter(l -> (l.getTag() == null && tag == null) || (l.getTag() != null) && l.getTag().equals(tag))
                .sorted(LineupDateUtils.LINEUP_DATE_TIME_COMPARATOR)
                .collect(Collectors.toList());
        return lineupDates;
    }

    public Integer calculateBootstrapColSize() {
        final int size = BOOTSTRAP_MAX_COL_SIZE / festival.getBuehnen().size();
        return size;
    }

}
