package de.oth.joa44741.swprojektjohn.jsf.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Andreas John
 *
 * adapted from:
 * http://www.javaknowledge.info/authentication-based-secure-login-logout-using-jsf-2-0-and-primefaces-3-4-1/
 */
public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static boolean isAdminLoggedIn() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        boolean result = false;
        if (session != null) {
            Object adminLoggedIn = session.getAttribute("adminLoggedIn");
            if (adminLoggedIn != null) {
                result = (boolean) adminLoggedIn;
            }
        }
        return result;
    }

}
