package DAO;

import models.Categoria;
import models.Produto;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    static List<Categoria> cats = new ArrayList<Categoria>();

    public void add(Categoria c){
        cats.add(c);
    }
    public void up(Produto c){

    }
    public List<Categoria> listAll() {
        return cats;
    }
    public void del(Categoria c){   // ou pelo id, public void del(int id)

    }
}
