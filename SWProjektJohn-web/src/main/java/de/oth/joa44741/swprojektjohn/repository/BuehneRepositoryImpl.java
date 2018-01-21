package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

/**
 * This class only needs the 'extends' keyword with the specific type
 * 'BuehneEntity' for the generic super class. There are no special cases for
 * this repository class that's why the body is empty.
 *
 * @author Andreas John
 */
@RequestScoped
@Transactional
public class BuehneRepositoryImpl extends RepositoryBaseImpl<BuehneEntity> implements BuehneRepository {

}
