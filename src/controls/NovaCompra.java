package controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Produto;
import DAO.ProdutoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NovaCompra implements Initializable {

    ProdutoDAO pDAO = new ProdutoDAO();

    @FXML
    private TextField txNome;

    @FXML
    private ComboBox<Produto> cbPesquisar;

    @FXML
    private Label lbNome;

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
    private Button btAdicionar;

    @FXML
    private Button btLimparText;

    @FXML
    private TextField txPrecoTotal;

    @FXML
    private Label lbPesquisar;

    @FXML
    private TableView<Produto> tbProdutos;

    @FXML
    private Label lbCarrinho;

    @FXML
    private Label lbVendedor;

    @FXML
    private TextField txVendedor;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btFinalizar;

    @FXML
    private Button btLimparVenda;

    @FXML
    private Button btRemover;

    Produto AOO = new Produto();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cbPesquisar.setValue(AOO);
            autoFill();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    public void botaoVoltar() throws IOException {
        new Compras().show();
    }

    public void autoFill() throws ClassNotFoundException {
        ObservableList<Produto> produtos = FXCollections.observableArrayList();

        cbPesquisar.getItems().removeAll();
        if (
                cbPesquisar.getSelectionModel().getSelectedItem() == null) {
            for (Produto p : pDAO.listAll()) {
                produtos.add(p);
            }
            cbPesquisar.setItems(produtos);
            System.out.println("IF");

        } else {
            String pesquisa = cbPesquisar.getValue().toString();
            for (Produto p : pDAO.listAllByName(pesquisa)) {
                produtos.add(p);
            }
            cbPesquisar.setItems(produtos);
            cbPesquisar.show();
            System.out.println("ELSE");
        }

    }

    public void showProd() {
        try {
            Produto prd = (Produto) cbPesquisar.getValue();
            //txId.setText(Integer.toString(prd.getId()));
            System.out.println("SHOW PROD");
            txId.setText(String.valueOf(prd.getId()));
            txNome.setText(prd.getNome());
            txPrecoUn.setText(String.valueOf(prd.getPreco()));
            txQtdEstoque.setText(String.valueOf(prd.getQtd()));

            System.out.println("TRY");
        } catch (Exception e) {

        }

    }
}
