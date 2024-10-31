
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Pedro Spindola
 * @date 30/10/2024
 * @brief Class LancamentoPositivoController
 */
public class TrasacaoDeContasController implements Initializable{
    
    @FXML
    private Button buttonClose;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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

}
