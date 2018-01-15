package de.oth.joa44741.swprojektjohn.services.weatherservice;

import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jboss.logging.Logger;

/**
 *
 * @author Andreas John
 */
@RequestScoped
public class WeatherSoapServiceClient {

    @Inject
    private transient Logger logger;

    private static final Map<de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum, BundeslandEnum> BUNDESLAND_MAPPING = new HashMap();

    static {
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.BADEN_WUERTTEMBERG, BundeslandEnum.BADEN_WÜRTEMBERG);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.BAYERN, BundeslandEnum.BAYERN);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.BERLIN, BundeslandEnum.BERLIN);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.BRANDENBURG, BundeslandEnum.BRANDENBURG);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.BREMEN, BundeslandEnum.BREMEN);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.HAMBURG, BundeslandEnum.HAMBURG);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.HESSEN, BundeslandEnum.HESSEN);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.MECKLENBURG_VORPOMMERN, BundeslandEnum.MECKLEMBURG_VORPOMMERN);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.NIEDERSACHSEN, BundeslandEnum.NIEDERSACHSEN);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.NORDRHEIN_WESTFALEN, BundeslandEnum.NORDREIN_WESTFALEN);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.RHEINLAND_PFALZ, BundeslandEnum.RHEINLAND_PFALZ);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.SAARLAND, BundeslandEnum.SAARLAND);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.SACHSEN, BundeslandEnum.SACHSEN);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.SACHSEN_ANHALT, BundeslandEnum.SACHSEN_ANHALT);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.SCHLESWIG_HOLSTEIN, BundeslandEnum.SCHLESWIG_HOLSTEIN);
        BUNDESLAND_MAPPING.put(de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum.THUERINGEN, BundeslandEnum.THÜRINGEN);
    }

    public Optional<WetterDto> getWeather(FestivalEntity festival) {
        final LocationEntity location = festival.getLocation();
        final Date date = festival.getDatumVon();

        final Optional<DetailWettervorhersage> optDetailWettervorhersage = retrieveDetailWettervorhersage(date, location);
        if (optDetailWettervorhersage.isPresent()) {
            return Optional.of(convertDetailWettervorhersageToWetterDto(optDetailWettervorhersage.get()));
        } else {
            return Optional.empty();
        }

    }

    private WetterDto convertDetailWettervorhersageToWetterDto(DetailWettervorhersage detailWettervorhersage) {
        final WetterDto wetterDto = new WetterDto();
        wetterDto.datum = detailWettervorhersage.datum.toGregorianCalendar().getTime();
        wetterDto.avgTemperatur = detailWettervorhersage.avgTemperatur;
        wetterDto.regenfall = detailWettervorhersage.regenfall;
        wetterDto.wetterWarnung = detailWettervorhersage.wetterwarnung;
        wetterDto.minTemperatur = detailWettervorhersage.minTemperatur;
        wetterDto.maxTemperatur = detailWettervorhersage.maxTemperatur;
        wetterDto.windgeschwindigkeit = detailWettervorhersage.windgeschwindigkeit;
        wetterDto.wolkenTyp = detailWettervorhersage.wolkenTyp.value();
        wetterDto.regenwahrscheinlichkeit = detailWettervorhersage.regenwahrscheinlichkeit;
        return wetterDto;
    }

    private Optional<DetailWettervorhersage> retrieveDetailWettervorhersage(Date date, LocationEntity location) {
        try {
            final DetailWettervorhersage result = doRetrieveWetterDto(date, location);
            logger.info("retrieved DetailWettervorhersage from weather service: " + result.getAvgTemperatur() + " Celsius, rain: " + result.getRegenwahrscheinlichkeit() * 100);
            return Optional.of(result);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return Optional.empty();
        }
    }

    private DetailWettervorhersage doRetrieveWetterDto(Date date, LocationEntity location) throws Exception {
        final WetterServiceService service = new WetterServiceService();
        final WetterService port = service.getWetterServicePort();
        final RegistrierterNutzer regNutzer = new RegistrierterNutzer();
        regNutzer.setNutzername("Andi");
        regNutzer.setPasswort("John");
        final Ort ort = new Ort();
        ort.setBundesland(BUNDESLAND_MAPPING.get(location.getBundesland()));
        ort.setLand(LandEnum.DEUTSCHLAND);
        ort.setOrtName(location.getOrt());
        final GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        final XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

        final DetailWettervorhersage result = port.holeDetailWettervorhersage(regNutzer, ort, xmlCal);
        return result;
    }

    public static class WetterDto {

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

}
