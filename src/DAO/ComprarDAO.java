package DAO;

import models.Compra;
import models.Produto;

import java.util.ArrayList;
import java.util.List;

public class ComprarDAO {
    static List<Compra> compras = new ArrayList<Compra>();
    static List<Produto> produtos = new ArrayList<Produto>();

    public void comprar(Compra c){
        compras.add(c);

        for(Produto p2 : c.getProdutos()) {
            for (Produto p : produtos) {
                if(p.getId() == p2.getId()) {
                    p.setQtd(p.getQtd() + p2.getQtd());
                    break;
                }
            }
        }
    }
    public void read(Compra c){

    }
    public List<Compra> listAll() {

        return compras;
    }
    public void del(Compra c){   // ou pelo id, public void del(int id)

    }
}
