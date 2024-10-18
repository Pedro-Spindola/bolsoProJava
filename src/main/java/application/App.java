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
            launch();
        } catch(Exception erro){
            System.out.println(erro.getMessage());
        }
    }
    
    public static Sistema getSistema() {
        return sistema;
    }
}