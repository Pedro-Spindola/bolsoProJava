/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Sistema;
import model.SistemaLogin;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Pedro Spindola
 */
public class LoginController implements Initializable {

    private SistemaLogin sistemaLogin;
    
    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonFechar;
    @FXML
    private TextField  usuario;
    @FXML
    private TextField  senha;
    @FXML
    private Label labelError;
    @FXML
    private void loginButtonClicked(ActionEvent event){
        try{
            Sistema sistema = App.getSistema();
            String usuarioInput = usuario.getText();
            String senhaInput = senha.getText();
            boolean autenticado = false;
            for (Usuario user : sistemaLogin.listarUsuarios()) {
                if (user.getNome().equals(usuarioInput) && user.getSenha().equals(senhaInput)){
                    autenticado = true;
                    sistema.setUsuario(user);
                } break;
            }
            if (autenticado){
                labelError.setText("Login autenticado, entrando...");
                Stage stage = (Stage) labelError.getScene().getWindow();
                App.setRoot("home", stage);
            }
            else labelError.setText("Usuário ou Senha inválidos.");
        } catch(Exception erro){
            System.out.println(erro.getMessage());
        }
    }
    @FXML
    private void fecharJanelaButtonClicked(ActionEvent event){
        try{
            Stage stage = (Stage) buttonFechar.getScene().getWindow();
            stage.close();
        } catch(Exception erro){
            System.out.println(erro.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            sistemaLogin = new SistemaLogin();
            sistemaLogin.adicionarUsuario(new Usuario("pedro@gmail.com", "12345678", "Pedro Spíndola"));
        } catch(Exception erro){
            System.out.println(erro.getMessage());
        }

    }    
    
}
