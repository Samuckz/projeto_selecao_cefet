import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

class Main {
  public static void main(String[] args) {
    Funcionario func = new Funcionario();
    int id = 1;

    // Variáveis navegação Menu
    int choose = -1;
    int choose2 = -1;

    Scanner in = new Scanner(System.in);

    // === CRIANDO ARRAY DE FUNCIONÁRIOS ===
    // Funcionario(int id_funcionario, String nome, String def_cargo, int mes, int
    // ano)
    ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
    funcionarios.add(new Funcionario(id, "Jorge Carvalho", "secretario", 1, 2018));
    id++;
    funcionarios.add(new Funcionario(id, "Maria Souza", "secretario", 12, 2015));
    id++;
    funcionarios.add(new Funcionario(id, "Ana Silva", "vendedor", 12, 2021));
    id++;
    funcionarios.add(new Funcionario(id, "João Mendes", "vendedor", 12, 2021));
    id++;
    funcionarios.add(new Funcionario(id, "Juliana Alves", "gerente", 7, 2017));
    id++;
    funcionarios.add(new Funcionario(id, "Bento Albino", "gerente", 3, 2014));
    id++;

    // === CRIANDO ARRAY DE VENDAS ===
    // Vendas(int id_funcionario, double valorVenda, int mes, int ano)
    ArrayList<Vendas> vendas = new ArrayList<Vendas>();
    // Vendas Ana Silva
    vendas.add(new Vendas(3, 5200, 12, 2021));
    vendas.add(new Vendas(3, 4000, 1, 2022));
    vendas.add(new Vendas(3, 4200, 2, 2022));
    vendas.add(new Vendas(3, 5850, 3, 2022));
    vendas.add(new Vendas(3, 7000, 4, 2022));

    // Vendas João Mendes
    vendas.add(new Vendas(4, 3400, 12, 2021));
    vendas.add(new Vendas(4, 7700, 1, 2022));
    vendas.add(new Vendas(4, 5000, 2, 2022));
    vendas.add(new Vendas(4, 5900, 3, 2022));
    vendas.add(new Vendas(4, 6500, 4, 2022));

    // === MENU APLICAÇÃO ===
    while (choose != 0) {
      OtherFunctions.clear();
      System.out.println("SISTEMA DE GESTÃO DE FUNCIONÁRIOS");
      System.out.println("O que você deseja fazer?");
      System.out.println("{1} - Verificar métodos pedidos no desafio\n{2} - Setor de Funcionários\n{3} - Setor de Vendas\n{0} - Sair");
      System.out.println("O que você deseja fazer?: ");
      choose = in.nextInt();
      while (choose < 0 || choose > 3) {
        System.out.println("Por favor Digite um valor entre 0 e 3 ");
        choose = in.nextInt();
      }

      switch (choose) {
        // === MÉTODOS EXIXGIDOS NO DESAFIO ===
        case 1:
          int mes;
          int ano;

          OtherFunctions.clear();

          System.out.println("Digite o mês que você quer consultar: ");
          mes = in.nextInt();
          in.nextLine();
          System.out.println();
          while(mes < 1 || mes > 12){
            System.out.println("Digite um mês válido (1 a 12): ");
            mes = in.nextInt();
          }
          
          System.out.println("Digite o ano que você quer consultar: ");
          ano = in.nextInt();
          in.nextLine();
          System.out.println();
          while(ano < 2014 || ano > 2023){
            System.out.println("Digite um ano válido (2000 a 2023): ");
            ano = in.nextInt();
          }

          OtherFunctions.clear();

          Metodos.totalPago(funcionarios, vendas, mes, ano);
          System.out.println("---");
          Metodos.salarioPago(funcionarios, mes, ano);
          System.out.println("---");
          Metodos.valorTotalBeneficios(funcionarios, vendas, mes, ano);
          System.out.println("---");
          Metodos.maiorValorRecebidoMes(funcionarios, vendas, mes, ano);
          System.out.println("---");
          Metodos.maiorValorBeneficios(funcionarios, vendas, mes, ano);
          System.out.println("---");
          Metodos.vendeuMais(funcionarios, vendas, mes, ano);
          System.out.println("---");
          System.out.println("\nDigite algo para continuar");
          in.next();
          break;

        // === SETOR DE FUNCIONÁRIOS ===
        case 2:
          choose2 = -1;
          while (choose2 != 0) {
            OtherFunctions.clear();
            System.out.println("SETOR FUNCIONÁRIOS");
            System.out.println("O que você deseja fazer?");
            System.out.println("{1} - Listar Funcionários\n{2} - Adicionar Funcionários\n{3} - Desativar Funcionário\n{0} - Sair");
            System.out.println("O que você deseja fazer?: ");
            choose2 = in.nextInt();
            while (choose2 < 0 || choose > 3) {
              System.out.println("Por favor Digite um valor entre 0 e 3 ");
              choose2 = in.nextInt();
            }

            switch (choose2) {
              // === LISTAR FUNCIONÁRIOS ===
              case 1:
                OtherFunctions.clear();
                Funcionario.listarFuncionarios(funcionarios);
                break;

              // === CADASTRAR FUNCIONÁRIO ===
              case 2:
                OtherFunctions.clear();
                Funcionario.cadastrarFuncionario(funcionarios, id);
                id++;
                break;

              // === DESATIVAR FUNCIONÁRIO ===
              case 3:
                OtherFunctions.clear();
                Funcionario.desativarFuncionario(funcionarios);
                break;
            }

          }

          break;

        // === SETOR DE VENDAS ===
        case 3:
          choose2 = -1;
          while (choose2 != 0) {
            OtherFunctions.clear();
            System.out.println("SETOR DE VENDAS");
            System.out.println("O que você deseja fazer?");
            System.out.println("{1} - Listar Vendas\n{2} - Cadastrar Venda\n{0} - Sair");
            System.out.println("O que você deseja fazer?: ");
            choose2 = in.nextInt();
            while (choose2 < 0 || choose > 3) {
              System.out.println("Por favor Digite um valor entre 0 e 3 ");
              choose2 = in.nextInt();
            }

            switch (choose2) {
                // === LISTAR VENDAS ===
              case 1:
                OtherFunctions.clear();
                Vendas.listarVendas(vendas, funcionarios);
                break;

              // === CADASTRAR VENDAS ===
              case 2:
                OtherFunctions.clear();
                Vendas.cadastrarVenda(vendas, funcionarios);
                break;

            }
          }

          break;
      }
    }

    OtherFunctions.clear();
    System.out.println("=== PROGRAMA FINALIZADO COM SUCESSO ===");
  }
}
