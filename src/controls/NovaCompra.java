package controls;

import DAO.CompraDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Compra;
import models.Produto;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class NovaCompra implements Initializable {

    ProdutoDAO pDAO = new ProdutoDAO();

    @FXML
    private TextField txNome;

    @FXML
    private ComboBox<Produto> cbPesquisar;

    @FXML
    private Label lbNome;

    @FXML
    private TextField txId;

    @FXML
    private TextField txQtdEstoque;

    @FXML
    private TextField txPrecoUn;

    @FXML
    private TextField txQtdCompra;

    @FXML
    private TextField txPrecoCompra;

    @FXML
    private Button btAdicionar;

    @FXML
    private Button btLimparText;

    @FXML
    private TextField txPrecoTotal;

    @FXML
    private Label lbPesquisar;

    @FXML
    private TableView<Produto> tbProdutos;

    @FXML
    private Label lbCarrinho;

    @FXML
    private Label lbVendedor;

    @FXML
    private TextField txVendedor;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btFinalizar;

    @FXML
    private Button btLimparVenda;

    @FXML
    private Button btRemover;

    @FXML
    private DatePicker dtEntrega;

    Produto AOO = new Produto();
    CompraDAO cdao = new CompraDAO();

    boolean view = false;
    int id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(view){
                visualizarCompra();
            }else {
                cbPesquisar.setValue(AOO);
                autoFill();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    NovaCompra(){

    }

    NovaCompra(boolean view, int id){
        this.view = view;
        this.id = id;
    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/NovaCompra.fxml"));
        root.setControllerFactory(c -> {
            return new NovaCompra();
        });
        primaryStage.setTitle("ControlX - Nova Compra");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void show(boolean view, int id) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/NovaCompra.fxml"));
        root.setControllerFactory(c -> {
            return new NovaCompra(view, id);
        });
        primaryStage.setTitle("ControlX - Visualizar Compra");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void visualizarCompra() throws ClassNotFoundException {
        btRemover.setDisable(true);
        btAdicionar.setDisable(true);
        btRemover.setDisable(true);
        btFinalizar.setDisable(true);
        btLimparText.setDisable(true);
        cbPesquisar.setDisable(true);

        dtEntrega.setEditable(false);
        txPrecoTotal.setEditable(false);
        txVendedor.setEditable(false);

        listView(cdao.listProdsCompra(id));
        Compra c = cdao.read(id);
        dtEntrega.setValue(LocalDate.parse(c.getDataEntrega().toString()));
        txPrecoTotal.setText(Double.toString(c.getValor()));
        txVendedor.setText(c.getUsuario().getNome());
    }

    public void listView(List <Produto> prods){
        tbProdutos.getItems().clear();
        tbProdutos.getColumns().clear();

        ObservableList<Produto> lista = FXCollections.observableArrayList();

        for (Produto p : prods) {
            lista.add(new Produto(p.getId(), p.getNome(), p.getPreco(), p.getQtd(), p.getTipoUn(), p.getCat()));
        }

        TableColumn<Produto, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Produto, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setMinWidth(250);
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Produto, Double> qtdColumn = new TableColumn<>("Qtd");
        qtdColumn.setMinWidth(60);
        qtdColumn.setCellValueFactory(new PropertyValueFactory<>("qtdProduto"));

        TableColumn<Produto, String> precoColumn = new TableColumn<>("Preço (R$)");
        precoColumn.setMinWidth(60);
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("precoUnProduto"));

        tbProdutos.setItems(lista);

        tbProdutos.getColumns().addAll(idColumn, nomeColumn, qtdColumn, precoColumn);
    }

    public void botaoVoltar() throws IOException {
        new Compras().show();
    }

    public void autoFill() throws ClassNotFoundException {
        ObservableList<Produto> produtos = FXCollections.observableArrayList();

        cbPesquisar.getItems().removeAll();
        if (
                cbPesquisar.getSelectionModel().getSelectedItem() == null) {
            for (Produto p : pDAO.listAll()) {
                produtos.add(p);
            }
            cbPesquisar.setItems(produtos);
            System.out.println("IF");

        } else {
            String pesquisa = cbPesquisar.getValue().toString();
            for (Produto p : pDAO.listAllByName(pesquisa)) {
                produtos.add(p);
            }
            cbPesquisar.setItems(produtos);
            cbPesquisar.show();
            System.out.println("ELSE");
        }

    }

    public void showProd() {
        try {
            Produto prd = (Produto) cbPesquisar.getValue();
            //txId.setText(Integer.toString(prd.getId()));
            System.out.println("SHOW PROD");
            txId.setText(String.valueOf(prd.getId()));
            txNome.setText(prd.getNome());
            txPrecoUn.setText(String.valueOf(prd.getPreco()));
            txQtdEstoque.setText(String.valueOf(prd.getQtd()));

            System.out.println("TRY");
        } catch (Exception e) {

        }

    }

    public void comprar() throws ClassNotFoundException {
        if(tbProdutos.getItems().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ControlX - Aviso");
            alert.setHeaderText("Impossível concluir a compra");
            alert.setContentText("Você deve adicionar ao menos um produto\n na lista de compras.");

            alert.showAndWait();
        }else {

            Compra c = new Compra();
            c.setUsuario(Login.getUser());
            c.setValor(Double.parseDouble(txPrecoTotal.getText()));

            Date data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            formatador.format(data);
            c.setData(data);

            Date dataE = new Date(dtEntrega.getValue().toString());
            c.setDataEntrega(dataE);

            cdao.comprar(c);
        }
    }
}
