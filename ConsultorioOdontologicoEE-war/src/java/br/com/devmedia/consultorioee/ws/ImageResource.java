/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.ws;

import br.com.devmedia.consultorioee.entities.Imagem;
import br.com.devmedia.consultorioee.service.ImageService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Fernando
 */
@Path("image")
@RequestScoped
public class ImageResource {

    @Context
    private UriInfo context;

    @EJB
    private ImageService imageService;
    
    
    public ImageResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idOrcamento}/{idCategoria}")
    public List<Imagem> getImagensData(@PathParam("idOrcamento") int idOrcamento,@PathParam("idCategoria") int idCategoria ) {
        System.out.println("Id do orcamento é:" + idOrcamento);
        System.out.println("Id da categoria é:" + idCategoria);
        List<Imagem> imgList = new LinkedList<Imagem>();
        List<Imagem> fromJPA = imageService.getImagensOfOrcamento(idOrcamento,idCategoria);
        for(Imagem img : fromJPA){
            Imagem toJson = new Imagem();
            toJson.setImgId(img.getImgId());
            toJson.setImgDescricao(img.getImgDescricao());
            imgList.add(toJson);
        }
        return imgList;
    }

}
