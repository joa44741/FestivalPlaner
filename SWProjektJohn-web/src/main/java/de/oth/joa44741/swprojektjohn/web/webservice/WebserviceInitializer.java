/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.webservice;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Andi
 */
@ApplicationPath("resources")
public class WebserviceInitializer extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(de.oth.joa44741.swprojektjohn.web.webservice.CustomersResource.class);
        resources.add(de.oth.joa44741.swprojektjohn.web.webservice.FestivalsResource.class);
        resources.add(de.oth.joa44741.swprojektjohn.web.webservice.JacksonHibernate4ModuleConfig.class);
    }
}
