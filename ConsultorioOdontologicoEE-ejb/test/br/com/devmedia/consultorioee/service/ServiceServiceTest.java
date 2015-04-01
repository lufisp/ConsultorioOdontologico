/*
 * Copyright (C) 2014 dyego.carmo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dyego.carmo
 */
public class ServiceServiceTest {
    
    private EJBContainer container;
    private ServiceService instance;
    private Service srvOne;
    private Service srvTwo;
    private Service srvThree;
    
    public ServiceServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws NamingException {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        instance = (ServiceService)container.getContext().lookup("java:global/classes/ServiceService");
        
        // Mock Service Object- One
        srvOne = new Service();
        srvOne.setSrvName("Service Name Of One "+new Random().nextInt());
        srvOne.setSrvCost(new BigDecimal(new Random().nextFloat()));
        // Mock Service Object - Two
        srvTwo = new Service();
        srvTwo.setSrvName("Service Name Of Two "+new Random().nextInt());
        srvTwo.setSrvCost(new BigDecimal(new Random().nextFloat()));
        // Mock Service Object - Three
        srvThree = new Service();
        srvThree.setSrvName("Service Name of Three "+new Random().nextInt());
        srvThree.setSrvCost(new BigDecimal(new Random().nextFloat()));
        // Persist
        srvOne   = instance.addService(srvOne);
        srvTwo   = instance.addService(srvTwo);
        srvThree = instance.addService(srvThree);
    }
    
    @After
    public void tearDown() {
        instance.removeService(srvOne);
        instance.removeService(srvTwo);
        instance.removeService(srvThree);
        instance = null;
        container.close();
        container = null;
    }

    /**
     * Test of addService method, of class ServiceService.
     */
    @Test
    public void testAddService() throws Exception {
        System.out.println("addService");
        Service service = new Service();
        service.setSrvName("Test of Add Method "+new Random().nextInt());
        service.setSrvCost(new BigDecimal(new Random().nextFloat()));
        Service result = instance.addService(service);
        Service expResult = instance.getService(result.getSrvId());
        instance.removeService(service);
        assertEquals(expResult, result);
    }

    /**
     * Test of setService method, of class ServiceService.
     */
    @Test
    public void testSetService() throws Exception {
        System.out.println("setService");
        Service service = srvOne;
        String newName = "The new name of Service "+new Random().nextInt();
        service.setSrvName(newName);
        Service result = instance.setService(service);
        Service expResult = instance.getService(service.getSrvId());
        assertEquals(newName, expResult.getSrvName());
    }

    /**
     * Test of removeService method, of class ServiceService.
     */
    @Test
    public void testRemoveService() throws Exception {
        System.out.println("removeService");
        Service service = new Service();
        service.setSrvName("The Removed Name of "+new Random().nextInt());
        service.setSrvCost(new BigDecimal(new Random().nextFloat()));
        Service added = instance.addService(service);
        added = instance.getService(added.getSrvId());
        instance.removeService(service);
        Service removedService = instance.getService(added.getSrvId());
        assertNull(removedService);        
    }

    /**
     * Test of getService method, of class ServiceService.
     */
    @Test
    public void testGetService() throws Exception {
        System.out.println("getService");
        int idOfService = srvThree.getSrvId();
        Service expResult = srvThree;
        Service result = instance.getService(idOfService);
        assertEquals(expResult, result);
    }

    /**
     * Test of getServices method, of class ServiceService.
     */
    @Test
    public void testGetServices() throws Exception {
        System.out.println("getServices");
        List<Service> result = instance.getServices();
        assertEquals(3,result.size());
    }

    /**
     * Test of getServicesByName method, of class ServiceService.
     */
    @Test
    public void testGetServicesByName() throws Exception {
        System.out.println("getServicesByName");
        assertEquals(3, instance.getServicesByName("Service Name Of ").size());
        assertEquals(1, instance.getServicesByName("Service Name Of One").size());
        assertEquals(1, instance.getServicesByName("Service Name Of Two").size());
        assertEquals(1, instance.getServicesByName("Service Name Of Three").size());
        assertEquals(0, instance.getServicesByName("akjsgdkajsdh"+new Random()).size());
    }
    
}
