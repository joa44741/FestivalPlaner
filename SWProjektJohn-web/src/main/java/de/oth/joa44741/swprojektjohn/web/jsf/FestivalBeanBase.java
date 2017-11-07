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

    protected FestivalBeanBase() {

    }

    private static Comparator<? super LineupDateEntity> lineupDateTimeComparator = (l1, l2) -> {
        final int tagCompareResult = getDateCompareResult(l1.getTag(), l2.getTag());
        if (tagCompareResult == 0) {
            return getDateCompareResult(l1.getTag(), l2.getTag());
        } else {
            return tagCompareResult;
        }
    };

    private static Comparator<? super Date> dateComparator = (l1, l2) -> {
        return getDateCompareResult(l1, l2);
    };

    public static int getDateCompareResult(Date tag1, Date tag2) {
        if (tag1 == null && tag2 == null) {
            return 0;
        } else if (tag1 == null) {
            return 1;
        } else if (tag2 == null) {
            return -1;
        } else {
            return tag1.compareTo(tag2);
        }
    }

    public List<Date> getTageOfLineupDatesByBuehne(Long buehnenId) {
        final Optional<BuehneEntity> optBuehne = festival.getBuehnen().stream().filter(b -> b.getId().equals(buehnenId)).findFirst();
        final List<Date> tage = optBuehne.get().getLineupDates().stream().map(l -> l.getTag()).distinct().sorted(dateComparator).collect(Collectors.toList());
        return tage;
    }

    public List<LineupDateEntity> getLineupDatesByBuehneAndTag(Long buehnenId, Date tag) {
        final Optional<BuehneEntity> optBuehne = this.festival.getBuehnen().stream().filter(b -> b.getId().equals(buehnenId)).findFirst();
        final List<LineupDateEntity> lineupDates = optBuehne.get().getLineupDates().stream()
                .filter(l -> (l.getTag() == null && tag == null) || (l.getTag() != null) && l.getTag().equals(tag))
                .sorted(lineupDateTimeComparator)
                .collect(Collectors.toList());
        return lineupDates;
    }

    public Integer calculateBootstrapColSize() {
        final int size = BOOTSTRAP_MAX_COL_SIZE / festival.getBuehnen().size();
        return size;
    }

}
