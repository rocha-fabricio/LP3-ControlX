package controls;

import DAO.CompraDAO;
import DAO.VendaDAO;
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
import models.Venda;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Historico implements Initializable {

    @FXML
    private TableView<Compra> tbCompras;

    @FXML
    private ChoiceBox cbFiltro;

    @FXML
    private TabPane tpPane;

    @FXML
    private TableView<Venda> tbVendas;

    @FXML
    private Tab tpCompra;

    @FXML
    private ChoiceBox cbFiltroCompra;

    @FXML
    private Button btDetalhesVenda;

    @FXML
    private Button btVoltarVendas;

    @FXML
    private Button btDetalhesCompra;

    @FXML
    private Tab tpVenda;

    @FXML
    private Button btVoltarCompras;

    VendaDAO vDAO = new VendaDAO();
    CompraDAO cDAO = new CompraDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            if(Login.getUser().getCargo() == 2){
                tpVenda.setDisable(true);
            } else if(Login.getUser().getCargo() == 3){
                tpCompra.setDisable(true);
            }
            ObservableList<String> datas = FXCollections.observableArrayList("Hoje", "Ultimos 7 dias", "Ultimo mês","Todas");
            cbFiltro.setItems(datas);
            cbFiltro.setValue("Todas");
            cbFiltroCompra.setItems(datas);
            cbFiltroCompra.setValue("Todas");
            fillTables();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/Historico.fxml"));
        root.setControllerFactory(c -> {
            return new Historico();
        });
        primaryStage.setTitle("ControlX - Histórico");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void fillTables() throws ClassNotFoundException {
        //Table VENDA

        tbVendas.getItems().clear();
        tbVendas.getColumns().clear();


        ObservableList<Venda> vendas = FXCollections.observableArrayList();

        for (Venda v: vDAO.listAll()) {
            vendas.add(new Venda(v.getId(), v.getUsuario(), v.getValor(), v.getData()));
        }

        TableColumn<Venda, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Venda, String> nomeColumn = new TableColumn<>("Usuário");
        nomeColumn.setMinWidth(200);
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        TableColumn<Venda, String> precoColumn = new TableColumn<>("Total (R$)");
        precoColumn.setMinWidth(50);
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));

        TableColumn<Venda, String> dataColumn = new TableColumn<>("Data");
        dataColumn.setMinWidth(50);
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));


        tbVendas.setItems(vendas);
        tbVendas.getColumns().addAll(idColumn, nomeColumn, precoColumn, dataColumn);

        //TABLE COMPRA ----------------------------------------- x -----------------------------------------

        tbCompras.getItems().clear();
        tbCompras.getColumns().clear();

        ObservableList<Compra> compras = FXCollections.observableArrayList();

        for (Compra c: cDAO.listAll()) {
            if(c.getStatus() == 1 )
                compras.add(new Compra(c.getId(), c.getUsuario(), c.getValor(), c.getProdutos(), c.getStatus(), c.getData(), c.getDataEntrega(), c.getDataFinal()));
        }

        TableColumn<Compra, String> cidColumn = new TableColumn<>("ID");
        cidColumn.setMinWidth(50);
        cidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Compra, String> cnomeColumn = new TableColumn<>("Usuário");
        cnomeColumn.setMinWidth(200);
        cnomeColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        TableColumn<Compra, String> cprecoColumn = new TableColumn<>("Total (R$)");
        cprecoColumn.setMinWidth(50);
        cprecoColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));

        TableColumn<Compra, String> cdataColumn = new TableColumn<>("Data Pedido");
        cdataColumn.setMinWidth(50);
        cdataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));

        TableColumn<Compra, String> cdataFinalColumn = new TableColumn<>("Data Pedido Finalizado");
        cdataFinalColumn.setMinWidth(50);
        cdataFinalColumn.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));


        tbCompras.setItems(compras);
        tbCompras.getColumns().addAll(cidColumn, cnomeColumn, cprecoColumn, cdataColumn, cdataFinalColumn);
    }

    public void botaoVoltar() throws IOException {
        new MenuPrincipal().show();
    }

    public void visualizarCompra() throws ClassNotFoundException, IOException {
        Compra cc =  cDAO.read(tbCompras.getSelectionModel().getSelectedItem().getId());
        new VisualizarCompra().show(cc, true);
    }
}
