package DAO;

import connection.ConnectionFactory;
import models.Categoria;
import models.Fornecedor;
import models.Produto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    static List<Produto> prods = new ArrayList<Produto>();

    public void add(Produto p) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO produtos (nome, preco, qtd, tipoUn, estoqueMin, idFornecedor, idCategoria) " +
                    "VALUES (?, ?, ?, ?, ?);");
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setDouble(3, p.getQtd());
            stmt.setString(4, p.getTipoUn());
            stmt.setDouble(5, p.getEstoqueMin());
            stmt.setInt(6, p.getForn().getId());
            stmt.setInt(7, p.getCat().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void up(Produto p) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("UPDATE produtos SET nome = ?, preco = ?, qtd = ?, " +
                    "tipoUn = ?, estoqueMin = ?, idFornecedor = ?, idCategoria = ? WHERE id = ?;");
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setDouble(3, p.getQtd());
            stmt.setString(4, p.getTipoUn());
            stmt.setDouble(5, p.getEstoqueMin());
            stmt.setInt(6, p.getForn().getId());
            stmt.setInt(7, p.getCat().getId());
            stmt.setInt(8, p.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Produto> listAll() throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = con.prepareStatement("SELECT * FROM produtos;");
            stmt.executeQuery();

            while (rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("qtd"));
                prod.setTipoUn(rs.getString("tipoUn"));
                prod.setEstoqueMin(rs.getDouble("estoqueMin"));

                /*Fornecedor forn = new Fornecedor();
                forn.setId(rs.getInt("idFornecedor"));
                prod.setForn(forn);

                prod.setCat();*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void del(Produto p) {   // ou pelo id, public void del(int id)

    }

    public Produto read(Produto p) {

        return null;
    }

    public Produto read(int id) {
        Produto prod = new Produto();

        return prod;
    }
}