package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Categoria;
import models.Fornecedor;
import models.Produto;
import java.util.Scanner;

import javax.swing.*;

public class Main extends Application {
    static int idForn = 0;
    static int idCat = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("ControlX - Entrar");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
        */

        System.out.println("\t\t ControlX v1.1");
        System.out.println("\t\t MENU");
        System.out.print("\n1 - Adicionar Fornecedor\t2 - Adicionar Categoria" +
                "\n3 - Adicionar Produto");
        System.out.print("\nSELECAO: ");
        Scanner scan = new Scanner(System.in);
        int select = scan.nextInt();
        boolean sair = true;
        while(sair) {
            switch (select) {
                case 0:
                    sair = false;
                    break;
                case 1:         //ADD FORNECEDOR
                    Fornecedor f1 = new Fornecedor();
                    System.out.print("-----* ADICIONAR FORNECEDOR *-----");
                    f1.setId(idForn++);
                    System.out.print("\nID:" + f1.getId() + "\n");

                    System.out.print("Nome: ");
                    f1.setNome(scan.next());

                    System.out.print("CNPJ: ");
                    f1.setCnpj(scan.next());

                    System.out.print("Cidade: ");
                    f1.setCidade(scan.next());
                    //IMPLEMENTAR O RESTO DEPOIS
                    break;
                case 2:         //ADD CATEGORIA
                    Categoria c1 = new Categoria();
                    c1.setId(idCat++);
                    System.out.print("\nID:" + c1.getId() + "\n");

                    System.out.print("Nome: ");
                    c1.setNome(scan.next());
                    break;
            }
        }

        Categoria c1 = new Categoria();


        Fornecedor f1 = new Fornecedor();

        Produto p1 = new Produto();
        p1.setNome("Yakult");
        p1.setPreco(10.99);
        p1.setId(1);
        p1.setCat(c1);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
