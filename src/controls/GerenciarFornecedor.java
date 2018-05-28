package controls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GerenciarFornecedor implements Initializable {

    @FXML
    private TextField txNome;

    @FXML
    private TextField txCnpj;

    @FXML
    private TextField txTel1;

    @FXML
    private TextField txId;

    @FXML
    private TextField txTel2;

    @FXML
    private TextField txCep;

    @FXML
    private TextField txNum;

    @FXML
    private TextField txRua;

    @FXML
    private TextField txComp;

    @FXML
    private TextField txBairro;

    @FXML
    private TextField txCidade;

    @FXML
    private TextField txEstado;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
