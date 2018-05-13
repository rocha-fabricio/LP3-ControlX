package controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
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

    public AddProduto(){

    }

    //Editar
    public AddProduto(boolean edit, String txNome, String txId, String txPreco, String txQtd, String cbUn, String txEstoqueMin, String cbForn, String cbCat){
        this.edit = edit;
        this.txNome.setText(txNome);
        this.txId.setText(txId);
        this.txPreco.setText(txPreco);
        this.txQtd.setText(txQtd);
        this.cbUn.setValue(cbUn);
        this.txEstoqueMin.setText(txEstoqueMin);
        this.cbForn.setValue(cbForn);
        this.cbCat.setValue(cbCat);
    }

    //Ver
    public AddProduto(String txNome, String txId, String txPreco, String txQtd, String cbUn, String txEstoqueMin, String cbForn, String cbCat, boolean view){
        this.txNome.setText(txNome);
        this.txId.setText(txId);
        this.txPreco.setText(txPreco);
        this.txQtd.setText(txQtd);
        this.cbUn.setValue(cbUn);
        this.txEstoqueMin.setText(txEstoqueMin);
        this.cbForn.setValue(cbForn);
        this.cbCat.setValue(cbCat);
        this.view = view;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (view == true){

            txNome.setEditable(false);
            txId.setEditable(false);
            txPreco.setEditable(false);
            txQtd.setEditable(false);
            cbUn.setEditable(false);
            txEstoqueMin.setEditable(false);
            cbForn.setEditable(false);
            cbCat.setEditable(false);

        } else if (edit == true){

        }
    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/AddProduto.fxml"));
        primaryStage.setTitle("ControlX - Produtos");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        if (edit == true){

        }
        primaryStage.show();
    }

    public void gerenciarProdutos(){

    }
}
