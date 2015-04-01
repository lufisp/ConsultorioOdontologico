/*
 * Copyright (C) 2014 dyego.carmo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Parcela;
import br.com.devmedia.consultorioee.service.repositories.FinanceRepository;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Schedule;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.tools.ant.taskdefs.SendEmail;

/**
 *
 * @author dyego.carmo
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FinanceService extends BasicService {
    
    @Resource(mappedName = "mail/gmailSMTP")
    private Session gmailSMTP;
    
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
    private EntityManager em;
    private FinanceRepository financeRepository;
    
    @EJB
    private CustomerService customerService;
    
    @PostActivate
    @PostConstruct
    private void postConstruct() {
        financeRepository = new FinanceRepository(em);
    }
    
    public Parcela addParcela(Parcela par) {
        return financeRepository.addParcela(par);
    }
    
    public Parcela getParcela(Integer idOfParcela) {
        return financeRepository.getParcela(idOfParcela);
    }
    
    public Parcela setParcela(Parcela par) {
        return financeRepository.setParcela(par);
    }
    
    public void removeParcela(Parcela par) {
        financeRepository.removeParcela(par);
    }
    
    public List<Parcela> getParcelasByOrcamento(int orcamentoId) {
        return financeRepository.getParcelasOfOrcamento(orcamentoId);
    }
    
    public List<Parcela> getParcelasOfOrcamentoPagas(int orcamentoId) {
        return financeRepository.getParcelasOfOrcamentoPagas(orcamentoId);
    }
    
    public List<Parcela> getParcelasOfOrcamentoEmAberto(int orcamentoId) {
        return financeRepository.getParcelasOfOrcamentoEmAberto(orcamentoId);
    }
    
    public List<Parcela> getParcelasOfCustomer(int customerId) {
        return financeRepository.getParcelasOfCustomer(customerId);
    }
    
    public List<Parcela> getParcelasOfCustomerPagas(int orcamentoId) {
        return financeRepository.getParcelasOfCustomerPagas(orcamentoId);
    }
    
    public List<Parcela> getParcelasOfCustomerEmAberto(int orcamentoId) {
        return financeRepository.getParcelasOfCustomerEmAberto(orcamentoId);
    }
    
    public Parcela setPagamentoParcela(int idOfParcela) {
        return financeRepository.setPagamentoParcela(idOfParcela);
    }
    
    public void eliminarParcelas(Orcamento orc) {
        financeRepository.eliminarParcelas(orc);
    }
    
    public void parcelaOrcamento(Orcamento orc) {
        float valorParcela = Math.round(orc.getOrdTotal().floatValue() / orc.getOrcTimes().intValue());
        for (int i = 0; i < orc.getOrcTimes(); i++) {
            Parcela par = new Parcela();
            par.setParNumero(i + 1);
            par.setParOrcamento(orc);
            par.setParPago(false);
            if ((i + 1) == orc.getOrcTimes()) {
                float valorUltimaParcela = orc.getOrdTotal().floatValue() - (valorParcela * i);
                par.setParValue(BigDecimal.valueOf(valorUltimaParcela));
            } else {
                par.setParValue(BigDecimal.valueOf(valorParcela));
            }
            addParcela(par);
        }
    }
    
    public List<Customer> getClientesComParcelaEmAberto() {
        return financeRepository.getParcelasOfOrcamentoEmAberto();
    }
    
    public byte[] getPDF(Parcela par) throws JRException {
        String codigoBarras = "2548799876521354548795";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("codigobarras", codigoBarras);
        parameters.put("parcela", par);
        //vá para o diretório que se encontra o FinanceService, pegue o documento chamado boletoParcela.jrxml e compile...
        JasperReport jr = JasperCompileManager.compileReport(FinanceService.class.getResourceAsStream("boletoParcela.jrxml"));
        JasperPrint jp = JasperFillManager.fillReport(jr, parameters);
        byte[] toReturn = JasperExportManager.exportReportToPdf(jp);
        return toReturn;
    }
    
    @Schedule(hour = "*", minute = "5,35", persistent = false)
    public void enviaBoletosPorEmail() throws JRException, IOException {
        List<Customer> customers = customerService.getCustomerByName("%");
        for (Customer customer : customers) {
            List<Parcela> parcelas = getParcelasOfCustomer(customer.getCusId());
            for (Parcela parcela : parcelas) {
                if (!parcela.getParPago()) {
                    try {
                        SendEmailTo(customer, parcela);
                    } catch (Exception e) {
                        System.out.println("Erro sending Email to Customer");
                    }
                    break;
                }
            }
        }
    }
    
    public void SendEmailTo(Customer customer, Parcela parcela) throws JRException, IOException {
        System.out.println("Chegou a solicitação para: " + customer.getCusName() + " para a parcela: " + parcela.getParNumero());
        byte[] pdfBoleto;
        pdfBoleto = getPDF(parcela);
        //pdfBoleto = null;
        InputStream stream = FinanceService.class.getResourceAsStream("invoice.html");   
       
        byte[] invoiceBytes = new byte[stream.available()];
        stream.read(invoiceBytes);
        stream.close();
        String body = new String(invoiceBytes,"UTF-8");
        body = body.replaceAll("@@@NOME_CLIENTE@@@", customer.getCusName());
        body = body.replaceAll("@@@PARCELA_NUMERO@@@", parcela.getParNumero() + "");
        body = body.replaceAll("@@@PARCELA_DATA@@@", new SimpleDateFormat("dd/MM/yyy").format(new Date()) + "");
        body = body.replaceAll("@@@PARCELA_VALOR@@@", new DecimalFormat("#0.00").format(parcela.getParValue().floatValue()));
        body = body.replaceAll("@@@NOME_USUARIO@@@", parcela.getParOrcamento().getOrcDentist().getUsuName());
        System.out.println("See the object " + gmailSMTP);
        //System.out.println("body = " + body);

        //Se não estiver enviando e-mail, não esquecer de verificar as opções do anti-virus e firewall...
        //opção 1: envar mensagem viaJavaMail, configurado no Glassfish
        callGlassfishJavaMail(body,pdfBoleto,customer);
        //opção2: configurar no código o javaMail
        //callDirectJavaMailAPI(body, pdfBoleto, customer);
        
    }
    
    private void callGlassfishJavaMail(String body, byte[] pdfBoleto, Customer customer) {
        Message msg = new MimeMessage(gmailSMTP);
        Address mailTo;
        try {
            Multipart multipart = new MimeMultipart();
            mailTo = new InternetAddress("lufisp@gmail.com");
            msg.setRecipient(Message.RecipientType.TO, mailTo);
            msg.setFrom(new InternetAddress("lufisp@gmail.com"));
            msg.setSubject("Consultório EE - Email");
            //a mensagem
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html; charset=ISO-8859-1");
            multipart.addBodyPart(messageBodyPart);
            //anexar o arquivo
            BodyPart boletoBodyPart = new MimeBodyPart();
            boletoBodyPart.setFileName("boleto.pdf");
            boletoBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(pdfBoleto, "application/pdf")));
            multipart.addBodyPart(boletoBodyPart);

            //anexar multipart
            msg.setContent(multipart);
            //enviar a mensagem...
            Transport.send(msg);
            
        } catch (MessagingException ex) {
            Logger.getLogger(FinanceService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Impossível enviar e-mail para " + customer.getCusName());
        }        
        
    }
    
    private void callDirectJavaMailAPI(String body, byte[] pdfBoleto, Customer customer) {
        //session Configuration...
        final String username = "lufisp@gmail.com";
        final String password = "laikinha45";        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.startls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
        session.setDebug(true);
        try {
            Message msg = new MimeMessage(session);
            Address mailTo;
            Multipart multipart = new MimeMultipart();
            mailTo = new InternetAddress("lufisp@gmail.com");
            msg.setRecipient(Message.RecipientType.TO, mailTo);
            msg.setFrom(new InternetAddress("lufisp@gmail.com"));
            msg.setSubject("Consultório EE - Email");
            //a mensagem
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html; charset=ISO-8859-1");
            multipart.addBodyPart(messageBodyPart);
            //anexar o arquivo
            BodyPart boletoBodyPart = new MimeBodyPart();
            boletoBodyPart.setFileName("boleto.pdf");
            boletoBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(pdfBoleto, "application/pdf")));
            multipart.addBodyPart(boletoBodyPart);

            //anexar multipart
            msg.setContent(multipart);
            //enviar a mensagem...
            Transport.send(msg);
            
        } catch (MessagingException ex) {
            Logger.getLogger(FinanceService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Impossível enviar e-mail para " + customer.getCusName());
        }        
        
    }
    
}
