/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.jsf;

import de.oth.joa44741.swprojektjohn.services.AdminBusinessService;
import de.oth.joa44741.swprojektjohn.jsf.util.PageNames;
import de.oth.joa44741.swprojektjohn.jsf.util.SessionUtils;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AdminBusinessService adminBusinessService;

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
        boolean isAdmin = adminBusinessService.isAdmin(user, password);
        if (isAdmin) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("adminLoggedIn", true);
            this.user = null;
            this.password = null;
            return PageNames.ADMIN_DATA;
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
