package controls;

import DAO.CategoriaDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Categoria;
import models.Fornecedor;
import org.w3c.dom.Text;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddProduto implements Initializable {

    private boolean edit = false;
    private boolean view = false;

    @FXML
    TextField txNome;
    @FXML
    TextField txId;
    @FXML
    TextField txPreco;
    @FXML
    TextField txQtd;
    @FXML
    ComboBox cbUn;
    @FXML
    TextField txEstoqueMin;
    @FXML
    ComboBox cbForn;
    @FXML
    ComboBox cbCat;
    @FXML
    Button btSalvar;
    @FXML
    Button btCancelar;

    private String nome, id, preco, qtd, un, estoqueMin, forn, cat;

    ProdutoDAO pdao = new ProdutoDAO();
    FornecedorDAO fdao = new FornecedorDAO();
    CategoriaDAO cdao = new CategoriaDAO();

    public AddProduto(){

    }

    //Editar
    public AddProduto(boolean edit, boolean view, String txNome, String txId, String txPreco,
                      String txQtd, String cbUn, String txEstoqueMin, String
                              cbForn, String cbCat){

        this.edit = edit;
        this.view = view;
        this.nome = txNome;
        this.id = txId;
        this.preco = txPreco;
        this.qtd = txQtd;
        this.un = cbUn;
        this.estoqueMin = txEstoqueMin;
        this.forn = cbForn;
        this.cat = cbCat;
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        if (view == true){
            preencher();
            txNome.setEditable(false);
            txId.setEditable(false);
            txPreco.setEditable(false);
            txQtd.setEditable(false);
            cbUn.setEditable(false);
            txEstoqueMin.setEditable(false);
            cbForn.setEditable(false);
            cbCat.setEditable(false);
        }
        if (edit == true){
            preencher();
        }
            iniComboBox();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void preencher(){
        txNome.setText(nome);
        txId.setText(id);
        txPreco.setText(preco);
        txQtd.setText(qtd);
        cbUn.setValue(un);
        txEstoqueMin.setText(estoqueMin);
        cbForn.setValue(forn);
        cbCat.setValue(cat);
    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/AddProduto.fxml"));
        primaryStage.setTitle("ControlX - Produto");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        primaryStage.show();
        if (view == true){
            btSalvar.setVisible(false);
            btCancelar.setText("Voltar");
        }
    }

    public void addProdutos(){

    }

    public void iniComboBox() throws ClassNotFoundException {
        ObservableList<String> opcoes = FXCollections.observableArrayList();

        //Categoria
        List<Categoria> categorias = cdao.listAll();
        for (Categoria cat : categorias) {
            opcoes.add(cat.getNome());
        }

        cbCat.setItems(opcoes);
        cbCat.setValue("<Selecione>");

       /* //Fornecedor
        List<Fornecedor> forn = fdao.listAll();
        for (Categoria cat : categorias) {
            opcoes.add(cat.getNome());
        }

        cbCat.setItems(opcoes);
        cbCat.setValue("<Selecione>"); */

       //TipoUn
        ObservableList<String> tipoUn = FXCollections.observableArrayList("UN", "KG", "L", "ML", "G", "M", "CM");
        cbUn.setItems(tipoUn);
        cbUn.setValue("<Selecione>");


    }

}
