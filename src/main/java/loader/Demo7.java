package loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;


class ClassLoader extends URLClassLoader{

    public ClassLoader(URL[] urls) {
        super(urls);
    }

}

public class Demo7 {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, URISyntaxException {
        //	protocol/ 斜线是单个额
        URL u = new URL("file:\\C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\target\\classes\\classes\\");//末尾的斜线必须加上否则路径是错误的
        URL []urls = {u};
        File f = new File(u.toURI());
//        LogUtil.log('1',u.toURI());
//        LogUtil.log('2',f.exists());
        ClassLoader cl = new ClassLoader(urls);
//        LogUtil.log(3,cl);
//        LogUtil.log(4,cl.getURLs(),Arrays.toString(cl.getURLs()));
        Class<?> cls = cl.loadClass("classes.Add");
//        LogUtil.log(5,cls);
        Object o = cls.newInstance();
     //   LogUtil.log(6,o);
        System.out.println(o);
        System.out.println(o.getClass().getClassLoader());
//        LogUtil.log(7,o.getClass().getClassLoader());
//        LogUtil.log(8,Demo7.class.getClassLoader());

    }

}
