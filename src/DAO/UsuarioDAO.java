package DAO;

import models.Produto;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    static List<Usuario> users = new ArrayList<Usuario>();

    public void add(Usuario u){

        users.add(u);
    }
    public void up(Usuario u){
        users.set(u.getId(), u);
    }
    public List<Usuario> listAll() {
        return users;
    }
    public void del(Usuario u){   // ou pelo id, public void del(int id)
        users.remove(u.getId());
    }
}
