package views;

import DAO.ProdutoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Categoria;
import models.Fornecedor;
import models.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class Main extends Application {
    static int idForn = 0;
    static int idCat = 0;
    static int idProd = 0;
    static ProdutoDAO prodDAO = new DAO.ProdutoDAO();
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("ControlX - Entrar");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
        */


        boolean sair = true;
        while(sair) {

            for (int i = 0; i < 5; ++i)
                System.out.println("-----x-----x-----x-----x-----x-----x-----x-----x-----");
            System.out.println("\t\t\t\t\tControlX v1.1");
            System.out.print("------x-----x-----x----- MENU -----x-----x-----x-----");
            System.out.print("\n1 - Adicionar Fornecedor\t2 - Adicionar Categoria" +
                    "\n3 - Adicionar Produto\n-----x-----x-----x-----x-----x-----x-----x-----x-----\n4 - Listar Fornecedores" +
                    "\t5 - Listar Categorias\n6 - Listar Produtos");
            System.out.print("\n-----x-----x-----x-----x-----x-----x-----x-----x-----\n0 - Sair" +
                    "\n   -----x-----x-----CONTROLX v1.0-----x-----x-----");
            System.out.print("\nSELECAO: ");
            Scanner scan = new Scanner(System.in);
            int select = scan.nextInt();

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
                    System.out.print("-----* ADICIONAR CATEGORIA *-----");
                    c1.setId(idCat++);
                    System.out.print("\nID:" + c1.getId() + "\n");

                    System.out.print("Nome: ");
                    c1.setNome(scan.next());
                    break;
                case 3:         //ADD PRODUTO
                    Produto p1 = new Produto();
                    System.out.print("-----* ADICIONAR PRODUTO *-----");
                    p1.setId(idProd++);
                    System.out.print("\nID:" + p1.getId() + "\n");

                    System.out.print("Nome: ");
                    p1.setNome(scan.next());

                    System.out.print("Preco: ");
                    p1.setPreco(scan.nextDouble());

                    System.out.print("Quantidade: ");
                    p1.setQtd(scan.nextDouble());

                    System.out.print("Tipo Un: ");
                    p1.setTipoUn(scan.next());

                    System.out.print("Estoque Minimo: ");
                    p1.setEstoqueMin(scan.nextDouble());


                    prodDAO.add(p1);
                    //p1.setForn(f);
                    //p1.setCat(c);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    List<Produto> prods = prodDAO.listAll();
                    for(Produto p : prods) {
                        System.out.println("ID: " + p.getId() +  " || Nome: " + p.getNome() + " || R$" + p.getPreco() + " || " + p.getQtd() + " " + p.getTipoUn() + " || Estoque Min: " + p.getEstoqueMin());
                    }

                    System.in.read();
                    break;
                default:
                    break;
            }
        }
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
