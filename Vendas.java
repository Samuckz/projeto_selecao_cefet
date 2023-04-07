import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Vendas extends Data{
  private int id_funcionario;
  private double valorVenda;
  protected Data dataVenda;

  // === CONSTRUCTORS ===
  public Vendas(){}

  public Vendas(int id_funcionario, double valorVenda, int mes, int ano){
    setIdFuncionario(id_funcionario);
    setValorVenda(valorVenda);
    setDataVenda(mes, ano);
  }

  // === GETTERS AND SETTERS ===
  
  //ID_FUNCIONARIO
  public void setIdFuncionario(int id_funcionario){
    this.id_funcionario = id_funcionario;
  }

  public int getIdFuncionario(){
    return this.id_funcionario;
  }

  //VALOR VENDA
  public void setValorVenda(double valorVenda){
    this.valorVenda = valorVenda;
  }

  public double getValorVenda(){
    return this.valorVenda;
  }

  //DATA VENDA
  public void setDataVenda(int mes, int ano){
    this.dataVenda = new Data(mes, ano);
  }

  public int getMesVenda(){
    return this.dataVenda.getIntMes();
  }

  public int getAnoVenda(){
    return this.dataVenda.getAno();
  }

  // === MÉTODOS ===

  // Variáveis auxiliares para manipulação dos Arrays
  static Funcionario func = new Funcionario();
  static Vendas venda = new Vendas();

  
  // Objetivo: Verificar se existe um funcionário que é vendedor com determinado nome
  public static String findVendedor(int id, ArrayList<Funcionario> funcionarios){
    String findName = null;
    for(int i = 0; i < funcionarios.size(); i++){
      func = funcionarios.get(i);
      if(id == func.getIdFuncionario() && func.getStatus()){
        findName = func.getNome();
      }
    }
    return findName;
  }

  // Objetivo: Mostrar todas as vendas já registradas
  public static void listarVendas(ArrayList<Vendas> vendas, ArrayList<Funcionario> funcionarios){
    Scanner in = new Scanner(System.in);

    System.out.println("LISTAR VENDAS");
    OtherFunctions.line();
    
    for(int i = 0; i< vendas.size(); i++){
      venda = vendas.get(i);
      if(findVendedor(venda.getIdFuncionario(), funcionarios) == null){
        continue;
      }
      System.out.println("Vendedor: " + findVendedor(venda.getIdFuncionario(), funcionarios));
      System.out.println("Data de venda: " + venda.dataVenda.getMes() + "/" + venda.dataVenda.getAno());
      System.out.println("Valor da venda: R$" + venda.getValorVenda());
      System.out.println("---");
      
    }
    System.out.println("\nDigite algo para continuar: ");
    in.next(); 
  }

  // Objetivo: Verirficar se funcionário é vendedor
  public static int verificaVendedor(String nome, ArrayList<Funcionario> funcionarios){
    for(int i = 0; i < funcionarios.size(); i++){
      func = funcionarios.get(i);
      if(func.getNome().equals(nome) && func.getCargo().equals("Vendedor")){
        return func.getIdFuncionario();
      }
    }
    return 0;
  }

  // Objetivo: Listar todos os vendedores presentes na lista de funcionários
  public static void listaVendedores(ArrayList<Funcionario> funcionarios){
    System.out.println("Lista de vendedores:");
    for(int i = 0; i<funcionarios.size(); i++){
      func = funcionarios.get(i);
      if(func.getStatus() && func.getCargo() == "Vendedor"){
        System.out.println("Nome: " + func.getNome() + "\n---");
      }
    }
  }

  // Objetivo: Cadastrar nova venda na lista de vendas
  public static void cadastrarVenda(ArrayList<Vendas> vendas, ArrayList<Funcionario> funcionarios){
    Scanner in = new Scanner(System.in);
    String nome;
    double valor;
    int mesVenda;
    int anoVenda;

    System.out.println("CADASTRAR VENDA\n---");
    listaVendedores(funcionarios);
    OtherFunctions.line();
    
    System.out.println("Digite o nome do vendedor que realizou as vendas: ");
    nome = in.nextLine();

    while(verificaVendedor(nome, funcionarios) == 0){
      System.out.println("Esse funcionário não foi cadastrado no sistema ou ele não é vendedor!\nFavor Digitar um nome válido");
      nome = in.nextLine();
    }

    System.out.println("Digite a quantia vendida no mês: ");
    valor = in.nextDouble();

    while(valor < 0){
      System.out.println("Digite um valor válido: ");
      valor = in.nextDouble();
    }

    System.out.println("Digite o ano da venda: ");
    anoVenda = in.nextInt();
    
    int id_func = verificaVendedor(nome, funcionarios);
    
    while(anoVenda < funcionarios.get(id_func-1).getAnoContratacao()){
      System.out.println("Este vendedor não era contratado neste ano.\nFavor inserir um ano válido");
      anoVenda = in.nextInt();
    }

    System.out.println("Digite o mês da venda: ");
    mesVenda = in.nextInt();

    while(mesVenda < 1 || mesVenda > 12 ||(funcionarios.get(id_func-1).getAnoContratacao() == anoVenda && mesVenda < funcionarios.get(id_func-1).getMesContratacao())){
      if(funcionarios.get(id_func-1).getAnoContratacao() == anoVenda && mesVenda < funcionarios.get(id_func-1).getMesContratacao()){
        System.out.println("O funcionário não estava cadastrado nesta data");
      } else{
        System.out.println("Por favor digite um mes válido");
      }
      mesVenda = in.nextInt();
    }

    //Somar valor de vendas ja cadastradas num mes
    boolean saleAlreadyDone = false;
    for(int j = 0; j<vendas.size(); j++){
      venda = vendas.get(j);
      if(venda.getIdFuncionario() == id_func && venda.getMesVenda() == mesVenda && venda.getAnoVenda() == anoVenda){
        venda.setValorVenda(venda.getValorVenda() + valor);
        saleAlreadyDone = true;
      }
    }

    if(!saleAlreadyDone){
      vendas.add(new Vendas(verificaVendedor(nome, funcionarios), valor, mesVenda, anoVenda));
    }
    

    System.out.println("Venda Cadastrada com sucesso!");
    System.out.println("\nDigite algo para continuar: ");
    in.next();     
  }
}
