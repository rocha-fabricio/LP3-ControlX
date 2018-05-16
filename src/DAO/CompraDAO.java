package DAO;

import models.Compra;
import models.Produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompraDAO {

    public void comprar(Compra c) throws ClassNotFoundException {
        double total = 0;
        ProdutoDAO prodDAO = new ProdutoDAO();
        List<Produto> prod = prodDAO.listAll();

        for (Produto p : c.getProdutos()) {              //p é o Produto da Venda, com qtd de venda
            for (Produto prods : prod)                   //prods é o Produto do estoque, com a qtd do estoque
                if (p.getId() == prods.getId())
                    prods.setQtd(prods.getQtd() + p.getQtd());
            total += (p.getQtd() * p.getPreco());


        }
        Date data = new Date(System.currentTimeMillis());
        c.setData(data);
        c.setValor(total);
        //compras.add(c);
    }

    public void read(Compra c) {

    }

    public void listAll() {

    }

    public void del(Compra c){

    }
}

