package controls;

import DAO.ProdutoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Categoria;
import models.Fornecedor;
import models.Produto;

public class Main extends Application {

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        primaryStage.setTitle("ControlX - Entrar");
        stage = primaryStage;
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setResizable(false);
        primaryStage.show();

        Fornecedor forn = new Fornecedor();
        Categoria cat = new Categoria();
        Produto prod = new Produto();
        prod.setQtd(3);
        prod.setEstoqueMin(1);
        prod.setForn(forn);
        prod.setNome("Pão");
        prod.setPreco(10.00);
        prod.setTipoUn("Un");
        prod.setCat(cat);
        ProdutoDAO dao = new ProdutoDAO();
        dao.add(prod);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
