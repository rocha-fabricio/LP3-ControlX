package DAO;

import models.Produto;
import models.Venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaDAO {
    static List<Venda> vendas = new ArrayList<Venda>();

    public Venda read(int v){
        Venda venda = new Venda();
        venda.setId(v);
        for(Venda x : vendas)
        {
            if(x.getId() == v)
            {
                venda.setData(x.getData());
                venda.setProdutos(x.getProdutos());
                venda.setValor(x.getValor());
                venda.setUsuario(x.getUsuario());
                return venda;
            }
            else
                return null;
        }
        return venda;
    }

    public void vender(Venda v) throws ClassNotFoundException {
        double total = 0;
        ProdutoDAO prodDAO = new ProdutoDAO();
        List<Produto> prod = prodDAO.listAll();

        for(Produto p : v.getProdutos()){           //p é o Produto da Venda, com qtd de venda
            for(Produto prods : prod)     //prods é o Produto do estoque, com a qtd do estoque
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
        if(vendas.size() > 0)
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
        else
            System.out.println("Nenhuma venda feita.");
    }
    public void del(Venda v){   // ou pelo id, public void del(int id)

    }
}
