package DAO;

import models.Categoria;
import models.Fornecedor;
import models.Produto;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    static List<Categoria> cats = new ArrayList<Categoria>();

    public void add(Categoria c){
        cats.add(c);
    }
    public void up(Categoria c){
        cats.set(c.getId(), c);
    }
    public List<Categoria> listAll() {
        for(Categoria c : cats){
            System.out.println("ID: " + c.getId() + " || Nome: " + c.getNome());
        }
        return cats;
    }
    public void del(Categoria c){   // ou pelo id, public void del(int id)
        cats.remove(c.getId());
    }

    public Categoria select(int id) {
        Categoria cat = new Categoria();
        cat.setId(id);
        for (Categoria c : cats) {
            if (c.getId() == id) {
                cat = c;
                return cat;
            }
        }
        return cat;
    }
}
