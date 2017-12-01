/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf;

import de.oth.joa44741.swprojektjohn.core.RemoveEvent;
import de.oth.joa44741.swprojektjohn.core.logging.DoLogging;
import de.oth.joa44741.swprojektjohn.core.util.LineupDateUtils;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import de.oth.joa44741.swprojektjohn.services.FestivalService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

@Named("lineupBean")
@SessionScoped
public class LineupBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FestivalService festivalService;

    private final List<BuehneEntity> cachedBuehnen = new ArrayList<>();

    public List<Date> getTageOfLineupDatesByBuehne(Long buehnenId) {
        final BuehneEntity buehne = retrieveBuehneById(buehnenId);
        final List<Date> tage = buehne.getLineupDates()
                .stream()
                .map(l -> l.getTag())
                .distinct()
                .sorted(LineupDateUtils.NULL_SAFE_DATE_COMPARATOR)
                .collect(Collectors.toList());
        return tage;
    }

    public List<LineupDateEntity> getLineupDatesByBuehneAndTag(Long buehnenId, Date tag) {
        final BuehneEntity buehne = retrieveBuehneById(buehnenId);
        final List<LineupDateEntity> lineupDates = buehne.getLineupDates()
                .stream()
                .filter(l -> (l.getTag() == null && tag == null) || (l.getTag() != null) && l.getTag().equals(tag))
                .sorted(LineupDateUtils.LINEUP_DATE_TIME_COMPARATOR)
                .collect(Collectors.toList());
        return lineupDates;
    }

    private BuehneEntity retrieveBuehneById(Long buehneId) {
        final Optional<BuehneEntity> optBuehne = findBuehneInCache(buehneId);
        if (optBuehne.isPresent()) {
            return optBuehne.get();
        } else {
            final BuehneEntity buehne = festivalService.retrieveBuehneById(buehneId);
            cachedBuehnen.add(buehne);
            return buehne;
        }
    }

    // too many slow calls to backend were the reason for this primitive cache
    private Optional<BuehneEntity> findBuehneInCache(Long buehneId) {
        final Optional<BuehneEntity> optBuehne = cachedBuehnen.stream()
                .filter(buehne -> buehne.getId().equals(buehneId))
                .findAny();
        return optBuehne;
    }

    @DoLogging
    public void listenToRemove(@Observes RemoveEvent removeEvent) {
        if (removeEvent.getClazz() == BuehneEntity.class) {
            cachedBuehnen.stream()
                    .filter(buehne -> buehne.getId().equals(removeEvent.getId()))
                    .findAny()
                    .ifPresent(buehne -> cachedBuehnen.remove(buehne));
        }
    }
}
