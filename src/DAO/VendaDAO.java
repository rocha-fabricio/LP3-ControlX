package DAO;

import connection.ConnectionFactory;
import models.Produto;
import models.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.DateFormat;
import java.util.List;

public class VendaDAO {
    static List<Venda> vendas = new ArrayList<Venda>();

    public Venda read(int v){
        Venda venda = new Venda();
        venda.setId(v);
        for(Venda x : vendas)
        {
            if(x.getId() == v)
            {
                venda.setData(x.getData());
                venda.setProdutos(x.getProdutos());
                venda.setValor(x.getValor());
                venda.setUsuario(x.getUsuario());
                return venda;
            }
            else
                return null;
        }
        return venda;
    }

    public boolean vender(Venda v) throws ClassNotFoundException {

        boolean sucess = false;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {

            stmt = con.prepareStatement("INSERT INTO vendas (idUsuario, valor, dataVenda) " +
                    "VALUES (?, ?, ?);");
            stmt.setInt(1, v.getUsuario().getId());
            stmt.setDouble(2, v.getValor());
            String data = dateFormat.format(v.getData());
            stmt.setString(3, data);
            stmt.executeUpdate();

            for (Produto p: v.getProdutos()) {

                PreparedStatement st = null;
                Connection conn = ConnectionFactory.getConnection();

                String preco = String.valueOf(p.getPreco());
                if (preco.contains(","))
                    preco = preco.replace(",", ".");

                String quantidade = String.valueOf(p.getQtd());
                if (quantidade.contains(","))
                    quantidade = quantidade.replace(",", ".");

                st = conn.prepareStatement("INSERT INTO produtos_venda (idVenda, idProduto, qtdProduto, precoUnProduto) " +
                        "VALUES (?, ?, ?, ?);");
                st.setInt(1, getIdVenda());
                st.setInt(2, p.getId());
                st.setString(3, quantidade);
                st.setString(4, preco);

                st.executeUpdate();
                ConnectionFactory.closeConnection(conn, st);
            }
            sucess = true;
        } catch (SQLException e) {
            e.printStackTrace();
            sucess = false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return sucess;

    }

    public void addProdVenda(Produto p, int idVenda) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement st = null;

        try {
            String preco = String.valueOf(p.getPreco());
            if (preco.contains(","))
                preco = preco.replace(",", ".");

            String quantidade = String.valueOf(p.getQtd());
            if (quantidade.contains(","))
                quantidade = quantidade.replace(",", ".");

            st = con.prepareStatement("INSERT INTO produtos_venda (idVenda, idProduto, qtdProduto, precoUnProduto) " +
                        "VALUES (?, ?, ?, ?);");
                st.setInt(1, idVenda);
                st.setInt(2, p.getId());
                st.setString(3, quantidade);
                st.setString(4, preco);

               st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, st);
        }
    }

    public int getIdVenda() throws ClassNotFoundException, SQLException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'controlx' AND TABLE_NAME = 'vendas'");
        rs = stmt.executeQuery();
        if (rs.next()) {
            int id = (rs.getInt("AUTO_INCREMENT"));
            return (id - 1);
        }
        else
            return 9999;

    }

    public void up(Venda v){

    }
    public void listAll() {
        if(vendas.size() > 0)
            for(Venda v : vendas){
                System.out.println(
                        "ID: " + v.getId() +
                                " || Valor: R$" + v.getValor() +
                                " || Data: " + v.getData() +
                                "\nProdutos:");
                for(Produto p : v.getProdutos())
                    System.out.print(p.getNome() + "(" + p.getQtd() +" " + p.getTipoUn() + ") - ");
                System.out.println("\n------------------------------");

            }
        else
            System.out.println("Nenhuma venda feita.");
    }
    public void del(Venda v){   // ou pelo id, public void del(int id)

    }
}
