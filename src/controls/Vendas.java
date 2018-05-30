package controls;

import DAO.VendaDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import models.Produto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.text.html.ListView;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import models.Produto;
import models.Usuario;
import models.Venda;

public class Vendas implements Initializable{
    @FXML
    TextField txQtdVenda;
    @FXML
    TextField txQtdEstoque;
    @FXML
    TextField txPrecoVenda;
    @FXML
    TextField txVendedor;
    @FXML
    TextField txPrecoUn;
    @FXML
    TextField txPesquisar;
    @FXML
    TextField txNome;
    @FXML
    TextField txId;
    @FXML
    TextField txPrecoTotal;
    @FXML
    TableView<Produto> tbProdutos;
    @FXML
    Button btVoltar;
    @FXML
    Button btRemover;
    @FXML
    Button btLimparVenda;
    @FXML
    Button btLimparText;
    @FXML
    Button btFinalizar;
    @FXML
    Button btAdicionar;
    @FXML
    javafx.scene.control.ListView lvProdutos;

    VendaDAO vDAO = new VendaDAO();
    ProdutoDAO pDAO = new ProdutoDAO();

    static List<Produto> produtos = new ArrayList<>();
    static double precoTotal = 0 ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            atvBotaoAdd();
            getUser();
            produtos.clear();
            txPrecoTotal.clear();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vendas(){

    }

    public void getUser(){
        txVendedor.setText(Login.getUser().getNome());
    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/Vendas.fxml"));
        root.setControllerFactory(c -> {
            return new Vendas();
        });
        primaryStage.setTitle("ControlX - Vendas");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void botaoVoltar() throws IOException{
        if(!produtos.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ControlX - Venda em andamento");
            alert.setResizable(false);
            alert.setHeaderText("Cancelar Venda");
            alert.setContentText("Existem produtos adicionados a lista de vendas, se sair agora, a venda será cancelada.\n Deseja realmente sair?");
            alert.getButtonTypes();

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent())
                return;
            else if(result.get() == ButtonType.OK) {
                new MenuPrincipal().show();
                produtos.clear();
            }
            else if(result.get() == ButtonType.CANCEL)
                return;
        }
        else {
            new MenuPrincipal().show();
            produtos.clear();
        }

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
        txPrecoVenda.setText(String.valueOf("R$ " + p.getPreco()));
    }
    public void clearFields(){
        txNome.clear();
        txId.clear();
        txPesquisar.clear();
        txPrecoUn.clear();
        txPrecoVenda.clear();
        txQtdEstoque.clear();
        txQtdVenda.clear();
        atvBotaoAdd();
    }


    public void addProds()throws ClassNotFoundException {
        //Dando cast do objeto Produto selecionado na ListView
        Produto pro = (Produto) lvProdutos.getSelectionModel().getSelectedItem();
        for (Produto p: produtos) {
            if (p.getId() == pro.getId()) { //Se o produto ja tiver adicionado na lista, cancelar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ControlX - Produto duplicado1");
                alert.setHeaderText("Produto duplicado2");
                alert.setContentText("Esse produto já está adicionado ao carrinho de venda, operação cancelada!");

                alert.showAndWait();
                return;
            }
        }
        //Lista estática produtos terá como qtd a quantidade de produto que foi vendida
        pro.setQtd(Double.parseDouble(txQtdVenda.getText()));
        //Adicionando o produto selecionado a lista
        produtos.add(pro);

        tbProdutos.getItems().clear();
        tbProdutos.getColumns().clear();

        ObservableList<Produto> prods = FXCollections.observableArrayList();

        for (Produto p : produtos) { //Para cada produto presente na lista estática
            //Adicionamos na observable list
            prods.add(new Produto(p.getId(), p.getNome(), p.getPreco(), p.getQtd(), p.getTipoUn(), p.getCat()));
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


        tbProdutos.setItems(prods);

        tbProdutos.getColumns().addAll(idColumn, nomeColumn, precoColumn, qtdColumn);
        clearFields();
        //DecimalFormat df = new DecimalFormat("#0.00");
        for(Produto p: produtos){
            precoTotal += (p.getQtd()*p.getPreco());
        }
        txPrecoTotal.setText(String.valueOf(precoTotal));//df.format(precoTotal)));
    }

    public void clearTable(){
        produtos.clear();
        precoTotal = 0;
        txPrecoTotal.setText(String.valueOf(precoTotal));
        tbProdutos.getItems().clear();
        tbProdutos.getColumns().clear();

    }
    public void atvBotaoAdd(){
        if(txNome.getText().isEmpty() || txPrecoVenda.getText().isEmpty() ||
                txPrecoVenda.getText().isEmpty() || txPrecoUn.getText().isEmpty() ||
                txQtdEstoque.getText().isEmpty() || txQtdVenda.getText().isEmpty() || txId.getText().isEmpty()){
            btAdicionar.setDisable(true);
        } else {
            btAdicionar.setDisable(false);
        }
    }
    public void finalizarCompra() throws ClassNotFoundException, IOException {
        Venda venda = new Venda();
        venda.setProdutos(produtos);
        Date data = new Date(System.currentTimeMillis());
        venda.setData(data);
        //DecimalFormat df = new DecimalFormat("#0.00");
        //txPrecoTotal.setText(String.valueOf(df.format(precoTotal)));
        venda.setValor(precoTotal);
        venda.setUsuario(Login.getUser());
        vDAO.vender(venda);
        boolean sucess = true;
        if(sucess){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ControlX - Venda Concluída");
            alert.setHeaderText("Produtos vendidos com sucesso");
            alert.setContentText("A venda foi finalizada com sucesso! Cheque o histórico para mais detalhes.");
            alert.showAndWait();
            new MenuPrincipal().show();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ControlX - Venda Malsucedida");
            alert.setHeaderText("Algo deu errado");
            alert.setContentText("Um erro inesperado aconteceu! A Venda não foi finalizada.");
            alert.showAndWait();
        }
    }
}
