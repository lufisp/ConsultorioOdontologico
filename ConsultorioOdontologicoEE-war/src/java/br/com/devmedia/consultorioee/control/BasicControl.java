/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Set;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Fernando
 */
public abstract class BasicControl implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    protected void createFacesErrorMessage(String msg){
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null);
        FacesContext.getCurrentInstance().addMessage(null, fm);        
    }
    
    protected Set<ConstraintViolation<Serializable>> getViolations(Serializable entidade){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Serializable>> toReturn = validator.validate(entidade);
        return toReturn;
    }
    
    protected void existsViolationsForJSF(Serializable entidade){
        Set<ConstraintViolation<Serializable>> toReturn = getViolations(entidade);        
        for( ConstraintViolation<Serializable> constraintViolation : toReturn){
            createFacesErrorMessage(constraintViolation.getMessage());
        }
        if(!toReturn.isEmpty()){
            throw new FacesException();
        }
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
    
    
    
}
