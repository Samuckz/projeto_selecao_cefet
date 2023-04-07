import static java.lang.Double.*;

public class Data{
  private int mes;
  private int ano;
  
  public Data(){}

  
  // Geral
  public Data(int mes, int ano){
    setMes(mes);
    setAno(ano);
  }

  // ============== DATA ================
  public int getIntMes(){
    return mes;
  }
  
  public String getMes(){
    if (mes < 10) {
      return "0" + mes;
    }
    return "" + mes;
  }

  public void setMes(int m){
    if (m < 1) {
      mes = 1;
    } else if (m > 12) {
      mes = 12;
    } else {
      mes = m;
    }
  }

  public int getAno(){
    return ano;
  }

  public void setAno(int a){
    ano = a;
  }
}