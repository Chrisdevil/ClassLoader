package classes;

public class Subtract {
    private static int start = 0;

    public static int subtract() {
//        System.out.println(Subtract.class.getClassLoader());
        System.out.println("subtract");
        return --start;
    }
}
