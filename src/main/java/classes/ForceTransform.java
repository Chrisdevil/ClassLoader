package classes;

import java.lang.reflect.Method;

public class ForceTransform {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException {
        while (true){
            Class<?> clazz = Class.forName("classes.Add");
            Object object = clazz.newInstance();
            System.out.println( object instanceof Add);
            Add add = (Add) object;
            add.add();
//            for (Method method : clazz.getMethods()) {
//                System.out.println(method.getName());
//            }
            Thread.sleep(2000);
        }
    }
}
