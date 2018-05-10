package DAO;

import connection.ConnectionFactory;
import models.Fornecedor;
import models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public void add(Fornecedor f) {

    }

    public void up(Fornecedor f) {

    }

    public void listAll() {

    }

    public void del(Fornecedor f) {   // ou pelo id, public void del(int id)

    }

    public Fornecedor read(int id) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Fornecedor forn = new Fornecedor();

        try {
            stmt = con.prepareStatement("SELECT id, nome, cnpj, tel1, tel2, cep, " +
                    "num, rua, comp, bairro, cidade, estado" +
                    " FROM fornecedor WHERE id = ? and deleted_at is NULL;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if(rs.next()) {
                forn.setId(rs.getInt("id"));
                forn.setNome(rs.getString("nome"));
                forn.setCnpj(rs.getString("cnpj"));
                forn.setTelefone1(rs.getString("tel1"));
                forn.setTelefone2(rs.getString("tel2"));
                forn.setCep(rs.getString("cep"));
                forn.setNum(rs.getInt("num"));
                forn.setRua(rs.getString("rua"));
                forn.setComp(rs.getString("comp"));
                forn.setBairro(rs.getString("bairro"));
                forn.setCidade(rs.getString("cidade"));
                forn.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return forn;
        }
    }
}