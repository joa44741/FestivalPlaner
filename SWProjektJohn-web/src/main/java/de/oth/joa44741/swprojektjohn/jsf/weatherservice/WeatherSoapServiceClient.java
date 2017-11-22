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

    public WeatherDto getWeather(LocationEntity location, Date date) {
        final Ort ort = new Ort();
        ort.bundesland = location.getBundesland().getText();
        ort.land = GERMANY;
        ort.name = location.getOrt();

        return createMockDto(ort, date);
    }

    private WeatherDto createMockDto(Ort ort, Date date) {
        final WeatherDto weatherDto = new WeatherDto();
        weatherDto.ort = ort;
        weatherDto.datum = date;
        weatherDto.avgTemperatur = 14.6f;
        weatherDto.wolkenTyp = "wolkig";
        weatherDto.regenwahrscheinlichkeit = 0.35f;
        return weatherDto;
    }

    public static class WeatherDto {

        private Ort ort;
        private Date datum;
        private float avgTemperatur;
        private float regenfall;
        private boolean wetterWarnung;
        private float minTemperatur;
        private float maxTemperatur;
        private float windgeschwindigkeit;
        private String wolkenTyp;
        private float regenwahrscheinlichkeit;

        public Date getDatum() {
            return datum;
        }

        public float getAvgTemperatur() {
            return avgTemperatur;
        }

        public String getWolkenTyp() {
            return wolkenTyp;
        }

        public float getRegenfall() {
            return regenfall;
        }

        public float getMaxTemperatur() {
            return maxTemperatur;
        }

        public float getMinTemperatur() {
            return minTemperatur;
        }

        public int getRegenwahrscheinlichkeit() {
            return (int) (regenwahrscheinlichkeit * 100);
        }

        public float getWindgeschwindigkeit() {
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
