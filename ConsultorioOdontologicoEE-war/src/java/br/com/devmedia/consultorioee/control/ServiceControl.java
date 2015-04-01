/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.service.ServiceService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.inject.Named;

/**
 *
 * @author Fernando
 */
@Named
@SessionScoped
public class ServiceControl extends BasicControl{
    
    @EJB
    private ServiceService serviceService;
    
    private String localizar;
    private List<Service> srvFiltrado;
    private Service serviceSelected;
    
    public List<Service> getServices(){
        return serviceService.getServices();
    }

    public String getLocalizar() {
        return localizar;
    }

    public void setLocalizar(String localizar) {
        this.localizar = localizar;
    }

    public List<Service> getSrvFiltrado() {
        if(srvFiltrado == null){
            localizar = "";
            doLocalizar();
        }
        return srvFiltrado;
    }

    public void setSrvFiltrado(List<Service> srvFiltrado) {
        this.srvFiltrado = srvFiltrado;
    }

    public Service getServiceSelected() {
        return serviceSelected;
    }

    public void setServiceSelected(Service serviceSelected) {
        this.serviceSelected = serviceSelected;
    }
    
     public String doLocalizar(){
        srvFiltrado = serviceService.getServicesByName(getLocalizar());
        return "/restrito/services.faces";
    }
     
    public String doStartAddService(){
        setServiceSelected(new Service());
        return "/restrito/addService.faces";
    } 
    
    public String doFinishAddService(){
        srvFiltrado = null;
        try{
            existsViolationsForJSF(serviceSelected);
            serviceService.addService(serviceSelected);
            return "/restrito/services.faces";        
        }catch(FacesException e){        
            return "/restrito/addService.faces";    
        }        
    }
    
     public String doFinishExcluir(){
        setSrvFiltrado(null);
        serviceService.removeService(serviceSelected);
        return "/restrito/services.faces";
    }
     
    public String doStartAlterar(){        
        return "/restrito/editService.faces";
    }
    
    public String doFinishAlterar(){
        setSrvFiltrado(null);   
        try{
            existsViolationsForJSF(serviceSelected);
            serviceService.setService(serviceSelected);
            return "/restrito/services.faces";            
        }catch(FacesException e){
            return "/restrito/editService.faces";    
        }
        
    }
    
    
}
