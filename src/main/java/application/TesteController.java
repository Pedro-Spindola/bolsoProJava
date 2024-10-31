package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TesteController implements Initializable {

    // Referências para o TableView e suas colunas
    @javafx.fxml.FXML
    private TableView<Dado> tableView;
    @javafx.fxml.FXML
    private TableColumn<Dado, String> column1;
    @javafx.fxml.FXML
    private TableColumn<Dado, String> column2;

    // Classe que representa os dados da tabela
    public static class Dado {
        private final String coluna1;
        private final String coluna2;

        public Dado(String coluna1, String coluna2) {
            this.coluna1 = coluna1;
            this.coluna2 = coluna2;
        }

        public String getColuna1() {
            return coluna1;
        }

        public String getColuna2() {
            return coluna2;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar as colunas para usar as propriedades da classe Dado
        column1.setCellValueFactory(new PropertyValueFactory<>("coluna1"));
        column2.setCellValueFactory(new PropertyValueFactory<>("coluna2"));

        // Adicionar dados de exemplo
        ObservableList<Dado> dados = FXCollections.observableArrayList(
                new Dado("Linha 1 - C1", "Linha 1 - C2"),
                new Dado("Linha 2 - C1", "Linha 2 - C2")
        );

        // Definir os dados no TableView
        tableView.setItems(dados);
        
        // Ocultar o cabeçalho
        tableView.widthProperty().addListener((obs, oldVal, newVal) -> {
            tableView.lookup("TableHeaderRow").setVisible(false);
        });
    }
}
