/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Parcela;
import br.com.devmedia.consultorioee.service.FinanceService;
import br.com.devmedia.consultorioee.service.OrcamentoService;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Fernando
 */
@Named
@SessionScoped
public class FinanceControl extends BasicControl implements Serializable{
    
    @EJB
    private OrcamentoService orcamentoService;
    @Inject
    private OrcamentoControl orcamentoControl;
    @EJB
    private FinanceService financeService;
    
    private Parcela selectedParcela;    
    private List<Parcela> parcelas;
    private Customer selectedCustomer;

    public String doSendEmailWithInvoice(Customer customer, Parcela par) throws JRException, IOException{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Email enviado com sucesso","Email enviado com sucesso"));
        financeService.SendEmailTo(customer, par);
        return null;
    }
    
    public Parcela getSelectedParcela() {
        return selectedParcela;
    }

    public void setSelectedParcela(Parcela selectedParcela) {
        this.selectedParcela = selectedParcela;
    }
    
    public List<Parcela> getParcelas() {
        return parcelas;
    }
    
    public List<Parcela> getParcelas(Customer cus) {
        this.selectedCustomer = cus;
        cleanCache();
        return getParcelas();
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public void cleanCache() {
        setParcelas(financeService.getParcelasOfCustomerEmAberto(getSelectedCustomer().getCusId()));
    }
    
    public String doPagar(){
        financeService.setPagamentoParcela(selectedParcela.getParId());
        cleanCache();
        return "/restrito/orcamentos.faces";
    }
    
    public List<Customer> getCustomersComParcelaEmAberto(){
        return financeService.getClientesComParcelaEmAberto();
    }
    
    public String doImprimirBoleto() throws JRException, IOException{
        byte[] boleto = financeService.getPDF(getSelectedParcela());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType("application/pdf");
        ec.setResponseContentLength(boleto.length);
        ec.setResponseHeader("Content-Disposition","attachement; filename=\"" + getSelectedParcela().getParNumero() + "_" + getSelectedParcela().getParOrcamento().getOrcCustomer().getCusName()+".pdf\"");
        OutputStream output = ec.getResponseOutputStream();
        output.write(boleto);
        fc.responseComplete();
        return null;    
    }
}
