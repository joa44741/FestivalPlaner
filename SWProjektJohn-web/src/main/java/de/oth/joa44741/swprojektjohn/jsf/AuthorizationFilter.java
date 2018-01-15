package de.oth.joa44741.swprojektjohn.jsf;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Andreas John
 *
 * adapted from:
 * http://www.javaknowledge.info/authentication-based-secure-login-logout-using-jsf-2-0-and-primefaces-3-4-1/
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
            String reqURI = reqt.getRequestURI();
            if (reqURI.endsWith(PageNames.VERWALTUNG + ".xhtml")) {
                if (ses != null && ses.getAttribute("adminLoggedIn") != null) {
                    chain.doFilter(request, response);
                } else {
                    resp.sendRedirect(reqt.getContextPath() + "/" + PageNames.LOGIN_DATA + ".xhtml");
                }
            } else {
                chain.doFilter(request, response);
            }

        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        // do nothing here
    }
}
