/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf.util;

import de.oth.joa44741.swprojektjohn.core.enums.GenreEnum;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author Johnny
 */
@Named("utilsBean")
public class UtilsBean {

    private static final int BOOTSTRAP_MAX_COL_SIZE = 12;

    public List<GenreEnum> getAvailableGenres() {
        return Arrays.asList(GenreEnum.values());
    }

    public String getDateAsString(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    public Integer calculateBootstrapColSize(int numberOfColumns) {
        final int size = BOOTSTRAP_MAX_COL_SIZE / numberOfColumns;
        return size;
    }
}
