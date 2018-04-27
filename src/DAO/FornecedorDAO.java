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
        System.out.print("Atualizando dados.");
        System.out.print(" .");
        System.out.println(" .");
        forns.set(f.getId(), f);
        System.out.println("Fornecedor Atualizado com sucesso");
    }

    public void listAll() {
        if(forns.size() > 0)
            for(Fornecedor f : forns){
                System.out.println("ID: " + f.getId() + " || Fornecedor: " + f.getNome());
            }
        else
            System.out.println("Nenhum Fornecedor encontrado");
    }

    public void del(Fornecedor f) {   // ou pelo id, public void del(int id)
        forns.remove(f.getId());
        System.out.println("Fornecedor removido com sucesso.");
    }


    public Fornecedor read(int id) {
        Fornecedor forn = new Fornecedor();
        forn.setId(id);
        for (Fornecedor f : forns) {
            if (f.getId() == id) {
                forn.setNome(f.getNome());
                forn.setId(f.getId());
                forn.setCnpj(f.getCnpj());
                forn.setTelefone1(f.getTelefone1());
                forn.setTelefone2(f.getTelefone2());
                forn.setCep(f.getCep());
                forn.setNum(f.getNum());
                forn.setRua(f.getRua());
                forn.setComp(f.getComp());
                forn.setBairro(f.getBairro());
                forn.setCidade(f.getCidade());
                forn.setEstado(f.getEstado());

                return forn;
            }
        }
        return forn;
    }
}
