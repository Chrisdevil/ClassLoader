package loader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestClassLoader extends URLClassLoader {
    public TestClassLoader(URL[] urls) {
        super(urls);
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        URL url = new URL("file:\\C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\target\\classes\\classes\\");
        TestClassLoader testClassLoader = new TestClassLoader(new URL[]{url});
        Object o = testClassLoader.loadClass("classes.Add").newInstance();
        System.out.println(o.getClass());
        System.out.println(o.getClass().getClassLoader());
    }
}
