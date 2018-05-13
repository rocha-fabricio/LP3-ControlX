package controls;

import DAO.CategoriaDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Categoria;
import models.Fornecedor;
import models.Produto;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Estoque implements Initializable {

    @FXML
    private TableView<Produto> tbView;
    @FXML
    private RadioButton rdId;
    @FXML
    private RadioButton rdNome;
    @FXML
    private TextField txPesquisar;
    @FXML
    private ComboBox cbCat;
    @FXML
    private Button btAdd;
    @FXML
    private Button btRemove;
    @FXML
    private Button btEdit;
    @FXML
    private Button btVoltar;
    @FXML
    Button btView;

    private ProdutoDAO pdao = new ProdutoDAO();
    private CategoriaDAO cdao = new CategoriaDAO();
    private FornecedorDAO fdao = new FornecedorDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listView(pdao.listAll());
            iniComboBox();
            verificaSelecao();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/Estoque.fxml"));
        primaryStage.setTitle("ControlX - Produtos");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public void listView(List<Produto> prods) throws ClassNotFoundException {
        tbView.getItems().clear();
        tbView.getColumns().clear();

        ObservableList<Produto> lista = FXCollections.observableArrayList();

        for (Produto p : prods) {
            lista.add(new Produto(p.getId(), p.getNome(), p.getPreco(), p.getQtd(), p.getTipoUn(), p.getCat()));
        }

        TableColumn<Produto, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Produto, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setMinWidth(250);
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Produto, String> precoColumn = new TableColumn<>("Preço (R$)");
        precoColumn.setMinWidth(60);
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        TableColumn<Produto, String> qtdColumn = new TableColumn<>("Qtd");
        qtdColumn.setMinWidth(60);
        qtdColumn.setCellValueFactory(new PropertyValueFactory<>("qtd"));

        TableColumn<Produto, String> unColumn = new TableColumn<>("Un");
        unColumn.setMinWidth(60);
        unColumn.setCellValueFactory(new PropertyValueFactory<>("tipoUn"));

        TableColumn<Produto, String> catColumn = new TableColumn<>("Cat");
        catColumn.setMinWidth(60);
        catColumn.setCellValueFactory(new PropertyValueFactory<>("cat"));

        tbView.setItems(lista);

        tbView.getColumns().addAll(idColumn, nomeColumn, precoColumn, qtdColumn, unColumn, catColumn);
        verificaSelecao();
    }

    public void botaoAddProduto() throws IOException {
        new AddProduto().show();
    }

    public void botaoRemoveProduto() {

    }

    public void botaoEditProduto() throws IOException {
        new AddProduto(
                true, false,
                tbView.getSelectionModel().getSelectedItem().getNome(),
                String.valueOf(tbView.getSelectionModel().getSelectedItem().getId()),
                String.valueOf(tbView.getSelectionModel().getSelectedItem().getPreco()),
                String.valueOf(tbView.getSelectionModel().getSelectedItem().getQtd()),
                tbView.getSelectionModel().getSelectedItem().getTipoUn(),
                String.valueOf(tbView.getSelectionModel().getSelectedItem().getEstoqueMin()),
                String.valueOf(tbView.getSelectionModel().getSelectedItem().getForn()),
                String.valueOf(tbView.getSelectionModel().getSelectedItem().getCat()))
                .show();
    }

    public void botaoViewProduto() throws IOException {
        new AddProduto(
                false, true,
                tbView.getSelectionModel().getSelectedItem().getNome(),
                Integer.toString(tbView.getSelectionModel().getSelectedItem().getId()),
                Double.toString(tbView.getSelectionModel().getSelectedItem().getPreco()),
                Double.toString(tbView.getSelectionModel().getSelectedItem().getQtd()),
                tbView.getSelectionModel().getSelectedItem().getTipoUn(),
                Double.toString(tbView.getSelectionModel().getSelectedItem().getEstoqueMin()),
                String.valueOf(tbView.getSelectionModel().getSelectedItem().getForn()),
                String.valueOf(tbView.getSelectionModel().getSelectedItem().getCat()))
                .show();
    }

    public void botaoVoltar() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/views/MenuPrincipal.fxml"));
        primaryStage.setTitle("ControlX - Menu");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public void pesquisarProduto() throws ClassNotFoundException {
        if (rdId.isSelected()) {
            listView(pdao.listAllById(txPesquisar.getText()));
        } else if (rdNome.isSelected()) {
            listView(pdao.listAllByName(txPesquisar.getText()));
        } else if (txPesquisar.getText().equals("")) {
            listView(pdao.listAll());
        }

    }

    public void pesquisarCategoria() throws ClassNotFoundException {
        String cat = cbCat.getValue().toString();
        if (cat.equals("Todos")) {
            listView(pdao.listAll());
        } else {
            Categoria c = cdao.readNome(cat);
            listView(pdao.listAllByCategoria(c.getId()));
        }
    }

    public void iniComboBox() throws ClassNotFoundException {
        ObservableList<String> opcoes = FXCollections.observableArrayList();
        List<Categoria> categorias = cdao.listAll();
        opcoes.add("Todos");

        for (Categoria cat : categorias) {
            opcoes.add(cat.getNome());
        }

        cbCat.setItems(opcoes);
        cbCat.setValue("Todos");
    }

    public void verificaSelecao(){
        if (tbView.isFocused()){
            btRemove.setDisable(false);
            btEdit.setDisable(false);
            btView.setDisable(false);
        } else {
            btRemove.setDisable(true);
            btEdit.setDisable(true);
            btView.setDisable(true);
        }
    }
}
