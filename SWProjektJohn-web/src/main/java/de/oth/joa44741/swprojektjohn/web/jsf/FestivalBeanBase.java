/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FestivalBeanBase implements Serializable {

    private static final int BOOTSTRAP_MAX_COL_SIZE = 12;

    protected FestivalEntity festival;

    private static Comparator<? super LineupDateEntity> lineupDateComparator = (l1, l2) -> {
        final Date l1Von = l1.getUhrzeitVon();
        final Date l2Von = l2.getUhrzeitVon();

        if (l1Von == null && l2Von == null) {
            return 0;
        } else if (l1Von == null) {
            return 1;
        } else if (l2Von == null) {
            return -1;
        } else {
            return l1Von.compareTo(l2Von);
        }
    };

    public List<Date> getTageOfLineupDatesByBuehne(Long buehnenId) {
        final Optional<BuehneEntity> optBuehne = festival.getBuehnen().stream().filter(b -> b.getId() == buehnenId).findFirst();
        final List<Date> tage = optBuehne.get().getLineupDates().stream().map(l -> l.getTag()).distinct().sorted(Date::compareTo).collect(Collectors.toList());
        return tage;
    }

    public List<LineupDateEntity> getLineupDatesByBuehneAndTag(Long buehnenId, Date tag) {
        final Optional<BuehneEntity> optBuehne = this.festival.getBuehnen().stream().filter(b -> b.getId() == buehnenId).findFirst();
        final List<LineupDateEntity> lineupDates = optBuehne.get().getLineupDates().stream()
                .filter(l -> l.getTag() == null || l.getTag().equals(tag))
                .sorted(lineupDateComparator)
                .collect(Collectors.toList());
        return lineupDates;
    }

    public Integer calculateBootstrapColSize() {
        final int size = BOOTSTRAP_MAX_COL_SIZE / festival.getBuehnen().size();
        return size;
    }

}
