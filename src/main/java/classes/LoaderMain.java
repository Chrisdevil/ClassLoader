package classes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class LoaderMain {

    public static Object o = new Object();
    public static Add add = new Add();
    public static Calculator calculator = new Calculator();
    public static MyUrlClassLoader urlClassLoader;
    public static MyClassLoader myClassLoader;

    static {
        try {
            urlClassLoader = new MyUrlClassLoader(new URL[]{new URL("file:\\C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\target\\classes")});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static class MyUrlClassLoader extends URLClassLoader {
        public MyUrlClassLoader(URL[] urls) {
            super(urls);
        }

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
    }

    public static class MyClassLoader extends ClassLoader {
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
    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, MalformedURLException {

//        ClassLoader classLoader = new ClassLoader() {
//            @Override
//            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                try {
//                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//                    InputStream inputStream = getClass().getResourceAsStream(fileName);
//                    if (inputStream == null) {
//                        return super.loadClass(name);
//                    }
//                    byte[] bytes = new byte[inputStream.available()];
//                    inputStream.read(bytes);
//                    return defineClass(name, bytes, 0, bytes.length);
//                } catch (IOException e) {
//                    throw new ClassNotFoundException(name);
//                }
//            }
//        };
        MyClassLoader classLoader = new MyClassLoader();
        // MyUrlClassLoader classLoader = new MyUrlClassLoader(new URL[]{new URL("file:\\C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\target\\classes")});
        String className = "classes.Calculator";
        o = classLoader.loadClass(className).newInstance();
        System.out.println(o.getClass().getClassLoader());
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        System.out.println(path);
        File file = new File(path + "classes");
        HashMap<String, Long> fileTimes = new HashMap<>();
        // File file1 = new File("C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\target\\classes\\classes");
        for (File listFile : Objects.requireNonNull(file.listFiles())) {
            if (listFile.getName().startsWith("Loader")) {
                continue;
            }
            fileTimes.put(listFile.getName(), listFile.lastModified());
        }
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1, r -> {
            Thread.currentThread().setName("scheduled");
            return new Thread(r);
        });
//        service.scheduleAtFixedRate(() -> {
//            try {
//                scheduleTask(fileTimes,o);
//            } catch (InvocationTargetException | InterruptedException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
//                e.printStackTrace();
//            }
//        }, 0, 1, TimeUnit.SECONDS);
        while (true) {
            scheduleTask(fileTimes);
            Thread.sleep(1000);
        }
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

//        Method[] methods = o.getClass().getMethods();
//        for (Method method : methods) {
//            if (method.getName().equals("run")) {
//                method.invoke(o);
//            }
//        }


    }

    public static void scheduleTask(HashMap<String, Long> fileTimes) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, InterruptedException, MalformedURLException {
        //  MyUrlClassLoader myClassLoader = new MyUrlClassLoader(new URL[]{new URL("file:\\C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\target\\classes")});
        MyClassLoader myClassLoader = new MyClassLoader();
        //System.out.println(classLoader);
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();

        File newFile = new File(path + "classes");
        //  System.out.println(Arrays.toString(newFile.listFiles()));
        for (File listFile : newFile.listFiles()) {
            if (listFile.getName().startsWith("Loader")) {
                continue;
            }
            if (listFile.lastModified() != fileTimes.getOrDefault(listFile.getName(), -1L)) {
                try {
                    //  if (listFile.getName().equals("Calculator.class")) {
                    //classLoader.close();
                    myClassLoader.loadClass("classes." + listFile.getName().replace(".class", "")).newInstance();
                    if (!listFile.getName().equals("Calculator.class")) {
                        myClassLoader.loadClass("classes.Calculator");
                    }// System.out.println(o.getClass().getClassLoader());
                    //  }
                    System.out.println("reload" + listFile.getName());

                    // classLoader = classLoader;
                    //new Add().add();
//                    Method[] methods1 = o.getClass().getMethods();
//                    for (Method method : methods1) {
//                        if (method.getName().equals("add")) {
//                            System.out.println("add");
//                            System.out.println(method.invoke(o));
//                            ;
//                        }
//                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                fileTimes.put(listFile.getName(), listFile.lastModified());
            }

        }
//        Calculator calculator = new Calculator();
//        calculator.run();
        // calculator.run();
        //      try {
        //    Object cal = Class.forName("classes.Calculator").newInstance();


//        Method[] methods = o.getClass().getMethods();
//        for (Method method : methods) {
//            if (method.getName().equals("run")) {
//                System.out.println("run");
//                method.invoke(o);
//            }
//        }
//            Object add = Class.forName("classes.Add").newInstance();
//        Method[] methods1 = o.getClass().getMethods();
//        for (Method method : methods1) {
//            if (method.getName().equals("add")) {
//                System.out.println("add");
//                System.out.println(method.invoke(o));
//                ;
//            }
//        }
        // new Add().add();
        // System.out.println(add.add());
//            Object o = classLoader.loadClass("classes.Add").newInstance();
//            System.out.println(o);
//          //  System.out.println("reload"+listFile.getName());
//            Method[] methods1 = o.getClass().getMethods();
//            for (Method method : methods1) {
//                if (method.getName().equals("add")) {
//                    System.out.println("add");
//                    System.out.println(method.invoke(o));;
//                }
//            }
//            Add add = new Add();
//            System.out.println(add.add());
//        }
//        catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
//            e.printStackTrace();
//        }
    }


}
