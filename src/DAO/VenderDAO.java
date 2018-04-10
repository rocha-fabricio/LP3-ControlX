package DAO;

import models.Produto;
import models.Venda;

import java.util.ArrayList;
import java.util.List;

public class VenderDAO {
    static List<Venda> vendas = new ArrayList<Venda>();
    static List<Produto> produtos = new ArrayList<Produto>();

    public void vender(Venda v){
        vendas.add(v);

        for(Produto p2 : v.getProdutos()) {
            for (Produto p : produtos) {
                if(p.getId() == p2.getId()) {
                    p.setQtd(p.getQtd() - p2.getQtd());
                    break;
                }
            }
        }

    }
    public void up(Venda v){

    }
    public List<Venda> listAll() {
        return vendas;
    }
    public void del(Venda v){   // ou pelo id, public void del(int id)

    }
}
