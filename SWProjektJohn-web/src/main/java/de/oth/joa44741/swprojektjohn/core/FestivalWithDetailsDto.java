/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.core;

import de.oth.joa44741.swprojektjohn.core.enums.StatusEnum;
import de.oth.joa44741.swprojektjohn.core.enums.ZusatzeigenschaftEnum;
import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import de.oth.joa44741.swprojektjohn.entity.CampingVarianteEntity;
import de.oth.joa44741.swprojektjohn.entity.FestivalEntity;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import de.oth.joa44741.swprojektjohn.entity.TicketArtEntity;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johnny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FestivalWithDetailsDto {

    private final Long id;
    private final String name;
    private final String veranstalter;
    private final String webseite;
    private final String logoUrl;
    private final LocationEntity location;
    private final Integer ticketKontingent;
    private final Integer verkaufteTickets;
    private final String lageplan;
    private final Date datumVon;
    private final Date datumBis;
    private final Set<ZusatzeigenschaftEnum> zusatzeigenschaften;
    private final Set<CampingVarianteEntity> campingVarianten;
    private final Set<TicketArtEntity> ticketArten;
    private final Set<BuehneEntity> buehnen;
    private final StatusEnum status;

    private FestivalWithDetailsDto() {
        this.id = null;
        this.name = null;
        this.veranstalter = null;
        this.webseite = null;
        this.logoUrl = null;
        this.location = null;
        this.ticketKontingent = null;
        this.verkaufteTickets = null;
        this.lageplan = null;
        this.datumVon = null;
        this.datumBis = null;
        this.zusatzeigenschaften = null;
        this.campingVarianten = null;
        this.ticketArten = null;
        this.buehnen = null;
        this.status = null;
    }

    public FestivalWithDetailsDto(FestivalEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.veranstalter = entity.getVeranstalter();
        this.webseite = entity.getWebseite();
        this.logoUrl = entity.getLogoUrl();
        this.location = entity.getLocation();
        this.ticketKontingent = entity.getTicketKontingent();
        this.verkaufteTickets = entity.getVerkaufteTickets();
        this.lageplan = entity.getLageplan();
        this.datumVon = entity.getDatumVon();
        this.datumBis = entity.getDatumBis();
        this.zusatzeigenschaften = entity.getZusatzeigenschaften();
        this.campingVarianten = entity.getCampingVarianten();
        this.ticketArten = entity.getTicketArten();
        this.buehnen = entity.getBuehnen();
        this.status = entity.getStatus();
    }
}
