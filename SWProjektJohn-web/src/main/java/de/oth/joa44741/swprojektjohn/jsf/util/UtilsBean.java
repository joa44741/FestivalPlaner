/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf.util;

import de.oth.joa44741.swprojektjohn.core.GenreEnum;
import java.util.Arrays;
import java.util.List;
import javax.inject.Named;

/**
 *
 * @author Johnny
 */
@Named("utilsBean")
public class UtilsBean {

    public List<GenreEnum> getAvailableGenres() {
        return Arrays.asList(GenreEnum.values());
    }
}
