package database;

import DAO.CategoriaDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;

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

    }

    public void popularCategoria(){

    }

    public void popularProduto(){

    }

}
