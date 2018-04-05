package DAO;

import models.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    static List<Produto> prods = new ArrayList<Produto>();

    public void add(Produto p){
        prods.add(p);
    }
    public void up(Produto p){

    }
     public List<Produto> listAll() { ;
         return prods;
    }
    public void del(Produto p){   // ou pelo id, public void del(int id)

    }
}
