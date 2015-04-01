/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control.converter;

import br.com.devmedia.consultorioee.entities.PaymentType;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Fernando
 */
@FacesConverter(forClass = PaymentType.class, value="payConversor")
public class PaymentTypeConverter implements Converter{

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {  
        int debug = 1;
        if(value == null) return null;
        for(PaymentType payt : PaymentType.values()){
            if(payt.getDescription().equals(value)){
                return payt;
            }
        }
        throw new IllegalArgumentException("Erro de conversão do tipo de pagamento");
    }

    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        int debug = 1;
        if(value == null)return null;
        return ((PaymentType) value).getDescription();
    }
    
}
