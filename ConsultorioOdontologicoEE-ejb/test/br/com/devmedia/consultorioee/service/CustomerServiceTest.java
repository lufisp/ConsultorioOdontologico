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

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.PaymentType;
import br.com.devmedia.consultorioee.entities.Users;
import java.math.BigDecimal;
import java.util.Date;
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
public class CustomerServiceTest {

    private EJBContainer container;
    private CustomerService instance;
    private UserService instanceUser;
    private Customer customerOne;
    private Customer customerTwo;
    private Customer customerThree;
    private Users usrOne;
    
    public CustomerServiceTest() {
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
        instance = (CustomerService)container.getContext().lookup("java:global/classes/CustomerService");

        // Mock Object One
        customerOne = new Customer();
        customerOne.setCusAddress("Address One "+new Random().nextInt());
        customerOne.setCusAge(Math.abs(new Random().nextInt(99)));
        customerOne.setCusBorndate(new Date());
        customerOne.setCusCity("City "+new Random().nextInt());
        customerOne.setCusComplement("Complement "+new Random().nextInt());
        customerOne.setCusFather("Father "+new Random().nextInt());
        customerOne.setCusMother("Mother "+new Random().nextInt());
        customerOne.setCusName("Customer Name One "+new Random().nextInt());
        customerOne.setCusObs("Obs "+new Random().nextInt());
        customerOne.setCusOcupation("Ocupation "+new Random().nextInt());
        customerOne.setCusState("XX");
        customerOne.setCusTelephone("Tel "+new Random().nextInt());
        customerOne.setCuscelNumber("Cel "+new Random().nextInt());
        customerOne.setCusworkAddress("Work address one "+new Random().nextInt());
        customerOne.setCusworkName("WorkName "+new Random().nextInt());
        customerOne.setCusworkNumber(new Random().nextInt()+"");
        customerOne.setCusworkObs("WorkObs "+new Random().nextInt());

        // Mock Object Two
        customerTwo = new Customer();
        customerTwo.setCusAddress("Address Two "+new Random().nextInt());
        customerTwo.setCusAge(Math.abs(new Random().nextInt(99)));
        customerTwo.setCusBorndate(new Date());
        customerTwo.setCusCity("City "+new Random().nextInt());
        customerTwo.setCusComplement("Complement "+new Random().nextInt());
        customerTwo.setCusFather("Father "+new Random().nextInt());
        customerTwo.setCusMother("Mother "+new Random().nextInt());
        customerTwo.setCusName("Customer Name Two "+new Random().nextInt());
        customerTwo.setCusObs("Obs "+new Random().nextInt());
        customerTwo.setCusOcupation("Ocupation "+new Random().nextInt());
        customerTwo.setCusState("XX");
        customerTwo.setCusTelephone("Tel "+new Random().nextInt());
        customerTwo.setCuscelNumber("Cel "+new Random().nextInt());
        customerTwo.setCusworkAddress("Work address Two "+new Random().nextInt());
        customerTwo.setCusworkName("WorkName "+new Random().nextInt());
        customerTwo.setCusworkNumber(new Random().nextInt()+"");
        customerTwo.setCusworkObs("WorkObs "+new Random().nextInt());
        
        // Mock Object Three
        customerThree = new Customer();
        customerThree.setCusAddress("Address Three "+new Random().nextInt());
        customerThree.setCusAge(Math.abs(new Random().nextInt(99)));
        customerThree.setCusBorndate(new Date());
        customerThree.setCusCity("City "+new Random().nextInt());
        customerThree.setCusComplement("Complement "+new Random().nextInt());
        customerThree.setCusFather("Father "+new Random().nextInt());
        customerThree.setCusMother("Mother "+new Random().nextInt());
        customerThree.setCusName("Customer Name Three "+new Random().nextInt());
        customerThree.setCusObs("Obs "+new Random().nextInt());
        customerThree.setCusOcupation("Ocupation "+new Random().nextInt());
        customerThree.setCusState("XX");
        customerThree.setCusTelephone("Tel "+new Random().nextInt());
        customerThree.setCuscelNumber("Cel "+new Random().nextInt());
        customerThree.setCusworkAddress("Work address Three "+new Random().nextInt());
        customerThree.setCusworkName("WorkName "+new Random().nextInt());
        customerThree.setCusworkNumber(new Random().nextInt()+"");
        customerThree.setCusworkObs("WorkObs "+new Random().nextInt());
        // Mock User Object
        usrOne = new Users();
        usrOne.setUsuAdministrator(new Random().nextBoolean());
        usrOne.setUsuDentist(new Random().nextBoolean());
        usrOne.setUsuLogin("testLoginOne"+new Random().nextInt());
        usrOne.setUsuName("testNameOne "+new Random().nextInt());
        usrOne.setUsuPassword(usrOne.getUsuLogin());
        
        // Persist
        customerOne   = instance.addCustomer(customerOne);
        customerTwo   = instance.addCustomer(customerTwo);
        customerThree = instance.addCustomer(customerThree);
    }
    
    @After
    public void tearDown() {
        instance.removeCustomer(customerOne);
        instance.removeCustomer(customerTwo);
        instance.removeCustomer(customerThree);
        instance = null;
        container.close();
        container = null;
    }

