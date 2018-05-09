package controls;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    private PasswordField txtPassword;

        @FXML
        public void logar() throws IOException {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
            primaryStage.setTitle("ControlX - Menu");
            Login.this.txtPassword.setText("");
            Main.stage.hide();
            Main.stage = primaryStage;
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.setResizable(true);
            primaryStage.show();

        }
}
