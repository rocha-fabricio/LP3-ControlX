# LP3-ControlX Java

Carlos Silva - Fabricio Rocha - Gustavo Nunes

ControlX  ->  Sistema de controle de Estoque, Compra e Venda
  ('Control' de Controle e 'X' como uma incógnita, uma variável, mostrando de que esse Software não tem uso especifico, é genérico na área)

//
### JAVA JDK 10 - Instalação Linux:
      - Executar:**
      sudo add-apt-repository ppa:linuxuprising/java
      sudo apt-get update
      sudo apt-get install oracle-java10-installer
    
      - Verificando Versão:
      $ /usr/lib/jvm/java-10-oracle/bin/java -version
      
      --
      java version "10.0.1" 2018-04-17
      Java(TM) SE Runtime Environment 18.3 (build 10.0.1+10)
      Java HotSpot(TM) 64-Bit Server VM 18.3 (build 10.0.1+10, mixed mode)
      --
    
      - Setando como DEFAULT:
      sudo apt-get install oracle-java10-set-default
 
 //

### - classe PRODUTO

    String nome;
    int id;
    double preco;
    double qtd;
    String tipoUn;
    double estoqueMin;
    
//   

### - classe CATEGORIA  

    String nome;
    int id;
    List<Produto> produtos = new ArrayList<Produto>();
  
//

### - classe FORNECEDOR

    String nome;
    int id;
    String cnpj;
    String telefone1;
    String telefone2;
    long cep;
    String num;
    String rua;
    String comp;
    String bairro;
    String cidade;
    String estado;
    
//    

### - classe COMPRA
 
    int id;
    String usuario;
    double valor;
    List<Produto> produtos = new ArrayList<Produto>();
    int status;
    Date dataCompra;   //LocalDateTime
    Date dataEntrega;
    Date dataFinal;
  
 //
 
### - classe VENDA
 
    int id;
    String usuario;
    double valor;
    List<Produto> produtos = new ArrayList<Produto>();
    Date data;
  
 //
 
### - classe USUARIO
**CRIAR CLASSES DOS CARGOS HERDANDO A CLASSE USUARIO**
 
    int id;
    String nome;
    long cpf;
    char sexo;
    Date dataNasc;
    String telefone1;
    String telefone2;
    long cep;
    String num;
    String rua;
    String comp;
    String bairro;
    String cidade;
    String estado;
    String login;
    String senha;
    
  //
