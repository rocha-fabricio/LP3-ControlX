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
    private Produto prod;
    private static int idProd;

    @FXML
    public TextField txNome;
    @FXML
    public TextField txId;
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

    //private String nome, id, preco, qtd, un, estoqueMin, forn, cat;



    ProdutoDAO pdao = new ProdutoDAO();
    FornecedorDAO fdao = new FornecedorDAO();
    CategoriaDAO cdao = new CategoriaDAO();

    public AddProduto(int idProd){
        this.idProd = idProd;
    }
    public AddProduto(){

    }
   public AddProduto(boolean view, boolean edit, Produto p) throws ClassNotFoundException {
       this.view = view;
       this.edit = edit;
       this.prod = p;
   }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("INICIO INITIALIZE");
        try {
            iniComboBox();
            prod = pdao.read(idProd);
            //txNome.setText(prod.getNome());
            preencher(prod);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("FIM INITIALIZE");
    }

    public void preencher(Produto p){
        System.out.println("PREENCHER");
        btSalvar.setVisible(false);
        //btCancelar.setText("Voltar");
        //txNome.setEditable(false);
        //txId.setEditable(false);
        //txPreco.setEditable(false);
        //txQtd.setEditable(false);
        //cbUn.setEditable(false);
        //txEstoqueMin.setEditable(false);
        //cbForn.setEditable(false);
        //cbCat.setEditable(false);
        //if (view){
        //    txNome.setText("VIEW");
        //}
        //if (edit){
        //    txNome.setText("EDIT");
        //}


        txNome.setText(p.getNome());
        txId.setText(Integer.toString(p.getId()));
        txPreco.setText(Double.toString(p.getPreco()));
        txQtd.setText(Double.toString(p.getQtd()));
        cbUn.setValue(p.getTipoUn());
        txEstoqueMin.setText(Double.toString(p.getEstoqueMin()));
        cbForn.setValue(p.getForn().getNome());
        cbCat.setValue(p.getCat().getNome());
    }


    public void show() throws IOException {
        System.out.println("1 - SHOW");
        Stage primaryStage = new Stage();
        System.out.println("2 - SHOW");
        Parent root = FXMLLoader.load(getClass().getResource("/views/AddProduto.fxml"));
        System.out.println("3 - SHOW");
        primaryStage.setTitle("ControlX - Produto");
        System.out.println("4 - SHOW");
        Main.stage.hide();
        System.out.println("5 - SHOW");
        Main.stage = primaryStage;
        System.out.println("6 - SHOW");
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        System.out.println("7 - SHOW");
        primaryStage.setResizable(false);
        System.out.println("8 - SHOW");
        primaryStage.show();
        System.out.println("FIM SHOW");
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
