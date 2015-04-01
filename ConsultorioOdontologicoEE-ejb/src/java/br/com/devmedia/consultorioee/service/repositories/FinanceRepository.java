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

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Parcela;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author dyego.carmo
 */
public class FinanceRepository extends BasicRepository {

    private static final long serialVersionUID = 1L;

    public FinanceRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Parcela getParcela(int idParcela) {
        return getEntity(Parcela.class, idParcela);
    }
    
    public Parcela setParcela(Parcela par) {
        return setEntity(Parcela.class, par);
    }
    
    public Parcela addParcela(Parcela par) {
        return addEntity(Parcela.class, par);
    }
    
    public void removeParcela(Parcela par) {
        removeEntity(par);
    }
    
    public List<Parcela> getParcelasOfOrcamento(int idOfOrcamento) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcId = ?1",idOfOrcamento);
    }
    
    public List<Parcela> getParcelasOfOrcamentoEmAberto(int idOfOrcamento) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcId = ?1 and par.parPago = ?2",idOfOrcamento,Boolean.FALSE);
    }
    
    public List<Parcela> getParcelasOfOrcamentoPagas(int idOfOrcamento) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcId = ?1 and par.parPago = ?2",idOfOrcamento,Boolean.TRUE);
    }
    
    public List<Parcela> getParcelasOfCustomer(int idOfCustomer) {
        return getPureList(Parcela.class, "select par from Parcela par where par.parOrcamento.orcCustomer.cusId = ?1 order by par.parPago ASC", idOfCustomer);
    }
    
    public List<Parcela> getParcelasOfCustomerEmAberto(int idOfCustomer) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcCustomer.cusId = ?1 and par.parPago = ?2", idOfCustomer,Boolean.FALSE);
    }
    
    public List<Parcela> getParcelasOfCustomerPagas(int idOfCustomer) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcCustomer.cusId = ?1 and par.parPago = ?2", idOfCustomer,Boolean.TRUE);
    }
    
    public Parcela setPagamentoParcela(int idOfParcela) {
        Parcela par = getParcela(idOfParcela);
        par.setParPago(true);
        par = setParcela(par);
        return par;
    }

    public void eliminarParcelas(Orcamento orc) {
        executeCommand("delete from Parcela par where par.parOrcamento.orcId = ?1",orc.getOrcId());
        
    }

    public List<Customer> getParcelasOfOrcamentoEmAberto() {
        return getPureList(Customer.class,"select distinct par.parOrcamento.orcCustomer from Parcela par where par.parPago = ?1",Boolean.FALSE);
    }
    
}
