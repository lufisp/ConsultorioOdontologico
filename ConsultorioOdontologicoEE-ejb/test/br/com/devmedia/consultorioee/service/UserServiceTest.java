/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.exceptions.AcessoInvalidoException;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernando
 */
public class UserServiceTest {
    
    private Users usrOne;
    private Users usrTwo;
    private Users usrThree;
    private EJBContainer container;
    private UserService instance;
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws NamingException, AcessoInvalidoException {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        //Mock user object
        usrOne = new Users();
        usrOne.setUsuAdministrator(new Random().nextBoolean());
        usrOne.setUsuDentist(new Random().nextBoolean());
        usrOne.setUsuLogin("myLogin" + new Random().nextInt());
        usrOne.setUsuName("testName" + new Random().nextInt());
        usrOne.setUsuPassword(usrOne.getUsuLogin());
        
        //Mock user object
        usrTwo = new Users();
        usrTwo.setUsuAdministrator(new Random().nextBoolean());
        usrTwo.setUsuDentist(new Random().nextBoolean());
        usrTwo.setUsuLogin("myLogin" + new Random().nextInt());
        usrTwo.setUsuName("testName" + new Random().nextInt());
        usrTwo.setUsuPassword(usrTwo.getUsuLogin());
        
        //Mock user object
        usrThree = new Users();
        usrThree.setUsuAdministrator(new Random().nextBoolean());
        usrThree.setUsuDentist(new Random().nextBoolean());
        usrThree.setUsuLogin("myLogin" + new Random().nextInt());
        usrThree.setUsuName("testName" + new Random().nextInt());
        usrThree.setUsuPassword(usrThree.getUsuLogin());
        
        usrOne = instance.addUser(usrOne);
        usrTwo = instance.addUser(usrTwo);
        usrThree = instance.addUser(usrThree);
        
        
    }
    
    @After
    public void tearDown() {
        instance.removeUser(usrOne);
        instance.removeUser(usrTwo);
        instance.removeUser(usrThree);
        
        usrOne = null;
        usrTwo = null;
        usrThree = null;
        
        container.close();
        
    }

    /**
     * Test of getUser method, of class UserService.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        int id = 0;        
        Users expResult = usrTwo;
        Users result = instance.getUser(usrTwo.getUsuId());
        assertEquals(expResult, result);
    }

    /**
     * Test of setUser method, of class UserService.
     */
    @Test
    public void testSetUser() throws Exception {
        System.out.println("setUser");
        Users user = usrThree;
        Users expResult = usrThree;        
        user.setUsuName("novoNome"+ new Random().nextInt());        
        Users result = instance.setUser(user);        
        Users resultFromGet = instance.getUser(user.getUsuId());        
              
        assertEquals(user,result);
        assertEquals(resultFromGet, result);
        assertEquals(resultFromGet.getUsuName(), result.getUsuName());
    }

    /**
     * Test of removeUser method, of class UserService.
     */
    @Test
    public void testRemoveUser() throws Exception {
        //Mock user object
        Users user;
        user = new Users();
        user.setUsuAdministrator(new Random().nextBoolean());
        user.setUsuDentist(new Random().nextBoolean());
        user.setUsuLogin("My Test Login User" + new Random().nextInt());
        user.setUsuName("My Test Name User" + new Random().nextInt());
        user.setUsuPassword(user.getUsuLogin());        
        user = instance.addUser(user);        
        System.out.println("removeUser");        
        instance.removeUser(user);
        Users userRemoved = instance.getUser(user.getUsuId());
        assertNull(userRemoved);
    }

    /**
     * Test of setPassword method, of class UserService.
     */
    @Test
    public void testSetPassword() throws Exception {
        System.out.println("setPassword");        
        String tmpPassword = new Random().nextInt() + "Change Pass";
        String md5TmpPassword = getMd5(tmpPassword);
        instance.setPassword(usrTwo.getUsuId(),tmpPassword);
        Users user = instance.getUser(usrTwo.getUsuId());
        assertEquals(user.getUsuPassword(),md5TmpPassword);        
    }

    /**
     * Test of addUser method, of class UserService.
     */
    @Test
    public void testAddUser() throws Exception {
         //Mock user object
        System.out.println("addUser");
        Users user;
        user = new Users();
        user.setUsuAdministrator(new Random().nextBoolean());
        user.setUsuDentist(new Random().nextBoolean());
        user.setUsuLogin("My Test Login User(add)" + new Random().nextInt());
        user.setUsuName("My Test Name User(add)" + new Random().nextInt());
        user.setUsuPassword(user.getUsuLogin());        
        Users result = instance.addUser(user);        
                
        assertEquals(user, result);
        instance.removeUser(user);
       
    }

    /**
     * Test of getUserByLoginPassword method, of class UserService.
     */
    @Test
    public void testGetUserByLoginPassword() throws Exception {
        System.out.println("getUserByLoginPassword");
        String login = usrOne.getUsuLogin();
        String password = usrOne.getUsuLogin();
        Users result = instance.getUserByLoginPassword(login, password);
        Users expResult = usrOne;
        assertEquals(expResult, result);       
    }

    /**
     * Test of getUsers method, of class UserService.
     */
    @Test
    public void testGetUsers() throws Exception {
        System.out.println("getUsers");
        List<Users> expResult = new LinkedList<Users>();
        expResult.add(usrOne);
        expResult.add(usrTwo);
        expResult.add(usrThree);
        List<Users> result = instance.getUsers();
        
        boolean teste = expResult.containsAll(result);
        teste = result.containsAll(expResult) & teste;
                
        assertTrue(teste);
       
    }
    
     private String getMd5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return digest;
    }
    
}
