package DAO;

import connection.ConnectionFactory;
import models.Categoria;
import models.Fornecedor;
import models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void add(Categoria c)
    {

    }
    public void up(Categoria c)
    {

    }

    public void listAll() {

    }
    public void del(Categoria c){   // ou pelo id, public void del(int id)


    }

    public Categoria read(int id) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Categoria cat = new Categoria();

        try {
            stmt = con.prepareStatement("SELECT id, nome FROM categoria WHERE id = ? and deleted_at is NULL;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cat.setId(rs.getInt("id"));
                cat.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return cat;
        }
    }
}
