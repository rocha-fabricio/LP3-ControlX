package DAO;

import models.Produto;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    static List<Usuario> usuarios = new ArrayList<Usuario>();

    public void add(Usuario u){
        usuarios.add(u);
    }
    public void up(Usuario u){

    }
    public List<Usuario> listAll() {
        return usuarios;
    }
    public void del(Usuario u){   // ou pelo id, public void del(int id)

    }
}
