/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fernando
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orcamentoitem.findAll", query = "SELECT o FROM Orcamentoitem o")})
public class Orcamentoitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ori_id", nullable = false)
    private Integer oriId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ori_cost", nullable = false, precision = 16, scale = 2)
    private BigDecimal oriCost = BigDecimal.ZERO;
    @Lob
    @Size(max = 65535)
    @Column(name = "ori_obs", length = 65535)
    private String oriObs;
    @JoinColumn(name = "ori_orcamento", referencedColumnName = "orc_id", nullable = false)
    @ManyToOne(optional = false)
    private Orcamento oriOrcamento;
    @JoinColumn(name = "ori_service", referencedColumnName = "srv_id", nullable = false)
    @ManyToOne(optional = false)
    private Service oriService;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ori_qnt", nullable = false)
    private int oriQnt = 1;

    public Orcamentoitem() {
    }

    public Orcamentoitem(Integer oriId) {
        this.oriId = oriId;
    }

    public Orcamentoitem(Integer oriId, BigDecimal oriCost) {
        this.oriId = oriId;
        this.oriCost = oriCost;
    }

    public Integer getOriId() {
        return oriId;
    }

    public void setOriId(Integer oriId) {
        this.oriId = oriId;
    }

    public BigDecimal getOriCost() {
        return oriCost;
    }

    public BigDecimal getTotalItemParcial() {
        if (getOriService() != null) {
            return getOriService().getSrvCost().multiply(BigDecimal.valueOf(getOriQnt()));
        } else {
            return BigDecimal.ZERO;
        }
    }

    public void setOriCost(BigDecimal oriCost) {
        this.oriCost = oriCost;
    }

    public String getOriObs() {
        return oriObs;
    }

    public void setOriObs(String oriObs) {
        this.oriObs = oriObs;
    }

    public Orcamento getOriOrcamento() {
        return oriOrcamento;
    }

    public void setOriOrcamento(Orcamento oriOrcamento) {
        this.oriOrcamento = oriOrcamento;
    }

    public Service getOriService() {
        return oriService;
    }

    public void setOriService(Service oriService) {
        this.oriService = oriService;
    }

    public int getOriQnt() {
        return oriQnt;
    }

    public void setOriQnt(int oriQnt) {
        this.oriQnt = oriQnt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oriId != null ? oriId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orcamentoitem other = (Orcamentoitem) obj;
        if (!Objects.equals(this.oriId, other.oriId)) {
            return false;
        }
        if (!Objects.equals(this.oriCost, other.oriCost)) {
            return false;
        }
        if (!Objects.equals(this.oriObs, other.oriObs)) {
            return false;
        }
        if (!Objects.equals(this.oriOrcamento, other.oriOrcamento)) {
            return false;
        }
        if (!Objects.equals(this.oriService, other.oriService)) {
            return false;
        }
        if (this.oriQnt != other.oriQnt) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "br.com.devmedia.consultorioee.entities.Orcamentoitem[ oriId=" + oriId + " ]";
    }

}
