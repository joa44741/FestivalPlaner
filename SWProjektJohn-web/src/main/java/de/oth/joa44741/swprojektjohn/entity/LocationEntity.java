package de.oth.joa44741.swprojektjohn.entity;

import de.oth.joa44741.swprojektjohn.core.enums.BundeslandEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Andreas John
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = LocationEntity.QUERY_NAME_RETRIEVE_LOCATION_BY_FESTIVAL_ID, query = "SELECT l FROM FestivalEntity f "
            + "join f.location l "
            + "where f.id = :id")
})
@Entity
@Table(name = "Locations")
public class LocationEntity extends AbstractLongEntity {

    public static final String QUERY_NAME_RETRIEVE_LOCATION_BY_FESTIVAL_ID = "retrieveLocationByFestivalId";

    @Column
    private String name;

    @Column
    private String strasse;

    @Column(nullable = false) // --> database
    @NotNull
    private String ort;

    @Column(nullable = false)
    @NotNull
    @Size(min = 5, max = 5)
    private String plz;

    @Column
    @Enumerated(EnumType.STRING)
    private BundeslandEnum bundesland;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public BundeslandEnum getBundesland() {
        return bundesland;
    }

    public void setBundesland(BundeslandEnum bundesland) {
        this.bundesland = bundesland;
    }

    public String getAddressAsString() {
        String address = "";
        if (StringUtils.isNotEmpty(strasse)) {
            address += strasse + " ";
        }
        address += plz + " ";
        address += ort;
        return address;
    }

    public String getLocationAsString() {
        String result = "";
        if (StringUtils.isNotEmpty(name)) {
            result += name + " ";
        }
        result += getAddressAsString();
        return result;
    }
}
