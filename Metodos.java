import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

class Metodos extends Funcionario{
    // === METHODS ===

    // Variáveis auxiliares para analise dos vetores
    static Funcionario func = new Funcionario();
    static Vendas func_vendas = new Vendas();
    
    // Objetivo: Verificar se determinado funcionário efetuou uma venda no mês requisitado
    public static double verificaVenda(ArrayList<Vendas> vendas, int mes, int ano, int IdFuncionario){
      double venda = 0;
      
      for(int i = 0; i < vendas.size(); i++){
        func_vendas = vendas.get(i);
        if(IdFuncionario == func_vendas.getIdFuncionario() && mes == func_vendas.dataVenda.getIntMes() && ano == func_vendas.dataVenda.getAno()){
          venda = func_vendas.getValorVenda();
        }
      }
      return venda;
    } 
    
    //=== INÍCIO VALOR TOTAL PAGO AOS FUNCIONÁRIOS ===
    public static void totalPago(ArrayList<Funcionario> funcionarios, ArrayList<Vendas> vendas,int mes, int ano){  
      boolean existeFuncionario = false;
      System.out.println("VALOR TORAL PAGO AOS FUNCIONÁRIOS EM " + mes + "/" + ano);
  
      for(int i =0; i < funcionarios.size(); i++){
        double finalValue = 0;
        func = funcionarios.get(i);   
        
        if(func.getStatus() && analisaAno(func, mes, ano)){
          existeFuncionario = true;
           // Analisa cargo e define o valor final
          if(func.getCargo() == "Vendedor"){
            if(mes == func.contratacao.getIntMes() && (ano - func.contratacao.getAno() >= 1)){
              finalValue = func.cargo.getSalario() + (func.cargo.getBeneficio() * verificaVenda(vendas, mes, ano, func.getIdFuncionario())) + func.cargo.getAnoServico(); 
            }
            else{
              finalValue = func.cargo.getSalario() + (func.cargo.getBeneficio() * verificaVenda(vendas, mes, ano, func.getIdFuncionario())); 
            }
          } 
    
          // Se o funcionario for Secretario
          else if(func.getCargo() == "Secretario"){
            if(mes == func.contratacao.getIntMes() && (ano - func.contratacao.getAno() >= 1)){
              finalValue = (func.cargo.getSalario() + func.cargo.getAnoServico()) + ((func.cargo.getSalario() + func.cargo.getAnoServico()) * 0.2); 
            } else{
              finalValue = func.cargo.getSalario() + func.cargo.getBeneficio(); 
            }
          } 
    
          // Se o funcionario for Gerente
          else if(func.getCargo() == "Gerente"){
            if(mes == func.contratacao.getIntMes() && (ano - func.contratacao.getAno() >= 1)){
              finalValue = func.cargo.getSalario() + func.cargo.getAnoServico();
            } else{
              finalValue = func.cargo.getSalario();
            }
          }
  
          // Mostrando valores
          System.out.println("Funcionário: " + func.getNome() + " | Cargo: " + func.getCargo() + " | Valor total pago: R$" + finalValue);
          }
        } 
  
      if(!existeFuncionario){
        System.out.println("Não existiam funcionários cadastrados nessa data");
      }
    }
    //=== FIM VALOR TOTAL PAGO AOS FUNCIONÁRIOS ===
  
    
    //=== INÍCIO SALÁRIO PAGO AOS FUNCIONÁRIOS ===
    public static void salarioPago(ArrayList<Funcionario> funcionarios, int mes, int ano){
      boolean existeFuncionario = false;
      System.out.println("SALÁRIO PAGO AOS FUNCIONÁRIOS EM " + mes + "/" + ano);
      
      for(int i =0; i< funcionarios.size(); i++){
        func = funcionarios.get(i);
        if(func.getStatus() && analisaAno(func, mes, ano)){
          existeFuncionario = true;
          if(mes == func.contratacao.getIntMes() && (ano - func.contratacao.getAno() >= 1)){
            System.out.println("Funcionário: " + func.getNome() + " | Cargo: " + func.getCargo() + " | Salário: R$" + (func.cargo.getSalario() + func.cargo.getAnoServico()));
          } else{
            System.out.println("Funcionário: " + func.getNome() + " | Cargo: " + func.getCargo() + " | Salário: R$" + func.cargo.getSalario());
          }
        }
      }  
      if(!existeFuncionario){
        System.out.println("Não existiam funcionários cadastrados nessa data");
      }
    }
     //=== FIM SALÁRIO PAGO AOS FUNCIONÁRIOS ===
  
    
     //=== INÍCIO BENEFÍCIOS PAGO AOS FUNCIONÁRIOS ===
    public static void valorTotalBeneficios(ArrayList<Funcionario> funcionarios, ArrayList<Vendas> vendas,int mes, int ano){
      boolean existeFuncionario = false;
      System.out.println("BENEFÍCIOS PAGO AOS FUNCIONÁRIOS EM " + mes + "/" + ano);
  
      for(int i =0; i < funcionarios.size(); i++){
        func = funcionarios.get(i);   
        double beneficios = 0;
  
        // Verifica se funcionário está ativo
        if(func.getStatus() && analisaAno(func, mes, ano)){
          existeFuncionario = true;
          if(func.getCargo() == "Vendedor"){
            beneficios = func.cargo.getBeneficio() * verificaVenda(vendas, mes, ano, func.getIdFuncionario());
          } 
          
          else if(func.getCargo() == "Secretario"){
            if(mes == func.contratacao.getIntMes() && (ano - func.contratacao.getAno() >= 1)){
              beneficios = (func.cargo.getSalario() + func.cargo.getAnoServico()) * 0.2;
            } else{
              beneficios = func.cargo.getBeneficio();  
            }
             
          } 
          
          // Gerente não recebe benefícios
          else if(func.getCargo() == "Gerente"){
            continue;
          }
    
          // Mostrando valores
          System.out.println("Funcionário: " + func.getNome() + " | Cargo: " + func.getCargo() + " | Benefícios Recebidos: R$" + beneficios);
        }
      }
  
      if(!existeFuncionario){
        System.out.println("Não existiam funcionários cadastrados nessa data");
      }
    }
    //=== FIM BENEFÍCIOS PAGO AOS FUNCIONÁRIOS ===
  
  
    //=== INÍCIO MAIOR VALOR RECEBIDO DOS FUNCIONÁRIOS ===
    public static void maiorValorRecebidoMes(ArrayList<Funcionario> funcionarios, ArrayList<Vendas> vendas,int mes, int ano){
      ArrayList<Funcionario> maioresRecebidos = new ArrayList<Funcionario>();
      boolean existeFuncionario = false;
      System.out.println("MAIOR VALOR RECEBIDO EM " + mes + "/" + ano);
      double maiorValor = 0;
      double finalValue = 0;
      
      for(int i =0; i < funcionarios.size(); i++){
        func = funcionarios.get(i);   
        
  
        if(func.getStatus() && analisaAno(func, mes, ano)){
          existeFuncionario = true;
          if(func.getCargo() == "Vendedor"){
            if(mes == func.contratacao.getIntMes() && (ano - func.contratacao.getIntMes() >= 1)){
              finalValue = func.cargo.getSalario() + (func.cargo.getBeneficio() * verificaVenda(vendas, mes, ano, func.getIdFuncionario())) + func.cargo.getAnoServico(); 
            }
            else{
              finalValue = func.cargo.getSalario() + (func.cargo.getBeneficio() * verificaVenda(vendas, mes, ano, func.getIdFuncionario())); 
            }          
          } 
          
          else if(func.getCargo() == "Secretario"){
            if(mes == func.contratacao.getIntMes() && (ano - func.contratacao.getIntMes() >= 1)){
              finalValue = func.cargo.getSalario() + ((func.cargo.getSalario() + func.cargo.getAnoServico()) * 0.2); 
            } else{
              finalValue = func.cargo.getSalario() + func.cargo.getBeneficio(); 
            } 
          } 
          
          else if(func.getCargo() == "Gerente"){
            if(mes == func.contratacao.getIntMes() && (ano - func.contratacao.getAno() >= 1)){
              finalValue = func.cargo.getSalario() + func.cargo.getAnoServico();
            } else{
              finalValue = func.cargo.getSalario();
            }
          }
  
          if(finalValue > maiorValor){
            maiorValor = finalValue;
          }
        }
      }

      for(int j = 0; j<funcionarios.size(); j++){
        func = funcionarios.get(j);
        if(func.getStatus() && analisaAno(func, mes, ano)){
          if(func.getCargo() == "Vendedor"){
            finalValue = func.cargo.getSalario() + (func.cargo.getBeneficio() * verificaVenda(vendas, mes, ano, func.getIdFuncionario()));          
          } 
          
          else if(func.getCargo() == "Secretario"){
            finalValue = func.cargo.getSalario() + func.cargo.getBeneficio(); 
          } 
          
          else if(func.getCargo() == "Gerente"){
            finalValue = func.cargo.getSalario();
          }
  
          if(finalValue == maiorValor){
            maioresRecebidos.add(func);
          }
        }
      }
  
      if(existeFuncionario){
        System.out.println("Os maiores recebidos de " + mes + "/" + ano);
        for(int k = 0; k < maioresRecebidos.size(); k++){
          func = maioresRecebidos.get(k);
          System.out.println("- " + func.getNome() + ". Valor recebido: " + maiorValor);
        }
      } else{
        System.out.println("Não existiam funcionários cadastrados nessa época!");
      }
    
    }
  
