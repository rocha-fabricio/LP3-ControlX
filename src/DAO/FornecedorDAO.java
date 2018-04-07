package DAO;

import models.Fornecedor;

import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    static List<Fornecedor> forns = new ArrayList<Fornecedor>();

    public void add(Fornecedor f){
        forns.add(f);
    }
    public void up(Fornecedor f){
        forns.set(f.getId(), f);
    }
    public List<Fornecedor> listAll() {

        return forns;
    }
    public void del(Fornecedor f){   // ou pelo id, public void del(int id)
        forns.remove(f.getId());
    }
}
