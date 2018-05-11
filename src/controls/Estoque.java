package controls;

import DAO.CategoriaDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Categoria;
import models.Produto;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Estoque implements Initializable {

    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> tcId;
    @FXML
    private TableColumn<Produto, String> tcNome;
    @FXML
    private TableColumn<Produto, Double> tcPreco;
    @FXML
    private TableColumn<Produto, Double> tcQtd;
    @FXML
    private TableColumn<Produto, String> tcUn;
    @FXML
    private TableColumn<Produto, String> tcCat;

    ProdutoDAO pdao = new ProdutoDAO();
    CategoriaDAO cdao = new CategoriaDAO();
    FornecedorDAO fdao = new FornecedorDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

/*    public void listView() throws ClassNotFoundException {
        List<Produto> lista = pdao.listAll();

        for (Produto p : lista){
            Categoria cat = p.getCat();
            tabela.setItems(FXCollections.observableArrayList(new Produto(p.getId(), p.getNome(), p.getPreco(), p.getQtd(), p.getTipoUn(), p.getCat())));
        }
    }*/
}
