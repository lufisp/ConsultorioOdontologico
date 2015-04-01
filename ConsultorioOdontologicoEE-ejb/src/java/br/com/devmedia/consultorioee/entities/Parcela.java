/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fernando
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parcela.findAll", query = "SELECT p FROM Parcela p")})
public class Parcela implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "par_id", nullable = false)
    private Integer parId;
    @Column(name = "par_numero")
    private Integer parNumero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "par_value", precision = 16, scale = 2)
    private BigDecimal parValue;
    @Column(name = "par_pago")
    private Boolean parPago;
    @JoinColumn(name = "par_orcamento", referencedColumnName = "orc_id")
    @ManyToOne
    private Orcamento parOrcamento;

    public Parcela() {
    }

    public Parcela(Integer parId) {
        this.parId = parId;
    }

    public Integer getParId() {
        return parId;
    }

    public void setParId(Integer parId) {
        this.parId = parId;
    }

    public Integer getParNumero() {
        return parNumero;
    }

    public void setParNumero(Integer parNumero) {
        this.parNumero = parNumero;
    }

    public BigDecimal getParValue() {
        return parValue;
    }

    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    public Boolean getParPago() {
        return parPago;
    }

    public void setParPago(Boolean parPago) {
        this.parPago = parPago;
    }

    public Orcamento getParOrcamento() {
        return parOrcamento;
    }

    public void setParOrcamento(Orcamento parOrcamento) {
        this.parOrcamento = parOrcamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parId != null ? parId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcela)) {
            return false;
        }
        Parcela other = (Parcela) object;
        if ((this.parId == null && other.parId != null) || (this.parId != null && !this.parId.equals(other.parId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.devmedia.consultorioee.entities.Parcela[ parId=" + parId + " ]";
    }
    
}
