package DAO;

import models.Fornecedor;

import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    static List<Fornecedor> forns = new ArrayList<Fornecedor>();

    public void add(Fornecedor f) {
        forns.add(f);
    }

    public void up(Fornecedor f) {
        forns.set(f.getId(), f);
    }

    public List<Fornecedor> listAll() {
        for(Fornecedor f : forns){
            System.out.println("ID: " + f.getId() + " || Nome: " + f.getNome());
        }
        return forns;
    }

    public void del(Fornecedor f) {   // ou pelo id, public void del(int id)
        forns.remove(f.getId());
    }

    public Fornecedor select(int id) {
        Fornecedor forn = new Fornecedor();
        forn.setId(id);
        for (Fornecedor f : forns) {
            if (f.getId() == id) {
                forn = f;
                return forn;
            }
        }
        return forn;
    }
}
