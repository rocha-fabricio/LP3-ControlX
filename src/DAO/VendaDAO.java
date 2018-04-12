package DAO;

import models.Produto;
import models.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    static List<Venda> vendas = new ArrayList<Venda>();
    //static List<Produto> prods = new ArrayList<Produto>();

    public void vender(Venda v){
        double total = 0;
        for(Produto p : v.getProdutos()) {
            total += (p.getPreco() * p.getQtd());
            ProdutoDAO prodDAO = new ProdutoDAO();
            /*for (Produto p : prods) {
                if(p.getId() == p2.getId()) {
                    p.setQtd(p.getQtd() - p2.getQtd());
                    break;
                }
            }*/
        }
        v.setValor(total);
        vendas.add(v);
    }

    public void up(Venda v){

    }
    public List<Venda> listAll() {
        return vendas;
    }
    public void del(Venda v){   // ou pelo id, public void del(int id)

    }
}
