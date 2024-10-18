/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Conta;
import model.IConta;
import model.Sistema;

/**
 * FXML Controller class
 *
 * @author Pedro Spindola
 */
public class HomeController implements Initializable {
    
    @FXML
    private TableView<IConta> tableView;
    @FXML
    private TableColumn<IConta, String> colunaNome;
    @FXML
    private TableColumn<IConta, Double> colunaSaldo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Sistema sistema = App.getSistema();
            Conta conta1 = new Conta(1, "NuBank", 0, 0);
            Conta conta2 = new Conta(2, "Inter", 0, 0);
            Conta conta3 = new Conta(3, "MercadoPago", 0, 0);
            sistema.adicionarContas(conta1);
            sistema.adicionarContas(conta2);
            sistema.adicionarContas(conta3);
            System.out.println(sistema.getContas());
            
            for (IConta conta : sistema.getContas()) {
                String intituicao = conta.getIntituicao();
                System.out.println(intituicao);
            }
            // Configura as colunas
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("intituicao"));
            colunaSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));

            // Vincula a lista de contas ao TableView
            tableView.setItems(FXCollections.observableArrayList(sistema.getContas()));
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch(Exception erro){
            System.out.println(erro.getMessage());
        }
    }    
}
