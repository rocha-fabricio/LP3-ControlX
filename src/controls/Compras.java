package controls;

import DAO.CompraDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Compra;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Compras implements Initializable {

    @FXML
    private TableView<Compra> tbCFinalizadas;
    @FXML
    private TableView<Compra> tbCPendentes;

    CompraDAO cdao = new CompraDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listViewFinalizadas(cdao.listAll());
            listViewPendentes(cdao.listAll());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/Compras.fxml"));
        root.setControllerFactory(c -> {
            return new Compras();
        });
        primaryStage.setTitle("ControlX - Compras");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void listViewFinalizadas(List<Compra> compras) throws ClassNotFoundException {
        //---------- Compras Finalizadas ---------

        tbCFinalizadas.getItems().clear();
        tbCFinalizadas.getColumns().clear();

        ObservableList<Compra> lista = FXCollections.observableArrayList();

        for (Compra c : compras) {
            if (c.getStatus() == 0) { //------------------ = 1
                lista.add(new Compra(c.getId(), c.getUsuario(), c.getValor(), c.getProdutos(), c.getStatus(), c.getData(), c.getDataEntrega(), c.getDataFinal()));
            }
        }

        TableColumn<Compra, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Compra, String> usuarioColumn = new TableColumn<>("Usuario");
        usuarioColumn.setMinWidth(120);
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        TableColumn<Compra, Integer> valorColumn = new TableColumn<>("Valor total");
        valorColumn.setMinWidth(100);
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));

        TableColumn<Compra, String> dataColumn = new TableColumn<>("Entregue em");
        dataColumn.setMinWidth(100);
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));

        tbCFinalizadas.setItems(lista);
        tbCFinalizadas.getColumns().addAll(idColumn, usuarioColumn, valorColumn, dataColumn);

    }

    public void listViewPendentes(List<Compra> compras) throws ClassNotFoundException {
        //---------- Compras Pendentes ---------

        tbCPendentes.getItems().clear();
        tbCPendentes.getColumns().clear();

        ObservableList<Compra> lista = FXCollections.observableArrayList();

        for (Compra c : compras) {
            if (c.getStatus() == 0)
                lista.add(new Compra(c.getId(), c.getUsuario(), c.getValor(), c.getProdutos(), c.getStatus(), c.getData(), c.getDataEntrega(), c.getDataFinal()));
        }

        TableColumn<Compra, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Compra, String> usuarioColumn = new TableColumn<>("Usuario");
        usuarioColumn.setMinWidth(120);
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        TableColumn<Compra, Integer> valorColumn = new TableColumn<>("Valor total");
        valorColumn.setMinWidth(100);
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));

        TableColumn<Compra, Date> dataColumn = new TableColumn<>("Previsão:");
        dataColumn.setMinWidth(100);
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataEntrega"));

        tbCPendentes.setItems(lista);
        tbCPendentes.getColumns().addAll(idColumn, usuarioColumn, valorColumn, dataColumn);
    }

    public void botaoVoltar(){

    }

    public void botaoAddCompra(){

    }

    public void botaoVisualizar(){

    }

    public void botaoFinalizar(){

    }

}
