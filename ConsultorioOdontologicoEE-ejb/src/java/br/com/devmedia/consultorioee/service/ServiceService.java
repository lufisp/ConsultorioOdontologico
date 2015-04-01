/*
 * Copyright (C) 2014 dyego.carmo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.service.repositories.ServiceRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dyego.carmo
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServiceService extends BasicService {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    private ServiceRepository serviceRepository;
    
    @PostConstruct
    @PostActivate
    private void postConstruct() {
        serviceRepository = new ServiceRepository(em);
    }
    
    public Service addService(Service service) {
        return serviceRepository.addService(service);
    }
    
    public Service setService(Service service) {
        return serviceRepository.setService(service);
    }
    
    public void removeService(Service service) {
        serviceRepository.removeService(service);
    }
    
    public Service getService(int idOfService) {
        return serviceRepository.getService(idOfService);
    }
    
    public List<Service> getServices() {
        return serviceRepository.getServices();
    }
    
    public List<Service> getServicesByName(String name) {
        return serviceRepository.getServiceByName(name);
    }
    
     public Service getServicesByExactName(String name) {
        return serviceRepository.getServiceByExactName(name);
    }
    
    
}
