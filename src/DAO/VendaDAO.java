package DAO;

import models.Produto;
import models.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    static List<Venda> vendas = new ArrayList<Venda>();

    public void vender(Venda v){
        double total = 0;
        ProdutoDAO pDao = new ProdutoDAO();
        for(Produto p : v.getProdutos()){
            for(Produto prods:ProdutoDAO.prods)
                if(p.getId() == prods.getId())
                    prods.setQtd(prods.getQtd() - p.getQtd());
            // Produto prod = new Produto();
            //Produto prod2 = pDao.read(p.getId());
            total += p.getPreco();
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
