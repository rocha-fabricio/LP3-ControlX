package controls;

import DAO.ProdutoDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Categoria;
import models.Fornecedor;
import models.Produto;

import java.awt.*;
import java.util.List;

public class Main extends Application {

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        new Login().show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
