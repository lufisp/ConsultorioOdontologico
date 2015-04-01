/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;



import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.UserService;
import br.com.devmedia.consultorioee.service.exceptions.AcessoInvalidoException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.print.attribute.standard.Severity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author Fernando
 */

/*
    As anotação abaixo indica a criação de um CDI Bean, que eu vou poder utilizar 
    em outro ponto da aplicação, com a injeção de dependencia....por exemplo, 
    na página login.xhtml, a linha: 
        <td><h:inputText value="#{userControl.userName}" /></td>
    está injetando o CDI Bean UserControl na aplicação...
*/
@Named
@SessionScoped
public class UserControl extends BasicControl implements Serializable{
    
    //annotation abaixo faz a injeção de dependencia do entrerprise java bean...
    @EJB    
    private UserService userService;
   
    private Users loggedUser;
    
    @NotNull(message = "Você deve especificar o usuário.")
    @NotEmpty(message = "Você deve especificar o usuário.")
    private String userName;
    
    @NotNull(message = "Você deve especificar uma senha.")
    @NotEmpty(message = "Você deve especificar uma senha.")
    @Length(min = 3, message = "Sua senha deve contar mais do que 2 caracteres.")
    private String password;
    
  
    private String localizar;
    private List<Users> usrFiltrado;
    
    private Users usuarioSelected;

    public Users getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Users usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    
    
    public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String doLogin(){
        loggedUser = null;
        loggedUser = userService.getUserByLoginPassword(userName, password);       
        if(loggedUser == null){
            createFacesErrorMessage("Usuário / senha inválidos");            
            return "/login.faces";
        } else{
            //para não ficar com o endereço da ultima página no browser,
            //posso utilizar a propriedade faces-redirect=true.
            return "/restrito/index.faces?faces-redirect=true";
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getLocalizar() {
        return localizar;
    }

    public void setLocalizar(String localizar) {
        this.localizar = localizar;
    }

    public List<Users> getUsrFiltrado() {
        //qdo carrega a página, lista todos os usr...
        if(usrFiltrado == null){
            localizar = "";
            this.doLocalizar();
        }
        return usrFiltrado;
    }

    public void setUsrFiltrado(List<Users> usrFiltrado) {
        this.usrFiltrado = usrFiltrado;
    }
    
    
    public String doLocalizar(){
        usrFiltrado = userService.getUsersByName(getLocalizar());
        return "users.faces";
    }
    
    
    public List<Users> getUsers(){
        return userService.getUsers();
    }
    
    public String doStartAddUsuario(){
        setUsuarioSelected(new Users());
        return "/restrito/addUser.faces";
    }
    
    public String doFinishAddUsuario(){
        setUsrFiltrado(null);
        try {
            userService.addUser(usuarioSelected);
        } catch (AcessoInvalidoException ex) {
            createFacesErrorMessage(ex.getMessage());            
            return "/restrito/addUser.faces";
        }
        return "/restrito/users.faces";
    }
    
    public String doFinishExcluir(){
        setUsrFiltrado(null);
        if(usuarioSelected.equals(loggedUser)){
            createFacesErrorMessage("Não é possivel apagar o usuário logado");            
            return "/restrito/users.faces";
        }
        userService.removeUser(usuarioSelected);
        return "/restrito/users.faces";
    }
    
    public String doStartAlterar(){
        return "/restrito/editUser.faces";
    }
    
    public String doFinishAlterar(){
        setUsrFiltrado(null);
        try {
            userService.setUser(usuarioSelected);
        } catch (AcessoInvalidoException ex) {
            createFacesErrorMessage(ex.getMessage());            
            return "/restrito/editUser.faces";
        }
        return "/restrito/users.faces";
    }
    
    public String doStartAlterarSenha(){
        //faço o comando abaixo para não aparecer a string md5 na tela...
        getUsuarioSelected().setUsuPassword("");
        return "/restrito/editUserPassword.faces";
    }
    
     public String doFinishAlterarSenha(){
        setUsrFiltrado(null);
        userService.setPassword(usuarioSelected.getUsuId(),usuarioSelected.getUsuPassword());
        return "/restrito/users.faces";
    }
    
    
}
