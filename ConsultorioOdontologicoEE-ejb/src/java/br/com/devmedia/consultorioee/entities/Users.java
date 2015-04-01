/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.entities;

import br.com.devmedia.consultorioee.entities.validator.LoginPadrao;
import java.io.Serializable;
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
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Fernando
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_id", nullable = false)
    private Integer usuId;
    @Basic(optional = false)
    @NotNull
    @Length(min=3,message="Nome deve possuir pelo menos 3 caracteres")
    @Column(name = "usu_name", nullable = false, length = 255)
    private String usuName;
    @Basic(optional = false)
    @NotNull
    @Length(min=4,message="Login deve possuir pelo menos 4 caracteres")
    @Column(name = "usu_login", nullable = false, length = 255)
    @LoginPadrao(message = "Login fora do padrão.")
    private String usuLogin;
    @Basic(optional = false)
    @NotNull
    @Length(min=4,message="Senha deve possuir pelo menos 4 caracteres")
    @Column(name = "usu_password", nullable = false, length = 32)
    private String usuPassword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_administrator", nullable = false)
    private boolean usuAdministrator = false;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_dentist", nullable = false)
    private boolean usuDentist = true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orcDentist")
    private List<Orcamento> orcamentoList = new LinkedList<>();

    public Users() {
    }

    public Users(Integer usuId) {
        this.usuId = usuId;
    }

    public Users(Integer usuId, String usuName, String usuLogin, String usuPassword, boolean usuAdministrator, boolean usuDentist) {
        this.usuId = usuId;
        this.usuName = usuName;
        this.usuLogin = usuLogin;
        this.usuPassword = usuPassword;
        this.usuAdministrator = usuAdministrator;
        this.usuDentist = usuDentist;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuName() {
        return usuName;
    }

    public void setUsuName(String usuName) {
        this.usuName = usuName;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public boolean getUsuAdministrator() {
        return usuAdministrator;
    }

    public void setUsuAdministrator(boolean usuAdministrator) {
        this.usuAdministrator = usuAdministrator;
    }

    public boolean getUsuDentist() {
        return usuDentist;
    }

    public void setUsuDentist(boolean usuDentist) {
        this.usuDentist = usuDentist;
    }

    @XmlTransient
    public List<Orcamento> getOrcamentoList() {
        return orcamentoList;
    }

    public void setOrcamentoList(List<Orcamento> orcamentoList) {
        this.orcamentoList = orcamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.devmedia.consultorioee.entities.Users[ usuId=" + usuId + " ]";
    }
    
}
