/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf.weatherservice;

import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.Date;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Andi
 */
@RequestScoped
public class WeatherSoapServiceClient {

    private static final String GERMANY = "Deutschland";

    public WetterDto getWeather(LocationEntity location, Date date) {
        final Ort ort = new Ort();
        ort.bundesland = location.getBundesland().getText();
        ort.land = GERMANY;
        ort.name = location.getOrt();

        return createMockDto(ort, date);
    }

    private WetterDto createMockDto(Ort ort, Date date) {
        final WetterDto weatherDto = new WetterDto();
        weatherDto.ort = ort;
        weatherDto.datum = date;
        weatherDto.avgTemperatur = 14.6f;
        weatherDto.wolkenTyp = "wolkig";
        weatherDto.regenwahrscheinlichkeit = 0.35f;
        return weatherDto;
    }

    public static class WetterDto {

        private Ort ort;
        private Date datum;
        private Float avgTemperatur;
        private Float regenfall;
        private boolean wetterWarnung;
        private Float minTemperatur;
        private Float maxTemperatur;
        private Float windgeschwindigkeit;
        private String wolkenTyp;
        private Float regenwahrscheinlichkeit;

        public Date getDatum() {
            return datum;
        }

        public Float getAvgTemperatur() {
            return avgTemperatur;
        }

        public String getWolkenTyp() {
            return wolkenTyp;
        }

        public Float getRegenfall() {
            return regenfall;
        }

        public Float getMaxTemperatur() {
            return maxTemperatur;
        }

        public Float getMinTemperatur() {
            return minTemperatur;
        }

        public int getRegenwahrscheinlichkeit() {
            return (int) (regenwahrscheinlichkeit * 100);
        }

        public Float getWindgeschwindigkeit() {
            return windgeschwindigkeit;
        }

        public boolean isWetterWarnung() {
            return wetterWarnung;
        }
    }

    private static class Ort {

        private String name;
        private String land;
        private String bundesland;
    }
}
