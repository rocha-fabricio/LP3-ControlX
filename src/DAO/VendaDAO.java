package DAO;

import models.Produto;
import models.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    static List<Venda> vendas = new ArrayList<Venda>();

    public void vender(Venda v){
        double total = 0;
        ProdutoDAO dao = new ProdutoDAO();
        for(Produto p : v.getProdutos()){
            Produto prod = new Produto();
            Produto prod2 = dao.read(p.getId());
            prod.setId(prod2.getId());
            prod.setQtd(prod2.getQtd());
            prod.setCat(prod2.getCat());
            prod.setForn(prod2.getForn());
            prod.setEstoqueMin(prod2.getEstoqueMin());
            prod.setNome(prod2.getNome());
            prod.setPreco(prod2.getPreco());
            prod.setTipoUn(prod2.getTipoUn());
            dao.up(prod);
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
