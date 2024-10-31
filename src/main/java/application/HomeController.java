/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Sistema;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.Conta;
import util.Constraints;

/**
 * FXML Controller class
 *
 * @author Pedro Spindola
 */
public class HomeController implements Initializable {
    
    Sistema sistema = App.getSistema();
    @FXML
    private Button buttonClose;
    @FXML
    private Label labelDataHoraAtual;
    @FXML
    private Label labelNomeDoUsuario;
    @FXML
    private Label labelSaudacao;
    @FXML
    private Label labelSaldoDaCarteira;
    @FXML
    private Label labelInvestimentoDaCarteira;
    @FXML
    private Label labelReceitaMesAtual;
    @FXML
    private Label labelDespesasMesAtual;
    @FXML
    private Label labelInformaErroAdicionarConta;
    @FXML
    private TableView<Conta> tableMinhasContasTelaInicial;
    @FXML
    private TableView<Conta> tableMeusCartoesTelaInicial;
    @FXML
    private TableView<Conta> tableTodasAsContasTelaContas;
    @FXML
    private TableColumn<Conta, String> columnTelaInicialNome;
    @FXML
    private TableColumn<Conta, Float> columnTelaInicialSaldo;
    @FXML
    private TableColumn<Conta, Integer> columnTelaInicialDataFechamento;
    @FXML
    private TableColumn<Conta, Integer> columnTelaInicialDataVencimento;
    @FXML
    private TableColumn<Conta, Float> columnTelaInicialLimite;
    @FXML
    private TableColumn<Conta, Float> columnTelaInicialLimiteDisponivel;
    @FXML
    private TableColumn<Conta, String> columnTelaInicialNomeCartoes;
    @FXML
    private TableColumn<Conta, Boolean> columnTelaContasCredito;
    @FXML
    private TableColumn<Conta, Integer> columnTelaContasFechamento;
    @FXML
    private TableColumn<Conta, Float> columnTelaContasInvestimento;
    @FXML
    private TableColumn<Conta, Float> columnTelaContasLimite;
    @FXML
    private TableColumn<Conta, Float> columnTelaContasLimiteDisponivel;
    @FXML
    private TableColumn<Conta, String> columnTelaContasNomes;
    @FXML
    private TableColumn<Conta, Float> columnTelaContasSaldo;
    @FXML
    private TableColumn<Conta, Integer> columnTelaContasVencimento;
    @FXML
    private StackPane stackPanePrincipal;
    @FXML
    private AnchorPane paneConfiguracao;
    @FXML
    private AnchorPane paneHome;
    @FXML
    private AnchorPane paneLancamentos;
    @FXML
    private AnchorPane paneRelatorios;
    @FXML
    private AnchorPane paneAdicionarUmaNovaConta;
    @FXML
    private TextField textFieledDataDeFechamento;
    @FXML
    private TextField textFieledDataDeVencimento;
    @FXML
    private TextField textFieledInvestimento;
    @FXML
    private TextField textFieledLimiteDoCartao;
    @FXML
    private TextField textFieledNomeDaConta;
    @FXML
    private TextField textFieledSaldoDaConta;
    @FXML
    private CheckBox checkBoxCartaoDeCreditoDisponivel;
    @FXML
    public void abrirPaneConfiguracao(ActionEvent event) {
        if (paneConfiguracao != null) {
            stackPanePrincipal.getChildren().clear();
            stackPanePrincipal.getChildren().add(paneConfiguracao);
            carregarTodasAsContasTelaContasNaTabela();
        } else {
            System.err.println("paneConfiguracao é nulo!");
        }
    } 
    @FXML
    public void abrirPaneHome(ActionEvent event) {
        if (paneHome != null) {
            stackPanePrincipal.getChildren().clear();
            stackPanePrincipal.getChildren().add(paneHome);
            atualizarPaneHome();
            carregarDadosNaTabela();
        } else {
            System.err.println("paneHome é nulo!");
        }
    }
    @FXML
    public void abrirPaneLancamento(ActionEvent event) {
        if (paneHome != null) {
            stackPanePrincipal.getChildren().clear();
            stackPanePrincipal.getChildren().add(paneLancamentos);
            atualizarPaneHome();
            carregarDadosNaTabela();
        } else {
            System.err.println("paneLancamentos é nulo!");
        }
    }
    @FXML
    public void abrirPaneRelatorios(ActionEvent event) {

    }
    @FXML
    public void abrirTelaLancamentoReceitas(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da nova tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaLancamentoPositivo.fxml"));
            Parent root = loader.load();

            // Cria um novo Stage para a nova janela
            Stage stage = new Stage();

            // Configura a cena da nova janela
            stage.setScene(new Scene(root));

            // Define a janela como modal
            stage.initModality(Modality.APPLICATION_MODAL); // Adiciona esta linha para tornar a janela modal
            stage.initOwner(((Stage) ((Node) event.getSource()).getScene().getWindow())); // Define a janela principal como dona da nova janela

            // (Opcional) Remover a barra de título
            stage.initStyle(StageStyle.UNDECORATED);

            // Exibe a nova janela
            stage.showAndWait(); // Usar showAndWait para bloquear a janela anterior até que esta seja fechada

        } catch (IOException e) {
            e.printStackTrace();
            // Exibir mensagem de erro ou log adicional se necessário
        }
    }
    @FXML
    public void abrirTelaLancamentoDespesas(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da nova tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaLancamentoNegativo.fxml"));
            Parent root = loader.load();

            // Cria um novo Stage para a nova janela
            Stage stage = new Stage();

            // Configura a cena da nova janela
            stage.setScene(new Scene(root));

            // Define a janela como modal
            stage.initModality(Modality.APPLICATION_MODAL); // Adiciona esta linha para tornar a janela modal
            stage.initOwner(((Stage) ((Node) event.getSource()).getScene().getWindow())); // Define a janela principal como dona da nova janela

            // (Opcional) Remover a barra de título
            stage.initStyle(StageStyle.UNDECORATED);

            // Exibe a nova janela
            stage.showAndWait(); // Usar showAndWait para bloquear a janela anterior até que esta seja fechada

        } catch (IOException e) {
            e.printStackTrace();
            // Exibir mensagem de erro ou log adicional se necessário
        }
    }
    @FXML
    public void abrirTelaTransferencia(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da nova tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaTransacao.fxml"));
            Parent root = loader.load();

            // Cria um novo Stage para a nova janela
            Stage stage = new Stage();

            // Configura a cena da nova janela
            stage.setScene(new Scene(root));

            // Define a janela como modal
            stage.initModality(Modality.APPLICATION_MODAL); // Adiciona esta linha para tornar a janela modal
            stage.initOwner(((Stage) ((Node) event.getSource()).getScene().getWindow())); // Define a janela principal como dona da nova janela

            // (Opcional) Remover a barra de título
            stage.initStyle(StageStyle.UNDECORATED);

            // Exibe a nova janela
            stage.showAndWait(); // Usar showAndWait para bloquear a janela anterior até que esta seja fechada

        } catch (IOException e) {
            e.printStackTrace();
            // Exibir mensagem de erro ou log adicional se necessário
        }
    }
    @FXML
    public void fecharJanelaButtonClicked(ActionEvent event) {
        try{
            Stage stage = (Stage) buttonClose.getScene().getWindow();
            stage.close();
        } catch(Exception erro){
            System.out.println(erro.getMessage());
        }
    }
    @FXML 
    public void buttonClickedCriarUmaNovaConta(ActionEvent event){
        if(paneAdicionarUmaNovaConta.isVisible()){
            paneAdicionarUmaNovaConta.setVisible(false);
        }else{
            paneAdicionarUmaNovaConta.setVisible(true);
            labelInformaErroAdicionarConta.setText("");
            textFieledNomeDaConta.setText("");
            textFieledSaldoDaConta.setText("");
            textFieledInvestimento.setText("");
            textFieledLimiteDoCartao.setText("");
            textFieledDataDeFechamento.setText("");
            textFieledDataDeVencimento.setText("");
        }
    }
    @FXML 
    public void buttonClickedAdicionarUmaNovaConta(ActionEvent event){
        try{
            Conta newConta = null;
            Integer id = sistema.idParaUmaNovaConta();
            if(textFieledNomeDaConta.getText() == null || textFieledNomeDaConta.getText().trim().isEmpty())throw new Constraints.ValidaContaException("Nome não pode ser vazio.");
            if(textFieledSaldoDaConta.getText() == null || textFieledSaldoDaConta.getText().trim().isEmpty()) throw new Constraints.ValidaContaException("Saldo não pode ser vazio.");
            if(textFieledInvestimento.getText() == null || textFieledInvestimento.getText().trim().isEmpty()) throw new Constraints.ValidaContaException("Investimento não pode ser vazio.");
            
            System.out.println(checkBoxCartaoDeCreditoDisponivel.isSelected());
            if(checkBoxCartaoDeCreditoDisponivel.isSelected()){
                if(textFieledLimiteDoCartao.getText() == null || textFieledLimiteDoCartao.getText().trim().isEmpty()) throw new Constraints.ValidaContaException("Limite não pode ser vazio.");
                if(textFieledDataDeFechamento.getText() == null || textFieledDataDeFechamento.getText().trim().isEmpty()) throw new Constraints.ValidaContaException("Fechamento não pode ser vazio.");
                if(textFieledDataDeVencimento.getText() == null || textFieledDataDeVencimento.getText().trim().isEmpty()) throw new Constraints.ValidaContaException("Vencimento não pode ser vazio.");
            }

            String nome = textFieledNomeDaConta.getText();
            float saldo = Float.parseFloat(textFieledSaldoDaConta.getText());
            float investimento = Float.parseFloat(textFieledInvestimento.getText());
            if (saldo < 0) throw new Constraints.ValidaContaException("Saldo não pode ser negativo.");
            if (investimento < 0) throw new Constraints.ValidaContaException("Investimento não pode ser negativo.");
            
            
            if(checkBoxCartaoDeCreditoDisponivel.isSelected()){
                float limite = Float.parseFloat(textFieledLimiteDoCartao.getText());
                Integer fechamento = Integer.parseInt(textFieledDataDeFechamento.getText());
                Integer vencimento = Integer.parseInt(textFieledDataDeVencimento.getText());
                if (limite < 0) throw new Constraints.ValidaContaException("Limite do cartão não pode ser negativo.");
                if (fechamento == null || fechamento < 1 || fechamento > 31) throw new Constraints.ValidaContaException("Data de fechamento é inválida.");
                if (vencimento == null || vencimento < 1 || vencimento > 31) throw new Constraints.ValidaContaException("Data de vencimento é inválida.");
            
                newConta = new Conta(id, nome, saldo, investimento, true, limite, fechamento, vencimento);
                textFieledNomeDaConta.setText("");
                textFieledSaldoDaConta.setText("");
                textFieledInvestimento.setText("");
                checkBoxCartaoDeCreditoDisponivel.setSelected(false);
                textFieledLimiteDoCartao.setText("");
                textFieledDataDeFechamento.setText("");
                textFieledDataDeVencimento.setText("");
                labelInformaErroAdicionarConta.setText("Conta criado com sucesso!");
            } else{
                newConta = new Conta(id, nome, saldo, investimento);
                textFieledNomeDaConta.setText("");
                textFieledSaldoDaConta.setText("");
                textFieledInvestimento.setText("");
                labelInformaErroAdicionarConta.setText("Conta criado com sucesso!");
            }
            sistema.adicionarConta(newConta);
            carregarTodasAsContasTelaContasNaTabela();
        }
        catch (Constraints.ValidaContaException e) {
            labelInformaErroAdicionarConta.setText("Erro de validação: " + e.getMessage());
        }
        catch (Exception erro) {
            labelInformaErroAdicionarConta.setText(erro.getMessage());
        }
    }
    
    public String obterSaudacao() {
        Calendar calendar = Calendar.getInstance();
        int horaAtual = calendar.get(Calendar.HOUR_OF_DAY);

        if (horaAtual >= 5 && horaAtual < 12) {
            return "Bom dia!";
        } else if (horaAtual >= 12 && horaAtual < 19) {
            return "Boa tarde!";
        } else {
            return "Boa noite!";
        }
    }
    
    public static String obterDataHoraAtual() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return formatador.format(calendar.getTime());
    }
    
    private void atualizarDataHora() {
        labelDataHoraAtual.setText(obterDataHoraAtual());
    }
    
    private void iniciarAtualizacaoSaudacao() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.hours(1), event -> {
            labelSaudacao.setText(obterSaudacao());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    private void iniciarAtualizacaoHora() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> atualizarDataHora()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Define para atualizar indefinidamente
        timeline.play(); // Inicia o timeline
    }
    
    // Método para formatar float para String no formato R$
    private String formatarValor(float valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formato.format(valor);
    }
    
    private void carregarDadosNaTabela() {
        tableMeusCartoesTelaInicial.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double tableWidthMinhasContasTelaInicial = newWidth.doubleValue();
            columnTelaInicialNome.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.5);     // 50%
            columnTelaInicialSaldo.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.49);    // 50%
        });
        ObservableList<Conta> contasMinhasContas = FXCollections.observableArrayList(sistema.getContas());
        columnTelaInicialNome.setCellValueFactory(new PropertyValueFactory<>("nomeDoBanco"));
        columnTelaInicialSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        columnTelaInicialSaldo.setCellFactory(col -> new TableCell<Conta, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Chama o método existente para formatar o valor
                    setText(formatarValor(item));
                }
            }
        });
        
        tableMinhasContasTelaInicial.setItems(contasMinhasContas);
        
        tableMeusCartoesTelaInicial.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double tableWidthMeusCartoesTelaInicial = newWidth.doubleValue();

            columnTelaInicialNomeCartoes.setPrefWidth(tableWidthMeusCartoesTelaInicial * 0.2);          // 20%
            columnTelaInicialLimite.setPrefWidth(tableWidthMeusCartoesTelaInicial * 0.2);               // 20%
            columnTelaInicialLimiteDisponivel.setPrefWidth(tableWidthMeusCartoesTelaInicial * 0.2);     // 20%
            columnTelaInicialDataFechamento.setPrefWidth(tableWidthMeusCartoesTelaInicial * 0.19);       // 20%
            columnTelaInicialDataVencimento.setPrefWidth(tableWidthMeusCartoesTelaInicial * 0.19);       // 20%
        });
        ObservableList<Conta> todasContas = FXCollections.observableArrayList(sistema.getContas());
        FilteredList<Conta> contasCartoesCredito = new FilteredList<>(todasContas, conta -> conta.isCartaoCreditos());
        columnTelaInicialNomeCartoes.setCellValueFactory(new PropertyValueFactory<>("nomeDoBanco"));
        columnTelaInicialLimite.setCellValueFactory(new PropertyValueFactory<>("limiteDoCartao"));
        columnTelaInicialLimite.setCellFactory(col -> new TableCell<Conta, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Chama o método existente para formatar o valor
                    setText(formatarValor(item));
                }
            }
        });
        columnTelaInicialLimiteDisponivel.setCellValueFactory(new PropertyValueFactory<>("limiteDisponivel"));
        columnTelaInicialLimiteDisponivel.setCellFactory(col -> new TableCell<Conta, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Chama o método existente para formatar o valor
                    setText(formatarValor(item));
                }
            }
        });
        columnTelaInicialDataFechamento.setCellValueFactory(new PropertyValueFactory<>("fechamentoDaFatura"));
        columnTelaInicialDataVencimento.setCellValueFactory(new PropertyValueFactory<>("vencimentoDaFatura"));
        tableMeusCartoesTelaInicial.setItems(contasCartoesCredito);
    }
    
    private void carregarTodasAsContasTelaContasNaTabela(){
        tableTodasAsContasTelaContas.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double tableWidthMinhasContasTelaInicial = newWidth.doubleValue();
            columnTelaContasNomes.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.2);     // 20%
            columnTelaContasSaldo.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.15);    // 15%
            columnTelaContasInvestimento.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.15);    // 15%
            columnTelaContasCredito.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.05);    // 5%
            columnTelaContasLimite.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.15);    // 15%
            columnTelaContasLimiteDisponivel.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.15);    // 15%
            columnTelaContasFechamento.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.07);    // 7%
            columnTelaContasVencimento.setPrefWidth(tableWidthMinhasContasTelaInicial * 0.07);    // 7%
        });
        ObservableList<Conta> contasMinhasContas = FXCollections.observableArrayList(sistema.getContas());
        columnTelaContasNomes.setCellValueFactory(new PropertyValueFactory<>("nomeDoBanco"));
        columnTelaContasSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        columnTelaContasSaldo.setCellFactory(col -> new TableCell<Conta, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Chama o método existente para formatar o valor
                    setText(formatarValor(item));
                }
            }
        });
        columnTelaContasInvestimento.setCellValueFactory(new PropertyValueFactory<>("investimentos"));
        columnTelaContasInvestimento.setCellFactory(col -> new TableCell<Conta, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Chama o método existente para formatar o valor
                    setText(formatarValor(item));
                }
            }
        });
        columnTelaContasCredito.setCellValueFactory(new PropertyValueFactory<>("cartaoCreditos"));
        columnTelaContasLimite.setCellValueFactory(new PropertyValueFactory<>("limiteDoCartao"));
        columnTelaContasLimite.setCellFactory(col -> new TableCell<Conta, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Chama o método existente para formatar o valor
                    setText(formatarValor(item));
                }
            }
        });
        columnTelaContasLimiteDisponivel.setCellValueFactory(new PropertyValueFactory<>("limiteDisponivel"));
        columnTelaContasLimiteDisponivel.setCellFactory(col -> new TableCell<Conta, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Chama o método existente para formatar o valor
                    setText(formatarValor(item));
                }
            }
        });
        columnTelaContasFechamento.setCellValueFactory(new PropertyValueFactory<>("fechamentoDaFatura"));
        columnTelaContasVencimento.setCellValueFactory(new PropertyValueFactory<>("vencimentoDaFatura"));
        
        tableTodasAsContasTelaContas.setItems(contasMinhasContas);
    }
    
    private void onEscolhaCartaoDeCreditoAlterado(Boolean novoValor) {
        if (novoValor) {
            textFieledLimiteDoCartao.setDisable(false);
            textFieledDataDeFechamento.setDisable(false);
            textFieledDataDeVencimento.setDisable(false);
        } else {
            textFieledLimiteDoCartao.setText("");
            textFieledDataDeFechamento.setText("");
            textFieledDataDeVencimento.setText("");
            textFieledLimiteDoCartao.setDisable(true);
            textFieledDataDeFechamento.setDisable(true);
            textFieledDataDeVencimento.setDisable(true);
        }
    }
    
    private void atualizarPaneHome(){
        labelSaldoDaCarteira.setText(formatarValor(sistema.valorTotalEmConta()));
        labelInvestimentoDaCarteira.setText(formatarValor(sistema.valorTotalEmInvestimentos()));
        labelReceitaMesAtual.setText(formatarValor(sistema.valorMensalEmReceitas()));
        labelDespesasMesAtual.setText(formatarValor(sistema.valorMensalEmDespesas()));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {          
            labelNomeDoUsuario.setText(sistema.getUsuario().getNome());
            labelSaudacao.setText(obterSaudacao());
            atualizarDataHora(); // Chama a função para definir a hora inicialmente
            iniciarAtualizacaoSaudacao(); // Inicia a atualização da saudação a cada horaPe
            iniciarAtualizacaoHora(); // Inicia a atualização a cada segundo
            
            atualizarPaneHome();
            carregarDadosNaTabela();
            
            checkBoxCartaoDeCreditoDisponivel.selectedProperty().addListener((observable, oldValue, newValue) -> {
                onEscolhaCartaoDeCreditoAlterado(newValue);
            });
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }
    }
}
