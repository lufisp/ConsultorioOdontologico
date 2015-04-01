/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.entities.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Fernando
 */
@Constraint(validatedBy = DescricaoServicoUnicoValidator.class )
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DescricaoServicoUnico {
    
     String message() default "Já existe um serviço com esse nome cadastrado.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    
}
