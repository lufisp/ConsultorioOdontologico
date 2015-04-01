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

import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Orcamentoitem;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Dyego Souza do Carmo
 * @version 1.0
 * @since 05/2014
 */
public class OrcamentoRepository extends BasicRepository {

    private static final long serialVersionUID = 1L;

    public OrcamentoRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Orcamento getOrcamento(int idOfOrcamento) {
        return getEntity(Orcamento.class, idOfOrcamento);
    }
    
    public Orcamento addOrcamento(Orcamento orc) {
        return addEntity(Orcamento.class, orc);
    }
    
    public Orcamento setOrcamento(Orcamento orc) {
        return setEntity(Orcamento.class, orc);
    }
    
    public void removeOrcamento(Orcamento orc) {
        removeEntity(orc);
    }

    public void removeItem(Orcamentoitem ori) {
        removeEntity(ori);
    }
    
    public Orcamentoitem addItem(Orcamentoitem ori) {
        return addEntity(Orcamentoitem.class, ori);
    }
    
    public Orcamentoitem setItem(Orcamentoitem ori) {
        return setEntity(Orcamentoitem.class, ori);
    }
    
    public Orcamentoitem getItem(int idofOri) {
        return getEntity(Orcamentoitem.class, idofOri);
    }
    
    public List<Orcamento> getOrcamentos(int idOfCustomer) {
        int debug = 1;
        List<Orcamento> orcList = getPureList(Orcamento.class,"select orc from Orcamento orc where orc.orcCustomer.cusId = ?1",idOfCustomer);
        return orcList;
    }
    
    public List<Orcamentoitem> getItens(int idOrcamento) {
        return getPureList(Orcamentoitem.class,"select ori from Orcamentoitem ori where ori.oriOrcamento.orcId = ?1",idOrcamento);
    }
    
    
}
