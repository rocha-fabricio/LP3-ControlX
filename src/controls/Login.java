package controls;
import database.ConexaoMySQL;
import controls.Menu;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Login {

        @FXML
        public void logar() throws IOException {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
            primaryStage.setTitle("ControlX - Menu");
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.setResizable(false);
            primaryStage.show();

        }
}
