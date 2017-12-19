package de.oth.joa44741.swprojektjohn.core.util;

import de.oth.joa44741.swprojektjohn.entity.LineupDateEntity;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Andreas John
 */
public class LineupDateUtils {

    public static final Comparator<? super LineupDateEntity> LINEUP_DATE_TIME_COMPARATOR = (l1, l2) -> {
        final int tagCompareResult = getDateCompareResult(l1.getTag(), l2.getTag());
        if (tagCompareResult == 0) {
            return getDateCompareResult(l1.getUhrzeitVon(), l2.getUhrzeitVon());
        } else {
            return tagCompareResult;
        }
    };

    public static final Comparator<? super Date> NULL_SAFE_DATE_COMPARATOR = (l1, l2) -> {
        return getDateCompareResult(l1, l2);
    };

    private static int getDateCompareResult(Date tag1, Date tag2) {
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
}
