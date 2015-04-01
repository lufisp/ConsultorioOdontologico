/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.service.CustomerService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Fernando
 */
@Named
@SessionScoped
public class CustomerControl extends BasicControl implements Serializable{
    
    @EJB
    private CustomerService customerService;
    
    private List<Customer> customers;
    private Customer customerSelected;
    private String localizar;

    public List<Customer> getCustomers() {
        if(customers == null){
            localizar = "";
            doLocalizar();
        }
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer getCustomerSelected() {
        return customerSelected;
    }

    public void setCustomerSelected(Customer customerSelected) {
        this.customerSelected = customerSelected;
    }    

    public String getLocalizar() {        
        return localizar;
    }

    public void setLocalizar(String localizar) {
        this.localizar = localizar;
    }
    
    public String doLocalizar(){
        cleanCache();
        customers = customerService.getCustomerByName(localizar);
        return "/restrito/customers.faces";
    }
    
    public String getUltimoAtendimento(Integer idOfCustomer){
        Date toReturn = customerService.getUltimoAtendimento(idOfCustomer);
        if(toReturn == null){
            return "nunca";
        }else{
            return getSdf().format(toReturn);
        }
    }
    
    private void cleanCache(){
        customers = null;
        setCustomerSelected(new Customer());
    }
    
    public int getCustomersCount(){
        return customerService.getCustomersCount();
    }
    
    public String doStartAddCustomer(){
        cleanCache();        
        return  "/restrito/addCustomer.faces";
    }
    
    public String doFinishAddCustomer(){
        try{
            existsViolationsForJSF(getCustomerSelected());
            customerService.addCustomer(customerSelected);
            cleanCache();
            return "/restrito/customers.faces";
                    
        } catch(Exception e){
                return "/restrito/addCustomer.faces";
        }
    }
    
    public String doStartAlterar(){
        return "/restrito/editCustomer.faces";
    }
    
    public String doFinishAlterar(){
        try{
            existsViolationsForJSF(getCustomerSelected());
            customerService.setCustomer(customerSelected);
            cleanCache();
            return "/restrito/customers.faces";
                    
        } catch(Exception e){
                return "/restrito/editCustomer.faces";
        }
    }
    
    public String doFinishExcluir(){        
        customerService.removeCustomer(customerSelected);
        customers.remove(customerSelected);
        return "/restrito/customers.faces";
    }
    
}
