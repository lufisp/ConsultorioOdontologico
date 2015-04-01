/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "categoriaimagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriaimagem.findAll", query = "SELECT c FROM Categoriaimagem c"),
    @NamedQuery(name = "Categoriaimagem.findByCigId", query = "SELECT c FROM Categoriaimagem c WHERE c.cigId = :cigId"),
    @NamedQuery(name = "Categoriaimagem.findByCigNome", query = "SELECT c FROM Categoriaimagem c WHERE c.cigNome = :cigNome")})
public class Categoriaimagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cig_id")
    private Integer cigId;
    @Basic(optional = false)
    @Column(name = "cig_nome")
    private String cigNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imgCategoria")
    private List<Imagem> imagemCollection;

    public Categoriaimagem() {
    }

    public Categoriaimagem(Integer cigId) {
        this.cigId = cigId;
    }

    public Categoriaimagem(Integer cigId, String cigNome) {
        this.cigId = cigId;
        this.cigNome = cigNome;
    }

    public Integer getCigId() {
        return cigId;
    }

    public void setCigId(Integer cigId) {
        this.cigId = cigId;
    }

    public String getCigNome() {
        return cigNome;
    }

    public void setCigNome(String cigNome) {
        this.cigNome = cigNome;
    }

    @XmlTransient
    public List<Imagem> getImagemCollection() {
        return imagemCollection;
    }

    public void setImagemCollection(List<Imagem> imagemCollection) {
        this.imagemCollection = imagemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cigId != null ? cigId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriaimagem)) {
            return false;
        }
        Categoriaimagem other = (Categoriaimagem) object;
        if ((this.cigId == null && other.cigId != null) || (this.cigId != null && !this.cigId.equals(other.cigId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.devmedia.consultorioee.entities.Categoriaimagem[ cigId=" + cigId + " ]";
    }
    
}
