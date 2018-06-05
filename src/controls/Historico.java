package controls;

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
import models.Venda;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Historico implements Initializable {

    @FXML
    Button btVoltar;
    @FXML
    Button btDetalhes;
    @FXML
    ChoiceBox cbFiltro;
    @FXML
    Tab tpCompra;
    @FXML
    Tab tpVenda;
    @FXML
    TableView tbVendas;
    @FXML
    TableView tbCompras;
    @FXML
    TabPane tpPane;

    VendaDAO vDAO = new VendaDAO();
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
        tbVendas.getItems().clear();
        tbVendas.getColumns().clear();
        //tbCompras.getItems().clear();
        //tbCompras.getColumns().clear();

        ObservableList<Venda> vendas = FXCollections.observableArrayList();

        for (Venda v: vDAO.listAll()) {
            vendas.add(new Venda(v.getId(), v.getUsuario(), v.getValor(), v.getData()));
        }

        TableColumn<Venda, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(30);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Venda, String> nomeColumn = new TableColumn<>("Usuário");
        nomeColumn.setMinWidth(150);
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        TableColumn<Venda, String> precoColumn = new TableColumn<>("Total (R$)");
        precoColumn.setMinWidth(50);
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));

        TableColumn<Venda, String> qtdColumn = new TableColumn<>("Data");
        qtdColumn.setMinWidth(50);
        qtdColumn.setCellValueFactory(new PropertyValueFactory<>("data"));


        tbVendas.setItems(vendas);

        tbVendas.getColumns().addAll(idColumn, nomeColumn, precoColumn, qtdColumn);
    }

    public void botaoVoltar() throws IOException {
        new MenuPrincipal().show();
    }
}
