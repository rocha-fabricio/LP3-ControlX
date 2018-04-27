package controls;

import DAO.*;
import database.ConexaoMySQL;
import database.Popular;
import javafx.application.Application;
import javafx.stage.Stage;
import models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {
    static int idForn = 10;
    static int idCat = 10;
    static int idProd = 10;
    static int idVenda = 10;
    static int idCompra = 10;

    static ProdutoDAO prodDAO = new DAO.ProdutoDAO();
    static CategoriaDAO catDAO = new DAO.CategoriaDAO();
    static FornecedorDAO fornDAO = new DAO.FornecedorDAO();
    static UsuarioDAO userDAO = new DAO.UsuarioDAO();
    static CompraDAO compraDAO = new CompraDAO();
    static VendaDAO vendaDAO = new VendaDAO();

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("ControlX - Entrar");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
        */

        //------------------------------------------------
        //Populando Banco de Dados
        Popular bd = new Popular(fornDAO, catDAO, prodDAO);
        bd.popularFornecedor();
        bd.popularCategoria();
        bd.popularProduto();
        //------------------------------------------------

        //ConexaoMySQL.getConexaoMySQL();


        boolean sair = true;
        while(sair) {
            int id;
            for (int i = 0; i < 3; ++i)
                System.out.println("-----x-----x-----x-----x-----x-----x-----x-----x-----");
            System.out.println("\t\t\t\t\tControlX v1.1");
            System.out.print("------x-----x-----x----- MENU -----x-----x-----x----- " +
                    "\n-----x-----x-----x-----x ADICIONAR x-----x-----x-----x-----");
            System.out.print("\n1 - Adicionar Fornecedor\t2 - Adicionar Categoria de Produto" +
                    "\n3 - Adicionar Produto" +

                    "\n-----x-----x-----x-----x EDITAR x-----x-----x-----x-----\n"
                    + "11 - Editar Fornecedores\t\t 12 - Editar Categorias\n13 - Editar Produtos"

                    + "\n-----x-----x-----x-----x REMOVER x-----x-----x-----x-----\n"
                    + "16 - Remover Fornecedores\t\t 17 - Remover Categorias\n18 - Remover Produtos"

                    + "\n-----x-----x-----x-----x LISTAR x-----x-----x-----x-----\n" +
                    "4 - Listar Fornecedores\t\t5 - Listar Categorias\n6 - Listar Produtos\t\t\t" +
                    "7 - Listar Vendas\n8 - Listar Compras");

            System.out.print("\n-----x-----x-----x-----x-----x-----x-----x-----x-----\n" +
                    "9 - Vender" +
                    "\t\t\t\t\t10 - Comprar");
            System.out.print("\n-----x-----x-----x-----x-----x-----x-----x-----x-----\n0 - Sair" +
                    "\n-----x-----x-----CONTROLX v1.1-----x-----x-----");
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


                    System.out.println("Insira o ID do Fornecedor:");
                    System.out.println("\t\t---x FORNECEDORES x---");
                    fornDAO.listAll();
                    System.out.print("\nID:");
                    p1.setForn(fornDAO.read(scan.nextInt()));


                    System.out.println("Insira o ID da Categoria:");
                    System.out.println("\t\t---x CATEGORIAS x---");
                    catDAO.listAll();
                    System.out.print("\nID:");
                    p1.setCat(catDAO.read(scan.nextInt()));

                    prodDAO.add(p1);
                    break;


                case 4:         //LISTAR FORNECEDORES
                    fornDAO.listAll();

                    System.in.read();
                    break;


                case 5:         //LISTAR CATEGORIAS
                    catDAO.listAll();

                    System.in.read();
                    break;


                case 6:         //LISTAR PRODUTOS
                    prodDAO.listAll();

                    System.in.read();
                    break;


                case 7:         //LISTAR VENDAS
                    vendaDAO.listAll();

                    System.in.read();
                    break;


                case 8:         //LISTAR COMPRAS
                    compraDAO.listAll();

                    System.in.read();
                    break;


                case 9:         //VENDER
                    List<Produto> produtosv = new ArrayList<Produto>();
                    Venda venda = new Venda();
                    System.out.println("\t\t\tVENDER");
                    int i = 1;
                    while(i == 1){
                        prodDAO.listAll();
                        Produto prodv = new Produto(); // PRODUTO A SER INSERIDO NA LISTA DE VENDA
                        System.out.print("\nID: ");
                        Produto prode = prodDAO.read(scan.nextInt()); // PRODUTO COM VALORES DO ESTOQUE
                        System.out.print("\nQTD de VENDA: ");
                        prodv.setQtd(scan.nextDouble());    //QUANTIDADE DE VENDA FICARA SALVA NESSE OBJ
                        //
                        prodv.setId(prode.getId());
                        prodv.setCat(prode.getCat());
                        prodv.setForn(prode.getForn());
                        prodv.setEstoqueMin(prode.getEstoqueMin());
                        prodv.setNome(prode.getNome());
                        prodv.setPreco(prode.getPreco());
                        prodv.setTipoUn(prode.getTipoUn());
                        //
                        produtosv.add(prodv);
                        System.out.println("Para adicionar mais produtos digite '1', para finalizar digite '0'");
                        i = scan.nextInt();
                    }
                    venda.setId(idVenda++);
                    //venda.setData(LocalDateTime.now());
                    venda.setProdutos(produtosv);
                    vendaDAO.vender(venda);
                    System.out.println("Produto(s) vendido(s) com sucesso!");
                    System.in.read();
                    break;


                case 10:         //COMPRAR
                    List<Produto> produtosc = new ArrayList<Produto>();
                    Compra compra = new Compra();
                    System.out.println("\t\t\tCOMPRAR");

                    int j = 1;
                    while(j == 1 ) {
                        prodDAO.listAll();
                        Produto prodc = new Produto(); //PRODUTO A SER INSERIDO NA LISTA DE COMPRA
                        System.out.print("\nID: ");
                        Produto prodEstq = prodDAO.read(scan.nextInt()); // PRODUTO COM VALORES DO ESTOQUE
                        System.out.print("\nQTD de Compra: ");
                        prodc.setQtd(scan.nextDouble());                //QUANTIDADE DE COMPRA FICARA SALVA NESSE OBJ
                        //
                        prodc.setId(prodEstq.getId());
                        prodc.setPreco(prodEstq.getPreco());
                        prodc.setNome(prodEstq.getNome());
                        prodc.setCat(prodEstq.getCat());
                        prodc.setTipoUn(prodEstq.getTipoUn());
                        prodc.setForn(prodEstq.getForn());
                        prodc.setEstoqueMin(prodEstq.getEstoqueMin());
                        //
                        produtosc.add(prodc);
                        System.out.println("Para adicionar mais produtos digite '1', para finalizar digite '0'");
                        j = scan.nextInt();
                    }
                    compra.setId(idCompra++);
                    compra.setProdutos(produtosc);
                    compraDAO.comprar(compra);
                    System.out.println("Produto(s) comprado(s) com sucesso!");
                    System.in.read();
                    break;

                case 11:
                    System.out.println("--------Listando Todos os Fornecedores-----------");
                    fornDAO.listAll();
                    System.out.println("\nDigite o id do Fornecedor:");
                    id = scan.nextInt();

                    //Dados a serem editados:
                    Fornecedor fornUP = new Fornecedor();
                    Fornecedor fornAUX = fornDAO.read(id);

                    fornUP.setId(id);
                    int aux = 1;
                    while(aux == 1)
                    {
                        System.out.println("-----Opções de Edite -----");
                        System.out.println("Deseja alterar o Nome? Se sim digite 1"
                                + "\nDeseja alterar o Endereço? Se sim digite 2"
                                + "\nDeseja alterar o telefone? Se sim digite 3");
                        int e = scan.nextInt();

                        if(e == 1)
                        {
                            System.out.println("Digite o Nome:");
                            fornUP.setNome(scan.next());
                            fornUP.setTelefone1(fornAUX.getTelefone1());
                            fornUP.setTelefone2(fornAUX.getTelefone2());
                            fornUP.setCnpj(fornAUX.getCnpj());
                            fornUP.setCep(fornAUX.getCep());
                            fornUP.setRua(fornAUX.getRua());
                            fornUP.setNum(fornAUX.getNum());
                            fornUP.setBairro(fornAUX.getBairro());
                            fornUP.setCidade(fornAUX.getCidade());
                            fornUP.setEstado(fornAUX.getEstado());

                            fornAUX =  fornUP;

                        }

                        if(e == 2)
                        {
                            fornUP.setNome(fornAUX.getNome());
                            fornUP.setTelefone1(fornAUX.getTelefone1());
                            fornUP.setTelefone2(fornAUX.getTelefone2());
                            fornUP.setCnpj(fornAUX.getCnpj());
                            System.out.println("Digite o novo CEP:");
                            fornUP.setCep(scan.nextLong());
                            System.out.println("Rua:");
                            fornUP.setRua(scan.next());
                            System.out.println("Numero:");
                            fornUP.setNum(scan.next());
                            System.out.println("Bairro:");
                            fornUP.setBairro(scan.next());
                            System.out.println("Cidade:");
                            fornUP.setCidade(scan.next());
                            System.out.println("Estado:");
                            fornUP.setEstado(scan.next());

                            fornAUX =  fornUP;

                        }

                        if(e == 3)
                        {
                            fornUP.setNome(fornAUX.getNome());
                            System.out.println("Digite o Telefone1:");
                            fornUP.setTelefone1(scan.next());
                            System.out.println("Digite o Telefone2:");
                            fornUP.setTelefone2(scan.next());
                            fornUP.setCnpj(fornAUX.getCnpj());
                            fornUP.setCep(fornAUX.getCep());
                            fornUP.setRua(fornAUX.getRua());
                            fornUP.setNum(fornAUX.getNum());
                            fornUP.setBairro(fornAUX.getBairro());
                            fornUP.setCidade(fornAUX.getCidade());
                            fornUP.setEstado(fornAUX.getEstado());


                        }

                        fornDAO.up(fornUP);
                        System.out.println("Quer continuar modificando? Digite 1 para conitnuar, 0 para sair");
                        aux = scan.nextInt();
                    }


                    break;

                case 16:
                    System.out.println("--------Listando Todos os Fornecedores-----------");
                    fornDAO.listAll();
                    System.out.println("\nDigite o id do Fornecedor:");
                    id = scan.nextInt();
                    fornDAO.del(fornDAO.read(id));
                    System.in.read();
                    break;

                case 17:
                    System.out.println("--------Listando Todas as Categorias-----------");
                    catDAO.listAll();
                    System.out.println("\nDigite o id da Categoria:");
                    id = scan.nextInt();
                    catDAO.del(catDAO.read(id));
                    System.in.read();
                    break;

                case 18:
                    System.out.println("----------Listando Todos os Produtos----------");
                    prodDAO.listAll();
                    System.out.println("\nDigite o id do Produto:");
                    id = scan.nextInt();
                    prodDAO.del(prodDAO.read(id));
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