    /**
     * Test of addCustomer method, of class CustomerService.
     */
    @Test
    public void testAddCustomer() throws Exception {
        System.out.println("addCustomer");
        Customer cus = new Customer();
        cus.setCusAddress("Address AddCustomer "+new Random().nextInt());
        cus.setCusAge(Math.abs(new Random().nextInt(99)));
        cus.setCusBorndate(new Date());
        cus.setCusCity("City "+new Random().nextInt());
        cus.setCusComplement("Complement "+new Random().nextInt());
        cus.setCusFather("Father "+new Random().nextInt());
        cus.setCusMother("Mother "+new Random().nextInt());
        cus.setCusName("Customer Name AddCustomer "+new Random().nextInt());
        cus.setCusObs("Obs "+new Random().nextInt());
        cus.setCusOcupation("Ocupation "+new Random().nextInt());
        cus.setCusState("XX");
        cus.setCusTelephone("Tel "+new Random().nextInt());
        cus.setCuscelNumber("Cel "+new Random().nextInt());
        cus.setCusworkAddress("Work address AddCustomer "+new Random().nextInt());
        cus.setCusworkName("WorkName "+new Random().nextInt());
        cus.setCusworkNumber(new Random().nextInt()+"");
        cus.setCusworkObs("WorkObs "+new Random().nextInt());
        Customer result = instance.addCustomer(cus);
        Customer expResult = instance.getCustomer(result.getCusId());
        assertEquals(result,expResult);
        assertEquals(result.getCusName(),expResult.getCusName());
    }

    /**
     * Test of setCustomer method, of class CustomerService.
     */
    @Test
    public void testSetCustomer() throws Exception {
        System.out.println("setCustomer");
        String nameOfCustomer = "Customer Changed Name "+new Random().nextInt();
        Customer cus = customerTwo;
        cus.setCusName(nameOfCustomer);
        Customer result = instance.setCustomer(cus);
        assertEquals(nameOfCustomer, result.getCusName());
    }

    /**
     * Test of removeCustomer method, of class CustomerService.
     */
    @Test
    public void testRemoveCustomer() throws Exception {
        System.out.println("removeCustomer");
        Customer cus = new Customer();
        cus.setCusAddress("Address AddCustomer "+new Random().nextInt());
        cus.setCusAge(Math.abs(new Random().nextInt(99)));
        cus.setCusBorndate(new Date());
        cus.setCusCity("City "+new Random().nextInt());
        cus.setCusComplement("Complement "+new Random().nextInt());
        cus.setCusFather("Father "+new Random().nextInt());
        cus.setCusMother("Mother "+new Random().nextInt());
        cus.setCusName("Customer Name AddCustomer "+new Random().nextInt());
        cus.setCusObs("Obs "+new Random().nextInt());
        cus.setCusOcupation("Ocupation "+new Random().nextInt());
        cus.setCusState("XX");
        cus.setCusTelephone("Tel "+new Random().nextInt());
        cus.setCuscelNumber("Cel "+new Random().nextInt());
        cus.setCusworkAddress("Work address AddCustomer "+new Random().nextInt());
        cus.setCusworkName("WorkName "+new Random().nextInt());
        cus.setCusworkNumber(new Random().nextInt()+"");
        cus.setCusworkObs("WorkObs "+new Random().nextInt());
        Customer toRemove = instance.addCustomer(cus);
        Customer gettedToRemove = instance.getCustomer(toRemove.getCusId());
        assertNotNull(toRemove);
        assertNotNull(gettedToRemove);
        instance.removeCustomer(gettedToRemove);
        Customer removed = instance.getCustomer(gettedToRemove.getCusId());
        assertNull(removed);
    }

    /**
     * Test of getCustomer method, of class CustomerService.
     */
    @Test
    public void testGetCustomer() throws Exception {
        System.out.println("getCustomer");
        int idOfCustomer = customerThree.getCusId();
        Customer expResult = customerThree;
        Customer result = instance.getCustomer(idOfCustomer);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCustomerByName method, of class CustomerService.
     */
    @Test
    public void testGetCustomerByName() throws Exception {
        System.out.println("getCustomerByName");
        assertEquals(3, instance.getCustomerByName("Customer Name ").size());
        assertEquals(1, instance.getCustomerByName("Customer Name Three").size());
        assertEquals(1, instance.getCustomerByName("Customer Name Two").size());
        assertEquals(1, instance.getCustomerByName("Customer Name One").size());
        assertEquals(0, instance.getCustomerByName("alksjhdgajksdh"+new Random().nextInt()).size());
    }

    /**
     * Test of getCustomersToCall method, of class CustomerService.
     */
    @Test
    public void testGetCustomersToCall() throws Exception {
        System.out.println("getCustomersToCall");
        int month = new Random().nextInt(13);
        if (month == 0) month++;
        int year = 2013;
        
        Orcamento orc = new Orcamento();
        orc.setOrcCustomer(customerOne);
        orc.setOrcDentist(usrOne);
        orc.setOrcTimes(10);
        orc.setOrcTotal(BigDecimal.TEN);
        orc.setOrcpaymentType(PaymentType.CREDITO);
        
        List<Customer> expResult = null;
        List<Customer> result = instance.getCustomersToCall(month, year);
        assertEquals(expResult, result);
        container.close();
    }

    /**
     * Test of getCustomersComPagamentoEmAberto method, of class CustomerService.
     */
    @Test
    public void testGetCustomersComPagamentoEmAberto() throws Exception {
        System.out.println("getCustomersComPagamentoEmAberto");
        int ifOfCustomer = 0;
        List<Customer> expResult = null;
        List<Customer> result = instance.getCustomersComPagamentoEmAberto(ifOfCustomer);
        assertEquals(expResult, result);
    }
    
}
