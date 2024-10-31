package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import model.Conta;
import model.Lancamentos;
import model.Sistema;

/**
 * @author Pedro Spindola
 * @date 13/10/2024
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Sistema sistema;
    
    @Override
    public void start(Stage stage) throws IOException {
        try {
            scene = new Scene(loadFXML("login"));
            //scene = new Scene(loadFXML("login"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Login"); 
            stage.setResizable(false);
            stage.show();
        } catch(Exception erro){
            System.out.println(erro.getMessage());
        }
    }

    static void setRoot(String fxml, Stage stage) throws IOException {
        scene.setRoot(loadFXML(fxml));
        stage.setMaximized(true);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        try{
            sistema = new Sistema();
            /*
            // CRIADO PARA TESTE DO SISTEMA
            System.out.println("Criando minha conta");
            Conta conta1 = new Conta(sistema.idParaUmaNovaConta(), "Nubank", 1200, 7000, true, 2500, 15, 20);
            Conta conta2 = new Conta(sistema.idParaUmaNovaConta(), "Inter", 800, 5000, true, 1200, 15, 20);
            Conta conta3 = new Conta(sistema.idParaUmaNovaConta(), "MercadoPago", 300, 1000);
            sistema.adicionarConta(conta1);
            sistema.adicionarConta(conta2);
            sistema.adicionarConta(conta3);
            sistema.mostrarContas();
            
            System.out.println("Começas a lanças meus lançamentos. \n");
            
            System.out.println("Lancamentos 01");
            Lancamentos lanca1 = new Lancamentos("Abastecimento.", 200, "MercadoPago", false, "Transporte", "", 1, null);
            sistema.adicionarLancamento(lanca1);
            System.out.println("Lancamentos 02");    
            Lancamentos lanca2 = new Lancamentos("Cafe da manhã.", 300, "Inter", true, "Supermercado", "", 1, null);
            sistema.adicionarLancamento(lanca2);
            System.out.println("Lancamentos 03");
            Lancamentos lanca3 = new Lancamentos("Viagem", 1000, "Nubank", true, "Diversão", "", 3, null);
            sistema.adicionarLancamento(lanca3);
            
            sistema.mostrarLancamentos();
            sistema.mostrarContas();
            
            Lancamentos lanca4 = new Lancamentos("Abastecimento.", 100, "MercadoPago", false, "Transporte", "", 1, null);   
            Lancamentos lanca5 = new Lancamentos("Cafe da manhã.", 50, "Inter", true, "Supermercado", "", 1, null);
            Lancamentos lanca6 = new Lancamentos("Viagem", 1500, "Nubank", true, "Diversão", "", 3, null);
            
            System.out.println("\nEditar lançamentos.");
            
            sistema.editarLancamentos(lanca4, lanca1);
            sistema.editarLancamentos(lanca5, lanca2);
            sistema.editarLancamentos(lanca6, lanca3);
            
            sistema.mostrarLancamentos();
            sistema.mostrarContas();
            
            System.out.println("\nRemover lançamentos.");
            sistema.removerLancamentos(lanca1);
            sistema.removerLancamentos(lanca2);
            sistema.removerLancamentos(lanca3);
            
            sistema.mostrarLancamentos();
            sistema.mostrarContas();
            // Encerrar a execução com sucesso
            System.exit(0);
            */
            Conta conta1 = new Conta(sistema.idParaUmaNovaConta(), "Nubank", 1200, 7000, true, 2500, 28, 30);
            Conta conta2 = new Conta(sistema.idParaUmaNovaConta(), "Inter", 800, 5000, true, 1200, 15, 20);
            Conta conta3 = new Conta(sistema.idParaUmaNovaConta(), "MercadoPago", 300, 1000);
            sistema.adicionarConta(conta1);
            sistema.adicionarConta(conta2);
            sistema.adicionarConta(conta3);
            
            Lancamentos lanca1 = new Lancamentos("Abastecimento.", -200, "MercadoPago", false, "Transporte", "", 1, null);
            Lancamentos lanca2 = new Lancamentos("Compra Semana", -220, "Inter", true, "Supermercado", "", 1, null);
            Lancamentos lanca3 = new Lancamentos("Cafe da manhã.", -300, "Inter", true, "Padaria", "", 1, null);
            Lancamentos lanca4 = new Lancamentos("Compra Semana", -140, "Inter", true, "Supermercado", "", 1, null);
            Lancamentos lanca5 = new Lancamentos("Abastecimento", -300, "Inter", true, "Transporte", "", 2, null);
            Lancamentos lanca6 = new Lancamentos("Viagem", -1200, "Nubank", true, "Lazer", "", 3, null);
            Lancamentos lanca7 = new Lancamentos("Jogos PC", -200, "Nubank", true, "Lazer", "", 1, null);
            Lancamentos lanca8 = new Lancamentos("Salario", 2000, "Nubank", false, "Salario", "", 1, null);
            sistema.adicionarLancamento(lanca1);
            sistema.adicionarLancamento(lanca2);
            sistema.adicionarLancamento(lanca3);
            sistema.adicionarLancamento(lanca4);
            sistema.adicionarLancamento(lanca5);
            sistema.adicionarLancamento(lanca6);
            sistema.adicionarLancamento(lanca7);
            sistema.adicionarLancamento(lanca8);
            
            launch();
        } catch(Exception erro){
            System.out.println(erro.getMessage());
        }
    }
    
    public static Sistema getSistema() {
        return sistema;
    }
}