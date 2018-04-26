package DAO;

import models.Produto;
import models.Venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaDAO {
    static List<Venda> vendas = new ArrayList<Venda>();

    public void vender(Venda v){
        double total = 0;
        for(Produto p : v.getProdutos()){           //p é o Produto da Venda, com qtd de venda
            for(Produto prods:ProdutoDAO.prods)     //prods é o Produto do estoque, com a qtd do estoque
                if(p.getId() == prods.getId())
                    prods.setQtd(prods.getQtd() - p.getQtd());
            total += (p.getPreco() * p.getQtd());
        }

        Date data = new Date(System.currentTimeMillis());
        v.setData(data);
        v.setValor(total);
        vendas.add(v);
    }

    public void up(Venda v){

    }
    public void listAll() {
        for(Venda v : vendas){
            System.out.println(
                    "ID: " + v.getId() +
                    " || Valor: R$" + v.getValor() +
                    " || Data: " + v.getData() +
                   "\nProdutos:");
            for(Produto p : v.getProdutos())
                System.out.print(p.getNome() + "(" + p.getQtd() +" " + p.getTipoUn() + ") - ");
            System.out.println("\n------------------------------");

        }
    }
    public void del(Venda v){   // ou pelo id, public void del(int id)

    }
}
