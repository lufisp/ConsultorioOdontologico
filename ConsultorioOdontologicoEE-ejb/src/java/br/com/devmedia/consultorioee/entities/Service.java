/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.entities;

import br.com.devmedia.consultorioee.entities.validator.DescricaoServicoUnico;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fernando
 */
@Entity
@XmlRootElement
@DescricaoServicoUnico
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")})
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "srv_id", nullable = false)
    private Integer srvId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "srv_name", nullable = false, length = 255)
    private String srvName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "srv_cost", nullable = false, precision = 16, scale = 2)
    private BigDecimal srvCost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oriService")
    private List<Orcamentoitem> orcamentoitemList  = new LinkedList<>();

    public Service() {
    }

    public Service(Integer srvId) {
        this.srvId = srvId;
    }

    public Service(Integer srvId, String srvName, BigDecimal srvCost) {
        this.srvId = srvId;
        this.srvName = srvName;
        this.srvCost = srvCost;
    }

    public Integer getSrvId() {
        return srvId;
    }

    public void setSrvId(Integer srvId) {
        this.srvId = srvId;
    }

    public String getSrvName() {
        return srvName;
    }

    public void setSrvName(String srvName) {
        this.srvName = srvName;
    }

    public BigDecimal getSrvCost() {
        return srvCost;
    }

    public void setSrvCost(BigDecimal srvCost) {
        this.srvCost = srvCost;
    }

    @XmlTransient
    public List<Orcamentoitem> getOrcamentoitemList() {
        return orcamentoitemList;
    }

    public void setOrcamentoitemList(List<Orcamentoitem> orcamentoitemList) {
        this.orcamentoitemList = orcamentoitemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (srvId != null ? srvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.srvId == null && other.srvId != null) || (this.srvId != null && !this.srvId.equals(other.srvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.devmedia.consultorioee.entities.Service[ srvId=" + srvId + " ]";
    }
    
}
