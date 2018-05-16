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
import javafx.scene.input.MouseEvent;
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

    ProdutoDAO pdao = new ProdutoDAO();
    FornecedorDAO fdao = new FornecedorDAO();
    CategoriaDAO cdao = new CategoriaDAO();

    private boolean edit = false;
    private boolean view = false;
    private int idProd;


    public AddProduto(){

    }
   public AddProduto(boolean view, boolean edit, int idProd) throws ClassNotFoundException {
       this.view = view;
       this.edit = edit;
       this.idProd = idProd;
   }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            iniComboBox();
            if(view){
                preencher();
                btSalvar.setVisible(false);
                btCancelar.setText("Voltar");
                txNome.setEditable(false);
                txId.setEditable(false);
                txPreco.setEditable(false);
                txQtd.setEditable(false);
                cbUn.setDisable(false);
                txEstoqueMin.setEditable(false);
                cbForn.setDisable(false);
                cbCat.setDisable(false);
            }
            if(edit) {
                preencher();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void preencher() throws ClassNotFoundException {
        Produto p = pdao.read(idProd);

        txNome.setText(p.getNome());
        txId.setText(Integer.toString(p.getId()));
        txPreco.setText(Double.toString(p.getPreco()));
        txQtd.setText(Double.toString(p.getQtd()));
        cbUn.setValue(p.getTipoUn());
        txEstoqueMin.setText(Double.toString(p.getEstoqueMin()));
        cbForn.setValue(p.getForn().getNome());
        cbCat.setValue(p.getCat().getNome());
    }


    public void show(boolean view, boolean edit, int id) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/AddProduto.fxml"));
        root.setControllerFactory(c -> {
                    try {
                        return new AddProduto(view, edit, id);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return new AddProduto();
                    }
                });
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public void iniComboBox() throws ClassNotFoundException {
        ObservableList<String> opcoes = FXCollections.observableArrayList();
        ObservableList<String> forn = FXCollections.observableArrayList();

        //Categoria
        List<Categoria> categorias = cdao.listAll();
        for (Categoria cat : categorias) {
            opcoes.add(cat.getNome());
        }
        cbCat.setItems(opcoes);
        cbCat.setValue("<Selecione>");

       //Fornecedor
        for(Fornecedor f : fdao.listAll()) {
            forn.add(f.getNome());
        }
        cbForn.setItems(forn);
        cbForn.setValue("<Selecione>");

       //TipoUn
        ObservableList<String> tipoUn = FXCollections.observableArrayList("UN", "KG", "L", "ML", "G", "M", "CM");
        cbUn.setItems(tipoUn);
        cbUn.setValue("<Selecione>");


    }

    public void btCancel_Click(MouseEvent mouseEvent) throws IOException {
        new Estoque().show();
    }

    public void btSalvar_Click(MouseEvent mouseEvent) throws ClassNotFoundException, IOException {

        Produto p = new Produto();
        p.setNome(txNome.getText());
        p.setPreco(Double.parseDouble(txPreco.getText()));
        p.setQtd(Double.parseDouble(txQtd.getText()));
        p.setTipoUn(cbUn.getValue().toString());
        p.setEstoqueMin(Double.parseDouble(txEstoqueMin.getText()));
        p.setCat(cdao.read(cbCat.getValue().toString()));
        p.setForn(fdao.read(cbForn.getValue().toString()));

        if(edit) {
            pdao.up(p);
        } else {
            pdao.add(p);
        }

        new Estoque().show();

    }
}