    // === INÍCIO MAIOR VALOR BENEFICIOS ===
    public static void maiorValorBeneficios(ArrayList<Funcionario> funcionarios, ArrayList<Vendas> vendas,int mes, int ano){
      ArrayList<Funcionario> maioresBeneficios = new ArrayList<Funcionario>();
      boolean existeFuncionario = false;
      System.out.println("MAIOR BENEFÍCIO RECEBIDO EM " + mes + "/" + ano);
      double lastBeneficio = 0;
      double beneficios = 0;
  
      for(int i =0; i < funcionarios.size(); i++){
        func = funcionarios.get(i);   
  
        if(func.getStatus() && analisaAno(func, mes, ano)){
          existeFuncionario = true;
          if(func.getCargo() == "Vendedor"){
            beneficios = func.cargo.getBeneficio() * verificaVenda(vendas, mes, ano, func.getIdFuncionario());
          } 
          
          else if(func.getCargo() == "Secretario"){
            if(mes == func.contratacao.getIntMes() && (ano - func.contratacao.getIntMes() >= 1)){
              beneficios = (func.cargo.getSalario() + func.cargo.getAnoServico()) * 0.2;
            } else{
              beneficios = func.cargo.getBeneficio();  
            }
          } 
            
          if(beneficios > lastBeneficio){
            lastBeneficio = beneficios;
          }
        }
      }

      for(int j = 0; j < funcionarios.size(); j++){
        func = funcionarios.get(j);
        if(func.getStatus() && analisaAno(func, mes, ano)){
          existeFuncionario = true;
          if(func.getCargo() == "Vendedor"){
            beneficios = func.cargo.getBeneficio() * verificaVenda(vendas, mes, ano, func.getIdFuncionario());
          } 
          
          else if(func.getCargo() == "Secretario"){
            beneficios = func.cargo.getBeneficio(); 
          }
          else if(func.getCargo() == "Gerente"){
            continue;
          }
            
          if(beneficios == lastBeneficio){
            maioresBeneficios.add(func);
          }
        }
        
      }
  
      // Mostrando valores
       if(existeFuncionario){
        System.out.println("Os maiores benficios de " + mes + "/" + ano);
        for(int k = 0; k < maioresBeneficios.size(); k++){
          func = maioresBeneficios.get(k);
          System.out.println("- " + func.getNome() + ". Valor recebido: " + lastBeneficio);
        }
      } else{
        System.out.println("Não existiam funcionários cadastrados nessa época!");
      }
    }
  
