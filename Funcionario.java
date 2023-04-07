import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Funcionario extends Cargo{
  protected boolean status = true;
  protected int id_funcionario;
  protected String nome;
  protected Data contratacao;
  protected Cargo cargo;

  // === CONSTRUCTORS ===

  public Funcionario(){}

  public Funcionario(int id_funcionario, String nome, String def_cargo, int mes, int ano){    
    setIdFuncionario(id_funcionario);
    setNome(nome);
    setCargo(def_cargo);
    setContratacao(mes, ano);
  }

  // === GETTERS AND SETTERS

  //STATUS
  public void setStatus(boolean status){
    this.status = status;
  }

  public boolean getStatus(){
    return this.status;
  }
  

  //NOME
  public void setIdFuncionario(int id_funcionario){
    this.id_funcionario = id_funcionario;
  }

  public int getIdFuncionario(){
    return this.id_funcionario;
  }
  
  //NOME
  public void setNome(String nome){
    this.nome = nome;
  }

  public String getNome(){
    return nome;
  }

  //CONTRATACAO
  public void setContratacao(int mes, int ano){
    this.contratacao = new Data(mes, ano);
  }

  public int getMesContratacao(){
    return this.contratacao.getIntMes();
  }
  
  public int getAnoContratacao(){
    return this.contratacao.getAno();
  }

  
  //CARGO
  public void setCargo(String cargo){
    this.cargo = new Cargo(cargo);
  }

  public String getCargo(){
    return this.cargo.getCargo();
  }

  // Variáveis auxiliares para analise dos vetores
  static Funcionario func = new Funcionario();
  static Vendas func_vendas = new Vendas();

  // === MÉTODOS ===

 // Objetivo: Verificar se Funcionário ja estava contratado na data inserida 
  public static boolean analisaAno(Funcionario func, int mes, int ano){
    if(func.getAnoContratacao() < ano){
      return true;
    }
    else if(func.getAnoContratacao() == ano && func.getMesContratacao() <= mes){
      return true;
    }
    return false;
  }
  

  //Objetivo: Listar Todos os funcionarios cadastrados
  public static void listarFuncionarios(ArrayList<Funcionario> funcionarios){

    Scanner in = new Scanner(System.in);
    System.out.println("LISTAR FUNCIONÁRIOS");
    for(int i = 0; i < funcionarios.size(); i++){
      func = funcionarios.get(i);
      if(func.getStatus()){
        System.out.println("Nome: " + func.getNome());
        System.out.println("Cargo: " + func.getCargo());
        System.out.println("Data de contratacao: " + func.contratacao.getMes() + "/" + func.contratacao.getAno());
        System.out.println("---");
      }
    }

    System.out.println("\nDigite algo para continuar: ");
    in.next();
  }


  // Objetivo: Verifica se um determinado funcionario existe no sistema pelo seu nome
  public static boolean verificaFuncionario(ArrayList<Funcionario> funcionarios,String nome){
    for(int i = 0; i < funcionarios.size(); i++){
      func = funcionarios.get(i);
      if(func.getNome().equals(nome) && func.getStatus() == true){
        return true;
      }
    }
    return false;
  }

  // Objetivo: Inserir novo funcionário na lista de funcionarios
  public static void cadastrarFuncionario(ArrayList<Funcionario> funcionarios, int id){
    Scanner in = new Scanner(System.in);
    String nome;
    int cargo;
    String cargoDef = "";
    int mes;
    int ano;

    System.out.println("CADASTRAR FUNCIONÁRIO");
    System.out.println("Digite o nome do funcionário: ");
    nome = in.nextLine();

    while(verificaFuncionario(funcionarios, nome)){
      System.out.println("Funcionário já cadastrado, favor inserir outro nome: ");
      nome = in.nextLine();
    }

    System.out.println("Qual o cargo do funcionario:");
    System.out.println("{1} - Secretario\n{2} - Vendedor\n{3} - Gerente");
    cargo = in.nextInt();
    while(cargo < 1 || cargo > 3){
      System.out.println("Por favor digite um número entre 1 e 3");
      cargo = in.nextInt();
    }

    if(cargo == 1){
      cargoDef = "secretario";
    }
    else if(cargo == 2){
      cargoDef = "vendedor";
    }
    if(cargo == 3){
      cargoDef = "gerente";
    }

    System.out.println("Digite o mês de contratação: ");
    mes = in.nextInt();
    while(mes < 1 || mes > 12){
      System.out.println("Por favor digite um mes válido");
      mes = in.nextInt();
    }

    System.out.println("Digite o ano de contratação: ");
    ano = in.nextInt();
    while(ano < 1900 || ano > 2023){
      System.out.println("Por favor digite um ano válido");
      ano = in.nextInt();
    }

    funcionarios.add(new Funcionario(id, nome, cargoDef, mes, ano));

    System.out.println("Funcionário Cadastrado com sucesso!");
    System.out.println("\nDigite algo para continuar: ");
    in.next();    
  }

  // Objetivo: Desativar Funcionario de forma que seus dados não fiquem disponívies nas consultas 
  public static void desativarFuncionario(ArrayList<Funcionario> funcionarios){
    String nome;
    Scanner in = new Scanner(System.in);
    
    System.out.println("DESATIVAR FUNCIONÁRIO");
    System.out.println("Digite o nome do funcionário que você quer desativar: ");
    nome = in.nextLine();

    for(int i = 0; i < funcionarios.size(); i++){
      func = funcionarios.get(i);
      if(func.getNome().equals(nome)){
        func.setStatus(false);
        System.out.println("Funcionário desativado");
        System.out.println("\nDigite algo para continuar: ");
        in.next();
        break;   
      }
    }

    if(func.getStatus()){
      System.out.println("Funcionário não encontrado");
      System.out.println("\nDigite algo para continuar: ");
      in.next();
    } 
  }
}