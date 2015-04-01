/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cus_id", nullable = false)
    private Integer cusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50, message="O nome do cliente deve ser informado.")
    @Column(name = "cus_name", nullable = false, length = 255)
    private String cusName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cus_age", nullable = false)
    private int cusAge;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255,message="O endereço deve ser fornecido.")
    @Column(name = "cus_address", nullable = false, length = 255)
    private String cusAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 2,message="O campo estado não pode ser vazio.")
    @Column(name = "cus_state", nullable = false, length = 2)
    private String cusState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255,message="A cidade deve ser informada.")
    @Column(name = "cus_city", nullable = false, length = 255)
    private String cusCity;
    @Lob
    @Size(max = 65535)
    @Column(name = "cus_complement", length = 65535)
    private String cusComplement;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255,message="O nome do pai deve ser informado.")
    @Column(name = "cus_father", nullable = false, length = 255)
    private String cusFather;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255,message="O nome da mãe deve ser informado.")
    @Column(name = "cus_mother", nullable = false, length = 255)
    private String cusMother;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535,message= "A observação do cliente deve ser informada.")
    @Column(name = "cus_obs", nullable = false, length = 65535)
    private String cusObs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45,message="A ocupação deve ser informada.")
    @Column(name = "cus_ocupation", nullable = false, length = 45)
    private String cusOcupation;
    @Size(max = 45)
    @Column(name = "cus_workNumber", length = 45)
    private String cusworkNumber;
    @Size(max = 15)
    @Column(name = "cus_celNumber", length = 15)
    private String cuscelNumber;
    @Size(max = 45)
    @Column(name = "cus_workName", length = 45)
    private String cusworkName;
    @Size(max = 255)
    @Column(name = "cus_workAddress", length = 255)
    private String cusworkAddress;
    @Lob
    @Size(max = 65535)
    @Column(name = "cus_workObs", length = 65535)
    private String cusworkObs;
    @Size(max = 255)
    @Column(name = "cus_telephone", length = 255)
    private String cusTelephone;
    @Basic(optional = false)
    @NotNull(message = "A data de nascimento deve ser informada.")
    @Column(name = "cus_borndate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date cusBorndate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orcCustomer")
    private List<Orcamento> orcamentoList  = new LinkedList<>();
    @OneToMany(mappedBy = "ansCustomer")
    private List<Anaminese> anamineseList  = new LinkedList<>();

    //O annotation abaixo, informa que antes de persistir o registro, ou atualizá-lo, o EE7 roda o método abaixo...
    @PrePersist
    @PreUpdate
    public void updateAge(){
        setCusAge(getIdade(getCusBorndate()));
    }
    
     public int getIdade(Date dataNascimento){
        Calendar nascimento = new GregorianCalendar();
        nascimento.setTime(dataNascimento);
        Calendar hoje = Calendar.getInstance();
        int age = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        nascimento.add(Calendar.YEAR,age);
        if(hoje.before(nascimento)){
            age--;
        }
        return age;
    }
    
    
    public Customer() {
    }

    public Customer(Integer cusId) {
        this.cusId = cusId;
    }
    
    public void addOrcamento(Orcamento orc){
        orc.setOrcCustomer(this);
        getOrcamentoList().add(orc);
    }
    
    public void addAnaminese(Anaminese anam){
        anam.setAnsCustomer(this);
        getAnamineseList().add(anam);
    }

    public Customer(Integer cusId, String cusName, int cusAge, String cusAddress, String cusState, String cusCity, String cusFather, String cusMother, String cusObs, String cusOcupation, Date cusBorndate) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusAge = cusAge;
        this.cusAddress = cusAddress;
        this.cusState = cusState;
        this.cusCity = cusCity;
        this.cusFather = cusFather;
        this.cusMother = cusMother;
        this.cusObs = cusObs;
        this.cusOcupation = cusOcupation;
        this.cusBorndate = cusBorndate;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public int getCusAge() {
        return cusAge;
    }

    public void setCusAge(int cusAge) {
        this.cusAge = cusAge;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusState() {
        return cusState;
    }

    public void setCusState(String cusState) {
        this.cusState = cusState;
    }

    public String getCusCity() {
        return cusCity;
    }

    public void setCusCity(String cusCity) {
        this.cusCity = cusCity;
    }

    public String getCusComplement() {
        return cusComplement;
    }

    public void setCusComplement(String cusComplement) {
        this.cusComplement = cusComplement;
    }

    public String getCusFather() {
        return cusFather;
    }

    public void setCusFather(String cusFather) {
        this.cusFather = cusFather;
    }

    public String getCusMother() {
        return cusMother;
    }

    public void setCusMother(String cusMother) {
        this.cusMother = cusMother;
    }

    public String getCusObs() {
        return cusObs;
    }

    public void setCusObs(String cusObs) {
        this.cusObs = cusObs;
    }

    public String getCusOcupation() {
        return cusOcupation;
    }

    public void setCusOcupation(String cusOcupation) {
        this.cusOcupation = cusOcupation;
    }

    public String getCusworkNumber() {
        return cusworkNumber;
    }

    public void setCusworkNumber(String cusworkNumber) {
        this.cusworkNumber = cusworkNumber;
    }

    public String getCuscelNumber() {
        return cuscelNumber;
    }

    public void setCuscelNumber(String cuscelNumber) {
        this.cuscelNumber = cuscelNumber;
    }

    public String getCusworkName() {
        return cusworkName;
    }

    public void setCusworkName(String cusworkName) {
        this.cusworkName = cusworkName;
    }

    public String getCusworkAddress() {
        return cusworkAddress;
    }

    public void setCusworkAddress(String cusworkAddress) {
        this.cusworkAddress = cusworkAddress;
    }

    public String getCusworkObs() {
        return cusworkObs;
    }

    public void setCusworkObs(String cusworkObs) {
        this.cusworkObs = cusworkObs;
    }

    public String getCusTelephone() {
        return cusTelephone;
    }

    public void setCusTelephone(String cusTelephone) {
        this.cusTelephone = cusTelephone;
    }

    public Date getCusBorndate() {
        return cusBorndate;
    }

    public void setCusBorndate(Date cusBorndate) {
        this.cusBorndate = cusBorndate;
    }

    @XmlTransient
    public List<Orcamento> getOrcamentoList() {
        return orcamentoList;
    }

    public void setOrcamentoList(List<Orcamento> orcamentoList) {
        this.orcamentoList = orcamentoList;
    }

    @XmlTransient
    public List<Anaminese> getAnamineseList() {
        return anamineseList;
    }

    public void setAnamineseList(List<Anaminese> anamineseList) {
        this.anamineseList = anamineseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cusId != null ? cusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.cusId == null && other.cusId != null) || (this.cusId != null && !this.cusId.equals(other.cusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.devmedia.consultorioee.entities.Customer[ cusId=" + cusId + " ]";
    }
    
}
