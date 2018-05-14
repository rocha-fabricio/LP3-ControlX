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
import models.Produto;
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

    private Produto prod = new Produto();

    ProdutoDAO pdao = new ProdutoDAO();
    FornecedorDAO fdao = new FornecedorDAO();
    CategoriaDAO cdao = new CategoriaDAO();

    public AddProduto(){

    }

   public AddProduto(boolean view, boolean edit, Produto p) throws ClassNotFoundException {
       this.view = view;
       this.edit = edit;
       this.prod = pdao.read(p.getId());
   }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try {
            iniComboBox();
        if (view == true){
            preencher();
            btSalvar.setVisible(false);
            btCancelar.setText("Voltar");
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
       }
    }

    public void preencher(){
       txNome.setText(prod.getNome());
       txId.setText(Integer.toString(prod.getId()));
       txPreco.setText(Double.toString(prod.getPreco()));
       txQtd.setText(Double.toString(prod.getQtd()));
       cbUn.setValue(prod.getTipoUn());
       txEstoqueMin.setText(Double.toString(prod.getEstoqueMin()));
       cbForn.setValue(prod.getForn().getNome());
       cbCat.setValue(prod.getCat().getNome());
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
