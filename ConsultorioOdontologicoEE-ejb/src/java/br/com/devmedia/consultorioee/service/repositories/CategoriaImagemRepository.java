package br.com.devmedia.consultorioee.service.repositories;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

public class CategoriaImagemRepository extends BasicRepository {

    private static final long serialVersionUID = 1L;

    public CategoriaImagemRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Categoriaimagem addCategoriaimagem(Categoriaimagem catImagem) {        
        return addEntity(Categoriaimagem.class, catImagem);
    }

    public Categoriaimagem setCategoriaimagem(Categoriaimagem catImagem) {        
        return setEntity(Categoriaimagem.class, catImagem);
    }

    public void removeCategoriaimagem(Categoriaimagem catImagem) {
        removeEntity(catImagem);
    }

    public Categoriaimagem getCategoriaimagem(int idOfCatImagem) {
        return getEntity(Categoriaimagem.class, idOfCatImagem);
    }
    
    public List<Categoriaimagem> getCategoriasDeImagem(){
        return getPureList(Categoriaimagem.class,"select ci from Categoriaimagem ci");
    }

    public int getCategoriaimagemCount() {
        Long toReturn = getPurePojo(Long.class,"select count(cat) from Categoriaimagem cat");
        if(toReturn != null) return toReturn.intValue();
        else return 0;
    }

    public List<Categoriaimagem> getCategoriaimagemByName(String localizar) {
        return getPureList(Categoriaimagem.class,"select ci from Categoriaimagem ci where ci.cigNome like ?1", localizar + "%");
    }

   
    

}
