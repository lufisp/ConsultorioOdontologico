/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.service.repositories.CategoriaImagemRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fernando
 */

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CategoriaImagemService extends BasicService {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    
    private CategoriaImagemRepository categoriaRepository;
    
    @PostActivate
    @PostConstruct
    private void postConstruct() {
        categoriaRepository = new CategoriaImagemRepository(em);
    }
    
    public Categoriaimagem addCategoriaimagem(Categoriaimagem catImagem) {        
        return categoriaRepository.addCategoriaimagem(catImagem);
    }

    public Categoriaimagem setCategoriaimagem(Categoriaimagem catImagem) {        
        return categoriaRepository.setCategoriaimagem(catImagem);
    }

    public void removeCategoriaimagem(Categoriaimagem catImagem) {
        categoriaRepository.removeCategoriaimagem(catImagem);
    }

    public Categoriaimagem getCategoriaimagem(int idOfCatImagem) {
        return categoriaRepository.getCategoriaimagem(idOfCatImagem);
    }
    
    public List<Categoriaimagem> getCategoriasDeImagem(){
        return categoriaRepository.getCategoriasDeImagem();
    }

    public List<Categoriaimagem> getCategoriaimagemByName(String localizar) {
        return categoriaRepository.getCategoriaimagemByName(localizar);
    }
                
              

    public int getCategoriaimagemsCount() {
        return categoriaRepository.getCategoriaimagemCount();
    }
    
    
    
}
