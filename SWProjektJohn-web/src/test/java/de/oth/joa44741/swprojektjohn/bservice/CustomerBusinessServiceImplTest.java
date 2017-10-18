/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.bservice;

import de.oth.joa44741.swprojektjohn.entity.CustomerEntity;
import java.util.List;
import javax.persistence.PersistenceException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Andi
 */
public class CustomerBusinessServiceImplTest extends BusinessServiceImplTestBase {

    @Test
    public void testPersistEntity_success() throws Exception {
        begin();
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setVorname("John");
        customerEntity.setNachname("Doe");
        persist(customerEntity);
        commit();
        final String statement = "SELECT t FROM " + CustomerEntity.class.getSimpleName() + " t";
        List<CustomerEntity> resultList = getResultList(statement, CustomerEntity.class);
        assertEquals(1, resultList.size());
    }

    @Test(expected = PersistenceException.class)
    public void testPersistEntity_fail() throws Exception {
        begin();
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setVorname(null);
        customerEntity.setNachname("Doe");
        persist(customerEntity);
        commit();
    }

}
