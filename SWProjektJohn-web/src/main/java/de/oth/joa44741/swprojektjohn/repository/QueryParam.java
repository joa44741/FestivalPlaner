package de.oth.joa44741.swprojektjohn.repository;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Andreas John
 *
 * adapted from:
 * http://www.adam-bien.com/roller/abien/entry/generic_crud_service_aka_dao
 */
public class QueryParam {

    private Map parameters = null;

    private QueryParam(String name, Object value) {
        this.parameters = new HashMap();
        this.parameters.put(name, value);
    }

    public static QueryParam with(String name, Object value) {
        return new QueryParam(name, value);
    }

    public QueryParam and(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    public Map parameters() {
        return this.parameters;
    }
}
