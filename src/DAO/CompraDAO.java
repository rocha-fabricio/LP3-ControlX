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

    public void comprar(Compra c) throws ClassNotFoundException {
        double total = 0;
        ProdutoDAO prodDAO = new ProdutoDAO();
        List<Produto> prod = prodDAO.listAll();

        for (Produto p : c.getProdutos()) {              //p é o Produto da Venda, com qtd de venda
            for (Produto prods : prod)                   //prods é o Produto do estoque, com a qtd do estoque
                if (p.getId() == prods.getId())
                    prods.setQtd(prods.getQtd() + p.getQtd());
            total += (p.getQtd() * p.getPreco());


        }
        Date data = new Date(System.currentTimeMillis());
        c.setData(data);
        c.setValor(total);
        //compras.add(c);
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
                compra.setUsuario(rs.getString("nome_usuario"));
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
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        try {
            stmt = con.prepareStatement("SELECT * FROM compras");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setUsuario(rs.getString("nome_usuario"));
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

