package models;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Compra {
    private int id;
    private String usuario;
    private double valor;
    private List<Produto> produtos = new ArrayList<Produto>();
    private int status;
    private Date dataCompra;   //LocalDateTime
    private Date dataEntrega;
    private Date dataFinal;

    //GET and SET
    //-------x----------------x--------
    public String getUsuario(){
        return this.usuario;
    }
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    //-------x----------------x--------
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    //-------x----------------x--------
    public double getValor(){
        return this.valor;
    }
    public void setValor(double valor){
        this.valor = valor;
    }
    //-------x----------------x--------
    public List<Produto> getProdutos(){
        return this.produtos;
    }
    public void setProdutos(List<Produto> produtos){
        this.produtos = produtos;
    }
    //-------x----------------x--------
    public Date getDataCompra(){
        return this.dataCompra;
    }
    public void setDataCompra(Date dataCompra){
        this.dataCompra = dataCompra;
    }
    //-------x----------------x--------
    public Date getDataEntrega(){
        return this.dataEntrega;
    }
    public void setDataEntrega(Date dataEntrega){
        this.dataEntrega = dataEntrega;
    }
    //-------x----------------x--------
    public Date getDataFinal(){
        return this.dataFinal;
    }
    public void setDataFinal(Date dataFinal){
        this.dataFinal = dataFinal;
    }
    //-------x----------------x--------
    //Construtores
    //-------x----------------x--------
    public Compra(int id, String usuario, double valor, List<Produto> produtos, int status, Date dataCompra, Date dataEntrega)
    {
        this.id = id;
        this.usuario = usuario;
        this.valor = valor;
        this.produtos = produtos;
        this.status = status;
        this.dataCompra = dataCompra;
        this.dataEntrega = dataEntrega;
    }
    //-------x----------------x--------
    public Compra(){

    }
}