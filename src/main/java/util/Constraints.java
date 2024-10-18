/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Pedro Spindola
 * @date 13/10/2024
 * @brief Class Constraints
 */
public class Constraints {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean EmailValido(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean SenhaValida(String senha) {
        return senha != null && senha.length() >= 8;
    }
    
}
