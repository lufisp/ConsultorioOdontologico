/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control.converter;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.service.CategoriaImagemService;
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
@FacesConverter(value = "CategoriaImagemConverter")
public class CategoriaImagemConverter implements Converter{
    CategoriaImagemService categoriaImagemService = lookupCategoriaImagemServiceBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null) return null;
        Integer idCategoria = Integer.parseInt(value);
        return categoriaImagemService.getCategoriaimagem(idCategoria);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null) return null;
        return ((Categoriaimagem) value).getCigId().toString();
    }

    private CategoriaImagemService lookupCategoriaImagemServiceBean() {
        try {
            Context c = new InitialContext();
            return (CategoriaImagemService) c.lookup("java:global/ConsultorioOdontologicoEE/ConsultorioOdontologicoEE-ejb/CategoriaImagemService!br.com.devmedia.consultorioee.service.CategoriaImagemService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
