package DAO;

import models.Compra;
import models.Produto;

import java.util.ArrayList;
import java.util.Date;
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
        Date data = new Date(System.currentTimeMillis());
        c.setDataCompra(data);
        c.setValor(total);
        compras.add(c);
    }
    public void read(Compra c){

    }
    public void listAll() {
        for(Compra c : compras){
            System.out.println("ID: " + c.getId() +
                    "|| Valor: " + c.getValor() +
                    "|| Data: " + c.getDataCompra());
        }
    }
    public void del(Compra c){   // ou pelo id, public void del(int id)

    }
}
