package classes;

public class Calculator {


    public void run() throws InterruptedException {
        Add add = new Add();
        System.out.println(this.getClass().getClassLoader());
        Mod mod = new Mod();
        mod.mod();

        Multiply multiply = new Multiply();

        System.out.println(add.add());
        System.gc();
        // System.out.println(Subtract.subtract());

        //System.out.println(multiply.multiply());


    }

}
