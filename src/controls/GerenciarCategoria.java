package controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GerenciarCategoria implements Initializable {

    boolean view = false;
    boolean edit = false;
    int idCat;

    @FXML
    private Button btSalvar;
    @FXML
    private Button btCancelar;
    @FXML
    private TextField txNome;
    @FXML
    private TextField txId;

    public GerenciarCategoria(){

    }

    public GerenciarCategoria(boolean view, boolean edit, int idCat){
        this.view = view;
        this.edit = edit;
        this.idCat = idCat;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void show(boolean view, boolean edit, int id) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/GerenciarCategoria.fxml"));
        root.setControllerFactory(c -> {
            return new GerenciarCategoria(view, edit, id);
        });
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/GerenciarCategoria.fxml"));
        root.setControllerFactory(c -> {
            return new GerenciarCategoria();
        });
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
