/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf;

import de.oth.joa44741.swprojektjohn.jsf.util.SessionUtils;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String HASHED_USER = "$2a$10$pMoeuG84oAVQw1cO0vOg/u8TVPLPJfHBE7a02n8PZpG7A6MWIkbLO";
    private static final String HASHED_PASSWORD = "$2a$10$lDg24xEjCht.U7W6gttbkeBSDl5k9npziwL3VmbvLIX2qLzau5PIW";

    private boolean isAdmin(String username, String password) {
        boolean isUserCorrect = BCrypt.checkpw(username, HASHED_USER);
        boolean isPasswordCorrect = BCrypt.checkpw(password, HASHED_PASSWORD);

        return isUserCorrect && isPasswordCorrect;
    }

    private String password;

    private String user;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String validateIsAdmin() {
        boolean isAdmin = isAdmin(user, password);
        if (isAdmin) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("adminLoggedIn", true);
            this.user = null;
            this.password = null;
            return PageNames.VERWALTUNG;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ungültiger Adminaccount",
                            "Bitte gib den korrekten Admin-Accountnamen und Passwort ein"));
            return PageNames.LOGIN_DATA;
        }
    }

    public boolean isAdminLoggedIn() {
        return SessionUtils.isAdminLoggedIn();
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return PageNames.LOGIN_DATA;
    }

}