    // === FIM MAIOR VALOR BENEFICIOS ===
  
  
    // === INÍCIO FUNCIONÁRIO QUE MAIS VENDEU ===
    public static void vendeuMais(ArrayList<Funcionario> funcionarios, ArrayList<Vendas> vendas,int mes, int ano){
      ArrayList<Vendas> maioresVendas = new ArrayList<Vendas>();
      ArrayList<String> nomeVendedores = new ArrayList<String>();
      boolean existeFuncionario = false;
      double valorVendido = 0;

      System.out.println("FUNCIONÁRIO(S) QUE MAIS VENDEU(RAM) EM " + mes + "/" + ano);
      
      for(int i = 0; i< vendas.size(); i++){
        func_vendas = vendas.get(i);
        if(mes == func_vendas.dataVenda.getIntMes() && ano == func_vendas.dataVenda.getAno() && func.getStatus()){
          existeFuncionario = true;
          if(func_vendas.getValorVenda() > valorVendido){
            valorVendido = func_vendas.getValorVenda();
          }
        }
      }

      for(int j = 0; j < vendas.size(); j++){
        func_vendas = vendas.get(j);
        if(mes == func_vendas.dataVenda.getIntMes() && ano == func_vendas.dataVenda.getAno() && func.getStatus()){
          existeFuncionario = true;
          if(func_vendas.getValorVenda() == valorVendido){
            maioresVendas.add(func_vendas);
            // Pegando o nome do vendedor
            for(int l = 0; l < funcionarios.size(); l++){
              func = funcionarios.get(l);
              if(func.getIdFuncionario() == func_vendas.getIdFuncionario()){
                nomeVendedores.add(func.getNome());
              }
            }
          }
        }
      }
  
      // encontrar funcionário
      if(existeFuncionario){
        System.out.println("As maiores vendas de " + mes + "/" + ano);
        for(int k = 0; k < maioresVendas.size(); k++){
          func_vendas = maioresVendas.get(k);
          System.out.println("- " + nomeVendedores.get(k) + ". Valor recebido: " + valorVendido);
        }
      } else{
        System.out.println("Não existiam vendedores cadastrados nessa época!");
      }
    }
    // === FIM FUNCIONÁRIO QUE MAIS VENDEU ===
}