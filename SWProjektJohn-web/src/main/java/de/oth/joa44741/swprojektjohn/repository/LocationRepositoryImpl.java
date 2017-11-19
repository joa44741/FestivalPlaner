/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.core.qualifier.LocationRepository;
import de.oth.joa44741.swprojektjohn.entity.LocationEntity;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

/**
 *
 * @author Andi
 */
@RequestScoped
@Transactional
@LocationRepository
public class LocationRepositoryImpl extends RepositoryBaseImpl<LocationEntity> {

}
