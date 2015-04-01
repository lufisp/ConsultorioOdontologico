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

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.service.repositories.CustomerRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class CustomerService extends BasicService {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    private CustomerRepository customerRepository;
    
    @PostActivate
    @PostConstruct
    private void postConstruct() {
        customerRepository = new CustomerRepository(em);
    }
    
    public Customer addCustomer(Customer cus) {
        //cus.setCusAge(getIdade(cus.getCusBorndate()));
        return customerRepository.addCustomer(cus);
    }
    
    public Customer setCustomer(Customer cus) {
        //cus.setCusAge(getIdade(cus.getCusBorndate()));
        return customerRepository.setCustomer(cus);
    }
    
    public int getIdade(Date dataNascimento){
        Calendar nascimento = new GregorianCalendar();
        nascimento.setTime(dataNascimento);
        Calendar hoje = Calendar.getInstance();
        int age = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        nascimento.add(Calendar.YEAR,age);
        if(hoje.before(nascimento)){
            age--;
        }
        return age;
    }
    
    public void removeCustomer(Customer cus) {
        customerRepository.removeCustomer(cus);
    }
    
    
    public Customer getCustomer(int idOfCustomer)  {
        return customerRepository.getCustomer(idOfCustomer);
    }
    
    
    public List<Customer> getCustomerByName(String nameOfCustomer) {
        return customerRepository.getCustomerByName(nameOfCustomer);
    }
    
    public List<Customer> getCustomersToCall(int month,int year) {
        return customerRepository.getCustomersToCall(month, year);
    }
    
    public List<Customer> getCustomersComPagamentoEmAberto(int ifOfCustomer) {
        return customerRepository.getCustomersComPagamentoEmAberto(ifOfCustomer);
    }
    
    public Date getUltimoAtendimento(int idOfCustomer){
        return customerRepository.getUltimoAtendimento(idOfCustomer);             
    }
    
    public int getCustomersCount(){
        return customerRepository.getCustomersCount();
    }
    
}
