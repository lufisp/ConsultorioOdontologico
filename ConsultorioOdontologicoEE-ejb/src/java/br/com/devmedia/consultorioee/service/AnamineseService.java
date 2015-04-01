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

import br.com.devmedia.consultorioee.entities.Anaminese;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.service.repositories.AnamineseRepository;
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
public class AnamineseService extends BasicService {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    private AnamineseRepository anamineseRepository;
    

    @PostActivate
    @PostConstruct
    private void postConstruct() {
        anamineseRepository = new AnamineseRepository(em);
    }
    
    public Anaminese getAnaminese(Integer idOfAnamnese) {
        return anamineseRepository.getAnaminese(idOfAnamnese);
    }
    
    public Anaminese addAnaminese(Anaminese an) {
        return anamineseRepository.addAnaminese(an);
    }
    
    public Anaminese setAnaminese(Anaminese an) {
        return anamineseRepository.setAnaminese(an);
    }
    
    public void removeAnaminese(Anaminese an) {
        anamineseRepository.removeAnamnese(an);
    }
    
    public List<Anaminese> getAnaminesesByCustomer(Customer customer) {
        return anamineseRepository.getAnaminesesByCustomer(customer);
    }
    
    public List<Anaminese> getAnaminesesByOrcamento(Orcamento orc) {
        return anamineseRepository.getAnaminesesByOrcamento(orc);
    }
    
}
