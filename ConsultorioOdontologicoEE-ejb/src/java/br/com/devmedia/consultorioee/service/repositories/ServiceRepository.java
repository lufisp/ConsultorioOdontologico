/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service.repositories;

import br.com.devmedia.consultorioee.entities.Service;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Fernando
 */
public class ServiceRepository extends BasicRepository {

   private static final long serialVersionUID = 1L;
    
    public ServiceRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Service addService(Service service) {
        return addEntity(Service.class, service);
    }
    
    public Service setService(Service service) {
        return setEntity(Service.class, service);
    }
    
    public void removeService(Service service) {
        removeEntity(service);
    }
    
    public Service getService(int idOfService) {
        return getEntity(Service.class, idOfService);
    }
    
    public List<Service> getServices() {
        return getPureList(Service.class, "select srv from Service srv");
    }

    public List<Service> getServiceByName(String name) {
        return getPureList(Service.class,"select srv from Service srv where srv.srvName like ?1",name+"%");
    }
    
     public Service getServiceByExactName(String name) {
        return getPurePojo(Service.class,"select srv from Service srv where srv.srvName = ?1",name);
    }
    
    
}
