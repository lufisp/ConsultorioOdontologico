/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.service.CategoriaImagemService;
import java.io.Serializable;
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
public class CategoriaImagemControl extends BasicControl implements Serializable{
    
    @EJB
    private CategoriaImagemService categoriaImagemService;    
    private List<Categoriaimagem> categoriaImagens;
    private Categoriaimagem categoriaImagenSelected;
    private String localizar;

    public List<Categoriaimagem> getCategoriaimagems() {
        if(categoriaImagens == null){
            localizar = "";
            doLocalizar();
        }
        return categoriaImagens;
    }

    public void setCategoriaimagems(List<Categoriaimagem> categoriaImagens) {
        this.categoriaImagens = categoriaImagens;
    }

    public Categoriaimagem getCategoriaimagemSelected() {
        return categoriaImagenSelected;
    }

    public void setCategoriaimagemSelected(Categoriaimagem categoriaImagenSelected) {
        this.categoriaImagenSelected = categoriaImagenSelected;
    }    

    public String getLocalizar() {        
        return localizar;
    }

    public void setLocalizar(String localizar) {
        this.localizar = localizar;
    }
    
    public String doLocalizar(){
        cleanCache();
        categoriaImagens = categoriaImagemService.getCategoriaimagemByName(localizar);
        return "/restrito/catimagens.faces";
    }    
    
    private void cleanCache(){
        categoriaImagens = null;
        setCategoriaimagemSelected(new Categoriaimagem());
    }
    
    public int getCategoriaimagemsCount(){
        return categoriaImagemService.getCategoriaimagemsCount();
    }
    
    public String doStartAddCategoria(){
        cleanCache();        
        return  "/restrito/addCatimagem.faces";
    }
    
    public String doFinishAddCategoriaimagem(){
        try{
            existsViolationsForJSF(getCategoriaimagemSelected());
            categoriaImagemService.addCategoriaimagem(categoriaImagenSelected);
            cleanCache();
            return "/restrito/catimagens.faces";
                    
        } catch(Exception e){
                return "/restrito/addCatimagem.faces";
        }
    }
    
    public String doStartAlterar(){
        return "/restrito/editCatimagem.faces";
    }
    
    public String doFinishAlterar(){
        try{
            existsViolationsForJSF(getCategoriaimagemSelected());
            categoriaImagemService.setCategoriaimagem(categoriaImagenSelected);
            cleanCache();
            return "/restrito/catimagens.faces";
                    
        } catch(Exception e){
                return "/restrito/editCatimagem.faces";
        }
    }
    
    public String doFinishExcluir(){        
        categoriaImagemService.removeCategoriaimagem(categoriaImagenSelected);
        categoriaImagens.remove(categoriaImagenSelected);
        return "/restrito/catimagens.faces";
    }
    
}
