/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.BandEntity;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

/**
 *
 * @author Andreas John
 */
@RequestScoped
@Transactional
public class BandRepositoryImpl extends RepositoryBaseImpl<BandEntity> implements BandRepository {

}
