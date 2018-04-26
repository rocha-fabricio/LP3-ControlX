package database;

import DAO.CategoriaDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import models.Categoria;
import models.Fornecedor;
import models.Produto;

public class Popular {

    private FornecedorDAO fornDAO;
    private CategoriaDAO catDAO;
    private ProdutoDAO prodDAO;

    public Popular(FornecedorDAO fornDAO, CategoriaDAO catDAO, ProdutoDAO prodDAO){
        this.fornDAO = fornDAO;
        this.catDAO = catDAO;
        this.prodDAO = prodDAO;
    }

    public void popularFornecedor(){
        Fornecedor forn = new Fornecedor();
        forn.setNome("Carlos");
        forn.setId(0);
        fornDAO.add(forn);

        //-------------------------

        Fornecedor forn1 = new Fornecedor();
        forn1.setNome("Fabricio");
        forn1.setId(1);
        fornDAO.add(forn1);

        //-------------------------

        Fornecedor forn2 = new Fornecedor();
        forn2.setNome("Gustavo");
        forn2.setId(2);
        fornDAO.add(forn2);

        //-------------------------
    }

    public void popularCategoria(){

        Categoria cat = new Categoria();
        cat.setId(0);
        cat.setNome("Diversos");
        catDAO.add(cat);

        //-------------------------
        Categoria cat1 = new Categoria();
        cat1.setId(1);
        cat1.setNome("Eletrônicos");
        catDAO.add(cat1);

        //-------------------------

    }

    public void popularProduto(){
        Produto prod0 = new Produto();
        prod0.setId(0);
        prod0.setNome("Notebook");
        prod0.setQtd(20);
        prod0.setPreco(1800.50);
        prod0.setTipoUn("un");
        prod0.setEstoqueMin(5);
        prod0.setForn(fornDAO.read(0));
        prod0.setCat(catDAO.read(1));
        prodDAO.add(prod0);

        //-------------------------

        Produto prod1 = new Produto();
        prod1.setId(1);
        prod1.setNome("Maquina de Lavar Roupa");
        prod1.setQtd(20);
        prod1.setPreco(820.51);
        prod1.setTipoUn("un");
        prod1.setEstoqueMin(5);
        prod1.setForn(fornDAO.read(1));
        prod1.setCat(catDAO.read(1));
        prodDAO.add(prod1);

        //-------------------------

        Produto prod2 = new Produto();
        prod2.setId(2);
        prod2.setNome("Macbook com Linux");
        prod2.setQtd(20);
        prod2.setPreco(2729.50);
        prod2.setTipoUn("un");
        prod2.setEstoqueMin(5);
        prod2.setForn(fornDAO.read(2));
        prod2.setCat(catDAO.read(1));
        prodDAO.add(prod2);

        //-------------------------

        Produto prod3 = new Produto();
        prod3.setId(3);
        prod3.setNome("Chiclets");
        prod3.setQtd(20);
        prod3.setPreco(0.50);
        prod3.setTipoUn("un");
        prod3.setEstoqueMin(5);
        prod3.setForn(fornDAO.read(2));
        prod3.setCat(catDAO.read(0));
        prodDAO.add(prod3);

        //-------------------------

    }

}
