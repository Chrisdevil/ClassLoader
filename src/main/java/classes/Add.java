package classes;

public class Add {
   private int start = 0;




   public  int add() {
//       System.out.println(this.getClass().getClassLoader());
       System.out.println("add");
       System.out.println(getClass().getClassLoader());
        return start+=1;
    }
}
