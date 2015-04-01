/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Anaminese;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.service.AnamineseService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Fernando
 */
@Named
@SessionScoped
public class AnamineseControl extends BasicControl implements Serializable {
   
    @EJB
    private AnamineseService anamineseService;
    @Inject
    private OrcamentoControl orcamentoControl;
   
    
    private Anaminese selectedAnaminese;
    private Customer selectedCustomer;
    private List<Anaminese> anamineses;

    public Anaminese getSelectedAnaminese() {
        return selectedAnaminese;
    }

    public void setSelectedAnaminese(Anaminese selectedAnaminese) {
        this.selectedAnaminese = selectedAnaminese;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<Anaminese> getAnamineses() {
        return anamineses;
    }
    
    public void setAnamineses(List<Anaminese> anamineses) {
        this.anamineses = anamineses;
    }
    
    public void cleanCache(){
        setSelectedAnaminese(new Anaminese());
        getSelectedAnaminese().setAnsCustomer(selectedCustomer);
        anamineses = anamineseService.getAnaminesesByCustomer(selectedCustomer);
    }
    
    public String doStartAddAnamnese(){
        cleanCache();
        return "/restrito/addAnamnese.faces";
    }
    
    public String doFinishAddAnamnese(){        
        selectedAnaminese.setAnsCustomer(selectedCustomer);
        anamineseService.addAnaminese(selectedAnaminese);
        cleanCache();
        return "/restrito/orcamentos.faces";
    }
    
    public String doFinishExcluir(){
        anamineseService.removeAnaminese(selectedAnaminese);
        cleanCache();
        return "/restrito/orcamentos.faces";
    }
    
    public String doStartAlterar(){
        return "/restrito/editAnamnese";
    }
    
    public String doFinishEditAnamnese(){
       anamineseService.setAnaminese(selectedAnaminese);
       cleanCache();
       return "/restrito/orcamentos.faces";
    }
    
   
    
    
    
}
