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

    public void up(Produto pr) {
        //prods.set(p.getId(), p);
        for(Produto p : prods){
            if(p.getId() == pr.getId()){
                p.setQtd(pr.getQtd());
                p.setEstoqueMin(pr.getEstoqueMin());
                p.setForn(pr.getForn());
                p.setTipoUn(pr.getTipoUn());
                p.setCat(pr.getCat());
                p.setNome(pr.getNome());
                p.setPreco(pr.getPreco());
            }
        }
        System.out.println("Produto atualizado com sucesso.");
    }

    public void listAll() {
        if(prods.size() > 0)
            for (Produto p : prods) {
                Fornecedor forn = p.getForn();
                Categoria cat = p.getCat();
                System.out.println("ID: " + p.getId() + " || Nome: " + p.getNome() + " || R$" + p.getPreco() + " || " + p.getQtd() + " " + p.getTipoUn() + " || Estoque Min: " + p.getEstoqueMin() + " || Fornecedor: " + forn.getNome() + " || Categoria: " + cat.getNome());
            }
        else
            System.out.println("Nenhum Produto cadastrado.");
    }

    public void del(Produto p) {   // ou pelo id, public void del(int id)
        prods.remove(p.getId());
        System.out.println("Produto removido com sucesso.");
    }

    public Produto read(Produto p) {
        for (Produto prod : prods) {
            if (prod.getId() == p.getId()) {
                p = prod;
                return p;
            }
        }
        return null;
    }

    public Produto read(int id) {
        for (Produto p : prods) {
            if (p.getId() == id) {
                Produto prod = new Produto();
                prod.setForn(p.getForn());
                prod.setNome(p.getNome());
                prod.setId(p.getId());
                prod.setPreco(p.getPreco());
                prod.setQtd(p.getQtd());
                prod.setTipoUn(p.getTipoUn());
                prod.setEstoqueMin(p.getEstoqueMin());
                return prod;
            }
        }
        return null;
    }
}