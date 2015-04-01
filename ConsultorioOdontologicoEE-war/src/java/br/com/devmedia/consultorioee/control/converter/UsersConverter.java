/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control.converter;

import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Fernando
 */
@FacesConverter(forClass = Users.class)
public class UsersConverter implements Converter{
    UserService userService = lookupUserServiceBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null) return null;
        return userService.getUsersByExactName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if(value == null) return null;
       return ((Users) value).getUsuName();
    }

    private UserService lookupUserServiceBean() {
        try {
            Context c = new InitialContext();
            return (UserService) c.lookup("java:global/ConsultorioOdontologicoEE/ConsultorioOdontologicoEE-ejb/UserService!br.com.devmedia.consultorioee.service.UserService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
            
    
}
