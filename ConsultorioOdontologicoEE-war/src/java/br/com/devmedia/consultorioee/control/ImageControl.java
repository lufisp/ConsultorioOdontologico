/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Imagem;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.service.ImageService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Fernando
 */
@Named
@SessionScoped
public class ImageControl extends BasicControl{
    
    @EJB
    private ImageService imageService;
    
    private List<Imagem> images;
    private Imagem selectedImagem;
    private Orcamento selectedOrcamento;
    
    public String doViewImages(){
        return "/restrito/viewImages.faces";
    }

    public ImageService getImageService() {
        return imageService;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public List<Imagem> getImages() {
        return images;
    }

    public void setImages(List<Imagem> images) {
        this.images = images;
    }

    public Imagem getSelectedImagem() {
        return selectedImagem;
    }

    public void setSelectedImagem(Imagem selectedImagem) {
        this.selectedImagem = selectedImagem;
    }

    public Orcamento getSelectedOrcamento() {
        return selectedOrcamento;
    }

    public void setSelectedOrcamento(Orcamento selectedOrcamento) {
        this.selectedOrcamento = selectedOrcamento;
    }
    
    
    
    
}
