/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.exceptions.AcessoInvalidoException;
import br.com.devmedia.consultorioee.service.repositories.UserRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fernando
 */

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserService extends BasicService{
    
    private static final long serialVersionUID = 1L;
    
    public UserService() {
    }
    
    @PersistenceContext
    private EntityManager em;    
    private UserRepository usrRepository;
    
    //anotação que indica que o método será chamado após a construção do bean...
    //preciso passar o entity manager depois que ele foi injetado, o que ocorre 
    //somente após a criação do objeto...
    @PostConstruct
    @PostActivate
    private void afterCreate(){
        usrRepository = new UserRepository(em);
    }
    
    public Users getUser(int id){
        return usrRepository.getUser(id);
    }
    
    public Users setUser(Users user) throws AcessoInvalidoException{
        if(!user.getUsuAdministrator() && !user.getUsuDentist()){
            throw new AcessoInvalidoException("O usuário não possui um acesso válido");
        }
        return usrRepository.setUser(user);        
    }
    
    public void removeUser(Users user){
        usrRepository.removeUser(user);
    }
    
    public void setPassword(int idOfUser, String password){
        usrRepository.setPassord(password, idOfUser);                
    }
    
    public Users addUser(Users user) throws AcessoInvalidoException{
         if(!user.getUsuAdministrator() && !user.getUsuDentist()){
            throw new AcessoInvalidoException("O usuário não possui um acesso válido");
        }
        return usrRepository.addUser(user);
    }
    
    public Users getUserByLoginPassword(String login, String password){
        int test;
        test = 1;
        try{
            return usrRepository.getUsersByLoginPassword(login, password);
        }catch (Exception e){
            return null;
        }
    }
    
    public List<Users> getUsersByName(String name){
        return usrRepository.getUsersByName(name);
    }
    
     public Users getUsersByExactName(String name){
        return usrRepository.getUsersByExactName(name);
    }
    
    public List<Users> getUsers(){
        return usrRepository.getUsers();
    }
    
    public List<Users> getDentistUsers(){
        return usrRepository.getDentistUsers();
    }
    
}
