/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.services;

import javax.enterprise.context.RequestScoped;
import org.jboss.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Andi
 */
@RequestScoped
public class AdminBusinessServiceImpl implements AdminBusinessService {

    private static final Logger LOG = Logger.getLogger(AdminBusinessServiceImpl.class);

    private static final String HASHED_USER = "$2a$10$pMoeuG84oAVQw1cO0vOg/u8TVPLPJfHBE7a02n8PZpG7A6MWIkbLO";
    private static final String HASHED_PASSWORD = "$2a$10$lDg24xEjCht.U7W6gttbkeBSDl5k9npziwL3VmbvLIX2qLzau5PIW";

    @Override
    public boolean isAdmin(String username, String password) {
        boolean isUserCorrect = BCrypt.checkpw(username, HASHED_USER);
        boolean isPasswordCorrect = BCrypt.checkpw(password, HASHED_PASSWORD);

        return isUserCorrect && isPasswordCorrect;
    }

}
