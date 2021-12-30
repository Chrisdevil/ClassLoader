package classes;

public class Calculator {

    public void run() throws InterruptedException {
        System.out.println(this.getClass().getClassLoader());
        while (true){
            Add add = new Add();
            Multiply multiply = new Multiply();

            System.out.println(add.add());
            System.out.println(Subtract.subtract());

            System.out.println(multiply.multiply());
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Calculator calculator = new Calculator();
        calculator.run();
    }
}
