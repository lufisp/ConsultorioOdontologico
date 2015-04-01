/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Anaminese;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Orcamentoitem;
import br.com.devmedia.consultorioee.entities.PaymentType;
import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.FinanceService;
import br.com.devmedia.consultorioee.service.OrcamentoService;
import br.com.devmedia.consultorioee.service.ServiceService;
import br.com.devmedia.consultorioee.service.UserService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Fernando
 */
@Named
@SessionScoped
public class OrcamentoControl extends BasicControl implements Serializable {

    @EJB
    private OrcamentoService orcamentoService;
    @Inject
    private AnamineseControl anamneseControl;
    @Inject
    private FinanceControl financeControl;
    @EJB
    private UserService userService;
    @EJB
    private ServiceService serviceService;
    @EJB
    private FinanceService financeService;

    private Orcamento selectedOrcamento;
    private Customer selectedCustomer;
    private Orcamentoitem selectedOrcamentoItem;
    private List<Orcamento> orcamentos;

    public Orcamentoitem getSelectedOrcamentoItem() {
        return selectedOrcamentoItem;
    }

    public void setSelectedOrcamentoItem(Orcamentoitem selectedOrcamentoItem) {
        this.selectedOrcamentoItem = selectedOrcamentoItem;
    }

    public Orcamento getSelectedOrcamento() {
        return selectedOrcamento;
    }

    public void setSelectedOrcamento(Orcamento selectedOrcamento) {
        this.selectedOrcamento = selectedOrcamento;
    }

    public List<Orcamento> getOrcamentos() {
        int debug = 1;
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public String doStartAtenderCliente(Customer customer) {
        setSelectedCustomer(customer);
        anamneseControl.setSelectedCustomer(customer);
        anamneseControl.cleanCache();
        financeControl.setSelectedCustomer(customer);
        financeControl.cleanCache();
        cleanCache();
        return "/restrito/orcamentos.faces";
    }

    private void cleanCache() {
        setSelectedOrcamento(new Orcamento());
        getSelectedOrcamento().setOrcCustomer(getSelectedCustomer());
        setOrcamentos(orcamentoService.getOrcamentos(getSelectedCustomer().getCusId()));
    }

    public String getItensOfOrcamento(Orcamento orc) {
        StringBuilder sb = new StringBuilder();
        for (Orcamentoitem item : orc.getOrcamentoitemList()) {
            sb.append(item.getOriService().getSrvName());
            sb.append("  ");
        }
        return sb.toString();
    }

    public String doStartOrcamentoComUmaAnamnese(Anaminese ans) {
        cleanCache();
        getSelectedOrcamento().setOrcAnaminese(ans);
        ans.setAnsOrcamento(getSelectedOrcamento());
        return "/restrito/addOrcamento.faces";
    }

    public List<Users> getDentistas() {
        return userService.getDentistUsers();
    }

    public List<PaymentType> getPaymentTypes() {
        return Arrays.asList(PaymentType.values());
    }

    public List<Service> getServices() {
        return serviceService.getServices();
    }

    public String doStartAddItemAoOrcamento() {
        selectedOrcamentoItem = new Orcamentoitem();
        selectedOrcamentoItem.setOriOrcamento(selectedOrcamento);
        return "/restrito/addOrcamentoItem.faces";
    }
    
    public String doStartAddItemAoOrcamentoEdit() {
        selectedOrcamentoItem = new Orcamentoitem();
        selectedOrcamentoItem.setOriOrcamento(selectedOrcamento);
        return "/restrito/addOrcamentoItemEdit.faces";
    }

    public String doFinishAddOrcamentoItem() {
        selectedOrcamentoItem.setOriCost(selectedOrcamentoItem.getTotalItemParcial());
        selectedOrcamento.getOrcamentoitemList().add(selectedOrcamentoItem);
        recalculaSaldoOrcamento();    
        return "/restrito/addOrcamento.faces";
    }
    
     public String doFinishAddOrcamentoItemEdit() {
        selectedOrcamentoItem.setOriCost(selectedOrcamentoItem.getTotalItemParcial());
        selectedOrcamento.getOrcamentoitemList().add(selectedOrcamentoItem);
        recalculaSaldoOrcamento();        
        return "/restrito/editOrcamento.faces";
    }

    public String doFinishExcluirItem() {
        int i = 1;
        List<Orcamentoitem> listaOrcItem = selectedOrcamento.getOrcamentoitemList();
        selectedOrcamento.getOrcamentoitemList().remove(selectedOrcamentoItem);
        listaOrcItem.remove(selectedOrcamentoItem);
        recalculaSaldoOrcamento();
        return "/restrito/addOrcamento.faces";
    }
    
     public String doFinishExcluirItemEdit() {
        int i = 1;
        List<Orcamentoitem> listaOrcItem = selectedOrcamento.getOrcamentoitemList();
        selectedOrcamento.getOrcamentoitemList().remove(selectedOrcamentoItem);
        listaOrcItem.remove(selectedOrcamentoItem);
        recalculaSaldoOrcamento();
        return "/restrito/editOrcamento.faces";
    }

    public void recalculaSaldoOrcamento() {
        BigDecimal total = BigDecimal.ZERO;
        for (Orcamentoitem item : selectedOrcamento.getOrcamentoitemList()) {
            total = total.add(item.getTotalItemParcial());
        }
        selectedOrcamento.setOrcTotal(total);

    }
    
    public String doStartAlterarItem(){
        return "/restrito/editOrcamentoItem.faces";
    }
    
    public String doStartAlterarItemEdit(){
        return "/restrito/editOrcamentoItemEdit.faces";
    }
    
    public String doFinishEditOrcamentoItem(){
        if(selectedOrcamentoItem.getOriId() != null){
            orcamentoService.setItem(selectedOrcamentoItem);
        }
        recalculaSaldoOrcamento();
        return "/restrito/addOrcamento.faces";
    }
    
     public String doFinishEditOrcamentoItemEdit(){
        if(selectedOrcamentoItem.getOriId() != null){
            orcamentoService.setItem(selectedOrcamentoItem);
        }
        recalculaSaldoOrcamento();
        return "/restrito/editOrcamento.faces";
    }
    
    public String doFinishAddOrcamento(){
        selectedOrcamento.setOrcCustomer(selectedCustomer);
        orcamentoService.addOrcamento(selectedOrcamento);
        cleanCache();
        financeControl.cleanCache();
        return "/restrito/orcamentos.faces";        
    }
    
    public String doFinishExcluir(){
        orcamentoService.removeOrcamento(selectedOrcamento);
        cleanCache();
        return "/restrito/orcamentos.faces";
    }
    
    public String doStartAlterar(){
        return "/restrito/editOrcamento.faces";
    }
    
    public String doFinishAlterar(){
        orcamentoService.setOrcamento(selectedOrcamento);
        cleanCache();
        financeControl.cleanCache();
        return "/restrito/orcamentos.faces";
    }
    
    public String doCancelar(){
        cleanCache();
        return "/restrito/orcamentos.faces";
    }
    
    public String doViewImages(){
        return "/restrito/images.faces";
    }
}
