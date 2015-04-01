/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.entities.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Fernando
 */
public class LoginPadraoValidator implements ConstraintValidator<LoginPadrao, String>{

    @Override
    public void initialize(LoginPadrao constraintAnnotation) {
        System.out.println("Login Padrão validator carregado com a mensagem: " +constraintAnnotation.message());
    }

    @Override
    //Faz com que os logins sejam do tipo luiz.fernando
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value != null && value.contains(".")){
            return true;
        }else{
            return false;
        }
    }
    
}
