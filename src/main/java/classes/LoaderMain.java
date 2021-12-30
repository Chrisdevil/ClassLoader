package classes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoaderMain {
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        String className = "classes.Calculator";
        Object o = classLoader.loadClass(className).newInstance();
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        System.out.println(path);
        File file = new File(path + "classes");
        HashMap<String, Long> fileTimes = new HashMap<>();
        // File file1 = new File("C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\target\\classes\\classes");
        for (File listFile : Objects.requireNonNull(file.listFiles())) {
            if(listFile.getName().startsWith("Loader")){
                continue;
            }
            fileTimes.put(listFile.getName(), listFile.lastModified());
        }
        ScheduledExecutorService service = Executors.newScheduledThreadPool(8);
        service.scheduleAtFixedRate(() -> scheduleTask(fileTimes,classLoader) , 0, 5, TimeUnit.SECONDS);
//        new Thread(()->{
//            while (true){
//                scheduleTask(fileTimes,classLoader);
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        //   System.out.println(file1.length());

        Method[] methods = o.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals("run")) {
                method.invoke(o);
            }
        }


    }
    public static void scheduleTask( HashMap<String, Long> fileTimes,ClassLoader classLoader){
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();

        File newFile = new File(path + "classes");
        System.out.println(Arrays.toString(newFile.listFiles()));
        for (File listFile : newFile.listFiles()) {
            if(listFile.getName().startsWith("Loader")){
                continue;
            }
            if (listFile.lastModified() != fileTimes.getOrDefault(listFile.getName(), -1L)) {
                try {
                    classLoader.loadClass("classes." + listFile.getName().replace(".class", ""));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                fileTimes.put(listFile.getName(), listFile.lastModified());
            }
        }
    }


}
