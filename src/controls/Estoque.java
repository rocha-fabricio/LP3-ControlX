package controls;

import DAO.CategoriaDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Categoria;
import models.Produto;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/Estoque.fxml"));
        root.setControllerFactory(c -> {
            return new Estoque();
        });
        primaryStage.setTitle("ControlX - Produtos");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
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

        TableColumn<Produto, Categoria> catColumn = new TableColumn<>("Cat");
        catColumn.setMinWidth(60);
        catColumn.setCellValueFactory(new PropertyValueFactory<>("cat"));

        tbView.setItems(lista);

        tbView.getColumns().addAll(idColumn, nomeColumn, precoColumn, qtdColumn, unColumn, catColumn);
        verificaSelecao();
    }

    public void botaoAddProduto() throws IOException {
       new GerenciarProduto().show();
    }

    public void botaoRemoveProduto() throws ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ControlX - Remover produto");
        alert.setHeaderText("Este produto será removido permanentemente.");
        alert.setContentText("Deseja continuar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            pdao.del(pdao.read(tbView.getSelectionModel().getSelectedItem().getId()));
            listView(pdao.listAll());
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void botaoEditProduto() throws IOException, ClassNotFoundException {
        new GerenciarProduto().show(false,true, tbView.getSelectionModel().getSelectedItem().getId());
    }

    public void botaoViewProduto() throws IOException, ClassNotFoundException {
        new GerenciarProduto().show(true,false, tbView.getSelectionModel().getSelectedItem().getId());
    }

    public void botaoVoltar() throws IOException {
        new MenuPrincipal().show();
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
            Categoria c = cdao.read(cat);
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
