package controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Produto;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuPrincipal
{

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/MenuPrincipal.fxml"));
        root.setControllerFactory(c -> {
            return new MenuPrincipal();
        });
        primaryStage.setTitle("ControlX - Menu");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void btProdutos() throws IOException {
        new Estoque().show();
    }

    public void btCompras() throws IOException {
        new Compras().show();
    }
    public void btVendas() throws IOException{
        new Vendas().show();
    }
    public void botaoVoltar() throws IOException{
        new Login().show();
    }

}
