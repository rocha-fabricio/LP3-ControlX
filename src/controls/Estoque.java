package controls;

import DAO.CategoriaDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import models.Categoria;
import models.Produto;

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
    private ChoiceBox bxCategoria;

    ProdutoDAO pdao = new ProdutoDAO();
    CategoriaDAO cdao = new CategoriaDAO();
    FornecedorDAO fdao = new FornecedorDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listView(pdao.listAll());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void listView(List<Produto> prods) throws ClassNotFoundException {
        tbView.getItems().clear();

        ObservableList<Produto> lista = FXCollections.observableArrayList();

        for (Produto p : prods) {
            lista.add(new Produto(p.getId(), p.getNome(), p.getPreco(), p.getQtd(), p.getTipoUn(), p.getCat()));
        }

        TableColumn<Produto, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory( new PropertyValueFactory<>("id") );

        TableColumn<Produto, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setMinWidth(200);
        nomeColumn.setCellValueFactory( new PropertyValueFactory<>("nome") );

        TableColumn<Produto, Double> precoColumn = new TableColumn<>("Preço (R$)");
        precoColumn.setMinWidth(60);
        precoColumn.setCellValueFactory( new PropertyValueFactory<>("preco") );

        TableColumn<Produto, Double> qtdColumn = new TableColumn<>("Qtd");
        qtdColumn.setMinWidth(60);
        qtdColumn.setCellValueFactory( new PropertyValueFactory<>("qtd") );

        TableColumn<Produto, String> unColumn = new TableColumn<>("Un");
        unColumn.setMinWidth(60);
        unColumn.setCellValueFactory( new PropertyValueFactory<>("tipoUn") );

        TableColumn<Produto, String> catColumn = new TableColumn<>("Cat");
        catColumn.setMinWidth(50);
        catColumn.setCellValueFactory( new PropertyValueFactory<>("cat") );

        tbView.setItems(lista);

        tbView.getColumns().addAll(idColumn, nomeColumn, precoColumn, qtdColumn, unColumn, catColumn);
    }

    public void addProduto(){

    }

    public void removeProduto(){

    }

    public void editProduto(){

    }

    public void pesquisarProduto() throws ClassNotFoundException {
        if(rdId.isSelected()){
            listView(pdao.listAllById(txPesquisar.getText()));
        }
        else if(rdNome.isSelected()){
            listView(pdao.listAllByName(txPesquisar.getText()));
        }
        else if(txPesquisar.getText().equals("")){
            listView(pdao.listAll());
        }

    }

}
