package DAO;

import models.Categoria;
import models.Fornecedor;
import models.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    static List<Produto> prods = new ArrayList<Produto>();

    public void add(Produto p) {
        prods.add(p);
    }

    public void up(Produto p) {
        prods.set(p.getId(), p);
    }

    public List<Produto> listAll() {
        for (Produto p : prods) {
            Fornecedor forn = p.getForn();
            Categoria cat = p.getCat();
            System.out.println("ID: " + p.getId() + " || Nome: " + p.getNome() + " || R$" + p.getPreco() + " || " + p.getQtd() + " " + p.getTipoUn() + " || Estoque Min: " + p.getEstoqueMin() + " || Fornecedor: " + forn.getNome() + " || Categoria: " + cat.getNome());
        }
        return prods;
    }

    public void del(Produto p) {   // ou pelo id, public void del(int id)
        prods.remove(p.getId());
    }

    public Produto read(Produto p) {
        for (Produto prod : prods) {
            if (prod.getId() == p.getId()) {
                p = prod;
                return p;
            }
        }
        return p;
    }
}