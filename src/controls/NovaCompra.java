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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class NovaCompra implements Initializable {

    ProdutoDAO pDAO = new ProdutoDAO();

    @FXML
    private TextField txNome;

    @FXML
    private TextField txPesquisar;

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

    @FXML
    javafx.scene.control.ListView lvProdutos;

    Produto AOO = new Produto();
    CompraDAO cdao = new CompraDAO();

    static List<Produto> produtos = new ArrayList<>();
    static double precoTotal = 0 ;

    boolean view = false;
    int id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            atvBotaoAdd();
            getUser();
            produtos.clear();
            txPrecoTotal.clear();
            precoTotal = 0;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    NovaCompra(){

    }

    NovaCompra(boolean view, int id){
        this.view = view;
        this.id = id;
    }

    public void getUser(){
        txVendedor.setText(Login.getUser().getNome());
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

        btRemover.setDisable(true);
        btFinalizar.setDisable(true);
        btLimparText.setDisable(true);
        txPesquisar.setDisable(true);

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

    public void autoComplete() throws ClassNotFoundException {
        String pesquisa = txPesquisar.getText();
        ObservableList<Produto> prods = FXCollections.observableArrayList();
        for (Produto p : pDAO.listAllByName(pesquisa)) {
            prods.add(p);
        }
        lvProdutos.setItems(prods);
    }
    public void fillFields(){
        Produto p = (Produto) lvProdutos.getSelectionModel().getSelectedItem();
        txId.setText(String.valueOf(p.getId()));
        txNome.setText(p.getNome());
        txQtdEstoque.setText(String.valueOf(p.getQtd()) + " " + p.getTipoUn());
        txPrecoUn.setText(String.valueOf("R$ " + p.getPreco()));
        txPrecoCompra.setText(String.valueOf("R$ " + p.getPreco()));
    }
    public void clearFields(){
        txNome.clear();
        txId.clear();
        txPesquisar.clear();
        txPrecoUn.clear();
        txPrecoCompra.clear();
        txQtdEstoque.clear();
        txQtdCompra.clear();
        atvBotaoAdd();
        lvProdutos.setItems(null);
    }

    public void addProds()throws ClassNotFoundException {
        //Dando cast do objeto Produto selecionado na ListView
        Produto pro = (Produto) lvProdutos.getSelectionModel().getSelectedItem();
        for (Produto p : produtos) {
            if (p.getId() == pro.getId()) { //Se o produto ja tiver adicionado na lista, cancelar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ControlX - Produto duplicado");
                alert.setHeaderText("Produto já adicionado na venda");
                alert.setContentText("Esse produto já está adicionado ao carrinho de venda, operação cancelada!");

                alert.showAndWait();
                return;
            }
        }
        if (Double.parseDouble(txQtdCompra.getText()) > pro.getQtd()) {//Se qtdVenda > qtdEstoque
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ControlX - Quantidade inválida");
            alert.setHeaderText("Impossível vender " + txQtdCompra.getText() + " " + pro.getTipoUn() + " do produto");
            alert.setContentText("Por favor verifique se a quantidade de venda está \n disponível no estoque");

            alert.showAndWait();
            return;
        }
        //Lista estática produtos terá como qtd a quantidade de produto que foi vendida
        pro.setQtd(Double.parseDouble(txQtdCompra.getText()));
        //Adicionando o produto selecionado a lista
        produtos.add(pro);
        refreshTable();

    }

    public void refreshTable(){
        tbProdutos.getItems().clear();
        tbProdutos.getColumns().clear();
        lvProdutos.setItems(null);

        ObservableList<Produto> prod = FXCollections.observableArrayList();

        for (Produto p : produtos) { //Para cada produto presente na lista estática
            //Adicionamos na observable list
            prod.add(new Produto(p.getId(), p.getNome(), p.getPreco(), p.getQtd(), p.getTipoUn(), p.getCat()));
        }

        TableColumn<Produto, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(30);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Produto, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setMinWidth(150);
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Produto, String> precoColumn = new TableColumn<>("Preço (R$)");
        precoColumn.setMinWidth(50);
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        TableColumn<Produto, String> qtdColumn = new TableColumn<>("Qtd de Venda");
        qtdColumn.setMinWidth(50);
        qtdColumn.setCellValueFactory(new PropertyValueFactory<>("qtd"));


        tbProdutos.setItems(prod);

        tbProdutos.getColumns().addAll(idColumn, nomeColumn, precoColumn, qtdColumn);
        clearFields();
        DecimalFormat df = new DecimalFormat("#0.00");
        precoTotal = 0;
        for(Produto p: produtos){
            precoTotal += (p.getQtd()*p.getPreco());
        }
        txPrecoTotal.setText(String.valueOf(df.format(precoTotal)));
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
            c.setProdutos(produtos);
            c.setUsuario(Login.getUser());
            c.setValor(precoTotal);

            Date data = new Date(System.currentTimeMillis());
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            formatador.format(data);
            c.setData(data);

            //Date dataE = dtEntrega.getValue();
            c.setDataEntrega(data);

            for(Produto p: produtos){
                Produto pEstoque = pDAO.read(p.getId());
                pEstoque.setQtd(pEstoque.getQtd() + p.getQtd());
                pDAO.up(pEstoque);
            }

            cdao.comprar(c);
        }
    }

    public void atvBotaoAdd(){

    }


}
