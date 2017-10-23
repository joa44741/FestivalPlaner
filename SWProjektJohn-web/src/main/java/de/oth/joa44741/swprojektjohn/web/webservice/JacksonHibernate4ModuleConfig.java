/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Johnny
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonHibernate4ModuleConfig implements ContextResolver<ObjectMapper> {

    private ObjectMapper objectMapper = new ObjectMapper() {
        private static final long serialVersionUID = 1L;

        {
            Hibernate4Module hibernate4Module = new Hibernate4Module();
            hibernate4Module.configure(Hibernate4Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
            registerModule(hibernate4Module);
        }
    };

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }

}
