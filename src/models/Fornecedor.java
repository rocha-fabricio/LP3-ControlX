package models;

public class Fornecedor {
    private String nome;
    private int id;
    private String cnpj;
    private String telefone1;
    private String telefone2;
    private long cep;
    private String num;
    private String rua;
    private String comp;
    private String bairro;
    private String cidade;
    private String estado;

    //GET and SET
    //-------x----------------x--------
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    //-------x----------------x--------
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    //-------x----------------x--------
    public String getCnpj(){
        return this.cnpj;
    }
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }
    //-------x----------------x--------
    public String getTelefone1(){
        return this.telefone1;
    }
    public void setTelefone1(String telefone1){
        this.telefone1 = telefone1;
    }
    //-------x----------------x--------
    public String getTelefone2(){
        return this.telefone2;
    }
    public void setTelefone2(String telefone2){
        this.telefone2 = telefone2;
    }
    //-------x----------------x--------
    public long getCep(){
        return this.cep;
    }
    public void setCep(long cep){
        this.cep = cep;
    }
    //-------x----------------x--------
    public String getNum(){
        return this.num;
    }
    public void setNum(String num){
        this.num = num;
    }
    //-------x----------------x--------
    public String getRua(){
        return this.rua;
    }
    public void setRua(String rua){
        this.rua = rua;
    }
    //-------x----------------x--------
    public String getComp(){
        return this.comp;
    }
    public void setComp(String comp){
        this.comp = comp;
    }
    //-------x----------------x--------
    public String getBairro(){
        return this.bairro;
    }
    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    //-------x----------------x--------
    public String getCidade(){
        return this.cidade;
    }
    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    //-------x----------------x--------
    public String getEstado(){
        return this.estado;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    //-------x----------------x--------
    //Construtores
    //-------x----------------x--------
    public Fornecedor(int id, String cpnj, String telefone1, String telefone2, long cep, String num, String rua, String comp, String bairro, String cidade, String estado, String nome)
    {
        this.id = id;
        this.cnpj = cpnj;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.cep = cep;
        this.rua = rua;
        this.comp = comp;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.nome = nome;
        this.num = num;
    }
    public Fornecedor(){

    }
}
