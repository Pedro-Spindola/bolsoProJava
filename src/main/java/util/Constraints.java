/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
/**
 *
 * @author Pedro Spindola
 * @date 13/10/2024
 * @brief Class Constraints
 */
public class Constraints {

    public static boolean EmailValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Método para validar senha
    public static boolean SenhaValida(String senha) {
        return senha != null && senha.length() >= 8;
    }
    
    public static void validacaoNumerosInteiros(TextField textField) {
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*([\\.]\\d{0,2})?")) {
                return change;
            }
            return null;
        });
        textField.setTextFormatter(formatter);
    }
    
    public static void validacaoDataFormatado(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Verifica se o novo valor é um número e tem no máximo 2 dígitos
            if (!newValue.matches("\\d{0,2}")) {
                // Se não corresponder, reverte para o valor antigo
                textField.setText(oldValue);
            }
        });
    }
    public static  class ValidaContaException extends Exception {
        public ValidaContaException(String message) {
            super(message);
        }
    }
}
