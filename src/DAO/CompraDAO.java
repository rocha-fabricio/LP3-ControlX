package DAO;

import connection.ConnectionFactory;
import models.Compra;
import models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompraDAO {

    ProdutoDAO pdao = new ProdutoDAO();
    UsuarioDAO udao = new UsuarioDAO();

    public void comprar(Compra c) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            stmt = con.prepareStatement("INSERT INTO compras (idUsuario, valor, dataCompra, dataEntrega) " +
                    "VALUES (?, ?, ?, ?);");
            stmt.setInt(1, c.getUsuario().getId());
            stmt.setDouble(2, c.getValor());

            String dcompra = dateFormat.format(c.getData());
            stmt.setDate(3, java.sql.Date.valueOf(dcompra));

            String dentrega = dateFormat.format(c.getDataEntrega());
            stmt.setDate(4, java.sql.Date.valueOf(dentrega));

            stmt.executeUpdate();

            for (Produto p : c.getProdutos()){
                stmt = con.prepareStatement("INSERT INTO produtos_compra (idCompra, idProduto, qtdProduto, precoUnProduto) " +
                        "VALUES (?, ?, ?, ?);");
                stmt.setInt(1, c.getId());
                stmt.setInt(2, p.getId());
                stmt.setDouble(3, p.getQtd());
                stmt.setDouble(4, p.getPreco());
                stmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public Compra read(Compra c) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Compra compra = new Compra();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            stmt = con.prepareStatement("SELECT * FROM compras WHERE id = ?");
            stmt.setInt(1, c.getId());
            rs = stmt.executeQuery();

            if (rs.next()) {
                compra.setId(rs.getInt("id"));
                compra.setUsuario(udao.read(rs.getInt("idUsuario")));
                compra.setValor(rs.getDouble("valor"));
                compra.setStatus(rs.getInt("status"));

                String dcompra = dateFormat.format(rs.getDate("dataCompra"));
                Date datac = new Date(dcompra);
                compra.setData(datac);

                String dentrega = dateFormat.format(rs.getDate("dataEntrega"));
                Date datae = new Date(dentrega);
                compra.setDataEntrega(datae);

                String dfinal = dateFormat.format(rs.getDate("dataFinal"));
                Date dataf = new Date(dfinal);
                compra.setDataFinal(dataf);
            }

            //Produtos da Compra

                stmt = con.prepareStatement("SELECT * FROM produtos_compra WHERE idCompra = ?");
                stmt.setInt(1, c.getId());
                rs = stmt.executeQuery();

                List <Produto> lista = new ArrayList<>();
                while (rs.next()) {
                    Produto p = pdao.read(rs.getInt("idProduto"));
                    p.setQtd(rs.getDouble("qtdProduto"));
                    p.setPreco(rs.getDouble("precoUnProduto"));
                    lista.add(p);
                }

                compra.setProdutos(lista);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return compra;
    }

    public List<Compra> listAll() throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Compra> lista = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        try {
            stmt = con.prepareStatement("SELECT * FROM compras");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setUsuario(udao.read(rs.getInt("idUsuario")));
                compra.setValor(rs.getDouble("valor"));
                compra.setStatus(rs.getInt("status"));

                String dcompra = dateFormat.format(rs.getDate("dataCompra"));
                Date datac = new Date(dcompra);
                compra.setData(datac);

                String dentrega = dateFormat.format(rs.getDate("dataEntrega"));
                Date datae = new Date(dentrega);
                compra.setDataEntrega(datae);

                String dfinal = dateFormat.format(rs.getDate("dataFinal"));
                Date dataf = new Date(dfinal);
                compra.setDataFinal(dataf);
                lista.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }

        return lista;

    }


    public void del(Compra c){

    }
}

