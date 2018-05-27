package controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipal
{

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/MenuPrincipal.fxml"));
        root.setControllerFactory(c -> {
            return new MenuPrincipal();
        });
        primaryStage.setTitle("ControlX - Menu");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void btProdutos() throws IOException {
        new Estoque().show();
    }

    public void btCompras() throws IOException {
        new Compras().show();
    }
    public void btVendas() throws IOException{
        new Vendas().show();
    }
    public void botaoVoltar() throws IOException{
        new Login().show();
    }
    public void botaoFornecedores() throws IOException {
        new Fornecedores().show();
    }

}
