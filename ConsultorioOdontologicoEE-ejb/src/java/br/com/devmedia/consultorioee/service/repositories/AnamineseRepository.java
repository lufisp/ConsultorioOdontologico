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

package br.com.devmedia.consultorioee.service.repositories;

import br.com.devmedia.consultorioee.entities.Anaminese;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import java.util.List;
import javax.persistence.EntityManager;


public class AnamineseRepository extends BasicRepository {

    public AnamineseRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Anaminese getAnaminese(int idOfAnamnese) {
        return getEntity(Anaminese.class, idOfAnamnese);
    }

    public Anaminese setAnaminese(Anaminese anaminese) {
        return setEntity(Anaminese.class, anaminese);
    }
    
    public Anaminese addAnaminese(Anaminese anaminese) {
        return addEntity(Anaminese.class, anaminese);
    }
    
    public void removeAnamnese(Anaminese anaminese) {
        removeEntity(anaminese);
    }
    
    public List<Anaminese> getAnaminesesByCustomer(Customer customer) {
        return getPureList(Anaminese.class,"select anam from Anaminese anam where anam.ansCustomer.cusId = ?1",customer.getCusId());
    }

    public List<Anaminese> getAnaminesesByOrcamento(Orcamento orc) {
        return getPureList(Anaminese.class,"select anam from Anaminese anam where anam.ansOrcamento.orcId = ?1",orc.getOrcId());
    }
    
}
