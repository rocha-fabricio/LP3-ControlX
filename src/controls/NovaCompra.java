package controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Produto;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NovaCompra implements Initializable {

    @FXML
    private TableView <Produto> tbProdutos;
    @FXML
    private TextField txPesquisar;
    @FXML
    private TextField txNome;
    @FXML
    private TextField txId;
    @FXML
    private TextField txQtdEstoque;
    @FXML
    private TextField txPrecoUn;
    @FXML
    private TextField txQtdCompra;
    @FXML
    private TextField txPrecoCompra;
    @FXML
    private TextField txPrecoTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/NovaCompra.fxml"));
        root.setControllerFactory(c -> {
            return new NovaCompra();
        });
        primaryStage.setTitle("ControlX - Nova Compra");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

}
