/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Imagem;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.service.CategoriaImagemService;
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
    
    @EJB
    private CategoriaImagemService categoriaService;
    
    private List<Imagem> images;
    private Imagem selectedImagem;
    private Categoriaimagem selectedCategoria;
    private Orcamento selectedOrcamento;
   
    public List<Categoriaimagem> getCategorias(){
        return categoriaService.getCategoriasDeImagem();
    }

    public CategoriaImagemService getCategoriaService() {
        return categoriaService;
    }

    public void setCategoriaService(CategoriaImagemService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public Categoriaimagem getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(Categoriaimagem selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }
    
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
    
    public String doCadastrarImagem(){
        return "/restrito/addImages.faces";
    }
    
    
    
    
}
