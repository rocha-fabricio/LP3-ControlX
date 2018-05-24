package controls;

import DAO.VendaDAO;
import DAO.ProdutoDAO;
import javafx.scene.image.Image;
import models.Produto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Vendas implements Initializable{
    @FXML
    TextField txQtdVenda;
    @FXML
    TextField txQtdEstoque;
    @FXML
    TextField txPrecoVenda;
    @FXML
    TextField txVendedor;
    @FXML
    TextField txPrecoUn;
    @FXML
    TextField txPesquisar;
    @FXML
    TextField txNome;
    @FXML
    TextField txId;
    @FXML
    TableView<Produto> tbProdutos;
    @FXML
    Button btVoltar;
    @FXML
    Button btRemover;
    @FXML
    Button btLimparVenda;
    @FXML
    Button btLimparText;
    @FXML
    Button btFinalizar;
    @FXML
    Button btAdicionar;

    VendaDAO vDAO = new VendaDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* try{

        }
        catch(){

        }
        */
    }

    public Vendas(){

    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/Vendas.fxml"));
        root.setControllerFactory(c -> {
            return new MenuPrincipal();
        });
        primaryStage.setTitle("ControlX - Vendas");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void botaoVoltar() throws IOException{
        new MenuPrincipal().show();
    }
}
