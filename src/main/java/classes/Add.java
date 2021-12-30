package classes;

public class Add {
   private static int start = 0;

   public  int add() {
       //System.out.println(this.getClass().getClassLoader());
       System.out.println("add");
        return start+=1;
    }
}
