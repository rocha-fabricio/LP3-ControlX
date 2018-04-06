package DAO;

import models.Compra;
import models.Produto;

import java.util.ArrayList;
import java.util.List;

public class ComprarDAO {
    static List<Compra> compras = new ArrayList<Compra>();

    public void add(Compra c){
        compras.add(c);
    }
    public void up(Compra c){

    }
    public List<Compra> listAll() {
        return compras;
    }
    public void del(Compra c){   // ou pelo id, public void del(int id)

    }
}
