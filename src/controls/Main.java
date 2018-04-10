package controls;

import DAO.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class Main extends Application {
    static int idForn = 0;
    static int idCat = 0;
    static int idProd = 0;

    static ProdutoDAO prodDAO = new DAO.ProdutoDAO();
    static CategoriaDAO catDAO = new DAO.CategoriaDAO();
    static FornecedorDAO fornDAO = new DAO.FornecedorDAO();
    static UsuarioDAO userDAO = new DAO.UsuarioDAO();
    static ComprarDAO compraDAO = new DAO.ComprarDAO();
    static VenderDAO vendaDAO = new DAO.VenderDAO();

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
            System.out.print("\n1 - Adicionar Fornecedor\t" +
                    "2 - Adicionar Categoria de Produto" +
                    "\n3 - Adicionar Produto\n-----x-----x-----x-----x-----x-----x-----x-----x-----\n" +
                    "4 - Listar Fornecedores" +
                    "\t5 - Listar Categorias\n6 - Listar Produtos");
            System.out.print("\n-----x-----x-----x-----x-----x-----x-----x-----x-----\n" +
                    "7 - Vender" +
                    "\t8 - Comprar");
            System.out.print("\n-----x-----x-----x-----x-----x-----x-----x-----x-----\n0 - Sair" +
                    "\n-----x-----x-----CONTROLX v1.0-----x-----x-----");
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

                    fornDAO.add(f1);
                    break;
                case 2:         //ADD CATEGORIA
                    Categoria c1 = new Categoria();
                    System.out.print("-----* ADICIONAR CATEGORIA *-----");
                    c1.setId(idCat++);
                    System.out.print("\nID:" + c1.getId() + "\n");

                    System.out.print("Nome: ");
                    c1.setNome(scan.next());

                    catDAO.add(c1);
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

                    fornDAO.listAll();
                    System.out.println("Selecione o id do Fornecedor:");
                    int id = scan.nextInt();
                    p1.setForn(fornDAO.select(id));

                    catDAO.listAll();
                    System.out.println("Selecione o id da categoria:");
                    id = scan.nextInt();
                    p1.setCat(catDAO.read(id));

                    prodDAO.add(p1);
                    break;
                case 4:
                    fornDAO.listAll();

                    System.in.read();
                    break;
                case 5:
                    catDAO.listAll();

                    System.in.read();
                    break;
                case 6:
                    prodDAO.listAll();

                    System.in.read();
                    break;
                case 7: //Vender

                    ArrayList<Produto> produtosv = new ArrayList<Produto>();
                    Venda venda = new Venda();
                    Produto prodv = new Produto();
                    prodDAO.listAll();
                    System.in.read();

                    System.out.println("Digite o id do produto que deseja vender: ");
                    for(int i = 1; i != 0; ){
                        prodv.setId(scan.nextInt());
                        prodDAO.read(prodv);
                        produtosv.add(prodv);
                        System.out.println("Para adicionar mais produtos digite 1, se ja terminou de adiconar digite 0");
                        i = scan.nextInt();
                    }

                    venda.setProdutos(produtosv);
                    vendaDAO.vender(venda);
                    System.out.println("Produto(s) comprado(s) com sucesso!");
                    System.in.read();

                    break;
                case 8: //Comprar
                    ArrayList<Produto> produtosc = new ArrayList<Produto>();
                    Compra compra = new Compra();
                    Produto prodc = new Produto();
                    prodDAO.listAll();
                    System.in.read();

                    System.out.println("Digite o id do produto que deseja Comprar estoque:");
                    for(int i = 1; i != 0; ) {
                        prodc.setId(scan.nextInt());
                        prodDAO.read(prodc);
                        produtosc.add(prodc);
                        System.out.println("Para adicionar mais produtos digite 1, se ja terminou de adiconar digite 0");
                        i = scan.nextInt();
                    }
                    compra.setProdutos(produtosc);
                    compraDAO.comprar(compra);
                    System.out.println("Produto(s) comprado(s) com sucesso!");
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
