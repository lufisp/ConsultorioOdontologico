/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Imagem;
import br.com.devmedia.consultorioee.service.repositories.ImageRepository;
import java.util.Date;
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
public class ImageService extends BasicService{
    
     private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    private ImageRepository imageRepository;
    
    @PostConstruct
    @PostActivate
    private void postConstruct() {
        imageRepository = new ImageRepository(em);
    }
    
    public Imagem addImagem(Imagem imagem) {
        imagem.setImgdataInclusao(new Date());
        imagem.setImghoraInclusao(new Date());
        
        return imageRepository.addImagem(imagem);
    }
    
    public Imagem setImagem(Imagem imagem) {
        return imageRepository.setImagem(imagem);
    }
    
    public void removeImagem(Imagem imagem) {
        imageRepository.removeImagem(imagem);
    }
    
    public Imagem getImagem(int idOfImagem) {
        return imageRepository.getImagem(idOfImagem);
    }
    
    public List<Imagem> getImagensOfOrcamento(int idOrcamento) {
        return imageRepository.getImagensOfOrcamento(idOrcamento);
    }

    public List<Imagem> getImagensOfOrcamento(int idOrcamento, int idCategoria) {
        return imageRepository.getImagensOfOrcamento(idOrcamento,idCategoria);
    }
    
}
