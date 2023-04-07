public class Cargo{
  private String cargo;
  private double salario;
  private double anoServico;
  private double beneficio;

  // === CONSTRUCTORS ===
  public Cargo(){}

  public Cargo(String cargo){
    if(cargo == "secretario"){
      this.cargo = "Secretario";
      this.salario = 7000;
      this.anoServico = 1000;
      this.beneficio = 0.2 * getSalario();
    } else if(cargo == "vendedor"){
      setCargo("Vendedor");
      setSalario(12000d);
      setAnoServico(1800d);
      setBeneficio(0.3);
    } else if(cargo == "gerente"){
      setCargo("Gerente");
      setSalario(20000d);
      setAnoServico(3000d);
      setBeneficio(0);
    }
  }
  
  // === GETTERS AND SETTERS

  //CARGO
  public void setCargo(String cargo){
    this.cargo = cargo;
  }

  public String getCargo(){
    return this.cargo;
  }

  //SALARIO
  public void setSalario(double salario){
    this.salario = salario;
  }

  public double getSalario(){
    return this.salario;
  }

  //ANOSERVICO
  public void setAnoServico(double anoServico){
    this.anoServico = anoServico;
  }

  public double getAnoServico(){
    return this.anoServico;
  }

  //BENEFICIO
  public void setBeneficio(double beneficio){
    this.beneficio = beneficio;
  }

  public double getBeneficio(){
    return this.beneficio;
  }
}