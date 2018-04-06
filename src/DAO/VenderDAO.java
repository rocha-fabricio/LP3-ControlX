package DAO;

import models.Venda;

import java.util.ArrayList;
import java.util.List;

public class VenderDAO {
    static List<Venda> vendas = new ArrayList<Venda>();

    public void add(Venda v){
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
