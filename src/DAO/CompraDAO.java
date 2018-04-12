package DAO;

import models.Compra;
import models.Produto;

import java.util.ArrayList;
import java.util.List;

public class CompraDAO {
    static List<Compra> compras = new ArrayList<Compra>();
    static List<Produto> prods = new ArrayList<Produto>();

    public void comprar(Compra c){
        double total = 0;
        for(Produto p2 : c.getProdutos()) {
            total += p2.getPreco();
            for (Produto p : prods) {
                if(p.getId() == p2.getId()) {
                    p.setQtd(p.getQtd() + p2.getQtd());
                    break;
                }
            }
        }
        c.setValor(total);
        compras.add(c);
    }
    public void read(Compra c){

    }
    public List<Compra> listAll() {

        return compras;
    }
    public void del(Compra c){   // ou pelo id, public void del(int id)

    }
}
