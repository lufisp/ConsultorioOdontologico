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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

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
    private Part arquivoImagem;

    public Part getArquivoImagem() {
        return arquivoImagem;
    }

    public void setArquivoImagem(Part arquivoImagem) {
        this.arquivoImagem = arquivoImagem;
    }
    
    
   
    public List<Categoriaimagem> getCategorias(){
        List<Categoriaimagem> cats = categoriaService.getCategoriasDeImagem();
        if(selectedCategoria == null && cats.size() > 0){
            selectedCategoria = cats.get(0);
        }
        return cats;
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
        selectedImagem = new Imagem();
        selectedImagem.setImgOrcamento(selectedOrcamento);
        return "/restrito/addImages.faces";
    }
    
    public String doFinishCadastrarImagem() throws IOException{        
        byte[] arqBytes = new byte[(int)arquivoImagem.getSize()];
        arquivoImagem.getInputStream().read(arqBytes);              
        selectedImagem.setImgImagem(arqBytes);  
        imageService.addImagem(selectedImagem);
        return "/restrito/viewImages.faces";
    }
    
    
    
    
}
