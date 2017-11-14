/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.repository;

import de.oth.joa44741.swprojektjohn.entity.BuehneEntity;

/**
 *
 * @author Andi
 */
public interface BuehneRepository {

    BuehneEntity retrieveBuehneById(Long id);

    BuehneEntity updateBuehne(BuehneEntity buehne);
}
