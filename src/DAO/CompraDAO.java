package DAO;

import models.Compra;
import models.Produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompraDAO {
    static List<Compra> compras = new ArrayList<Compra>();

    public void comprar(Compra c) {
        double total = 0;
        for (Produto p : c.getProdutos()) {              //p é o Produto da Venda, com qtd de venda
            for (Produto prods : ProdutoDAO.prods)      //prods é o Produto do estoque, com a qtd do estoque
                if (p.getId() == prods.getId())
                    prods.setQtd(prods.getQtd() + p.getQtd());
            total += (p.getQtd() * p.getPreco());


        }
        Date data = new Date(System.currentTimeMillis());
        c.setData(data);
        c.setValor(total);
        compras.add(c);
    }

    public void read(Compra c) {

    }

    public void listAll() {
        for (Compra c : compras) {
            System.out.println(
                    "ID: " + c.getId() +
                            " || Valor: R$" + c.getValor() +
                            " || Data: " + c.getData() +
                            "\nProdutos:");
            for (Produto p : c.getProdutos())
                System.out.print(p.getNome() + "(" + p.getQtd() + " " + p.getTipoUn() + ") - ");
            System.out.println("\n------------------------------");
        }
    }
        public void del(Compra c){   // ou pelo id, public void del(int id)

        }
}

