package loader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JarClassLoader {
    public static class MyUrlClassLoader extends URLClassLoader {

        public MyUrlClassLoader(URL[] urls) {
            super(urls);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                //  String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream inputStream = getClass().getResourceAsStream(name);
                if (inputStream == null) {
                    return super.loadClass(name);
                }
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                //  String[]names = name.split("/");
                name = name.substring(1);
                String[] names = name.split("\\.");
                name = names[0];
                name = name.replace("/", ".");

                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                throw new ClassNotFoundException(name);
            }
        }

    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, InterruptedException {
        while (true){
            MyUrlClassLoader urlClassLoader = new MyUrlClassLoader(new URL[]{new URL("file:\\C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\out\\artifacts\\ClassHotLoader_jar\\ClassHotLoader.jar")});
            Object o = urlClassLoader.loadClass("/classes/Add.class").newInstance();
            Method[] methods = o.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals("add")) {
                    method.invoke(o);
                }
            }
            Thread.sleep(2000);
        }
    }
}
