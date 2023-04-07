public class OtherFunctions{

  public static void line(){
    System.out.println("=-==-==-==-==-==-==-==-==-==-=");
  }

  public static void clear(){
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }
}