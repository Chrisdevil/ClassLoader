package loader;

import classes.Player;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class GamePacketFactory {


    public static long refreshTime = 0;
    public static HashMap<Integer, Class<?>> objectHashMap = new HashMap<>();
    public static HashMap<Integer, Player> playerHashMap = new HashMap<>();

//    public static void init() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
//        JarClassLoader.MyUrlClassLoader urlClassLoader = new JarClassLoader.MyUrlClassLoader(
//                new URL[]{new URL("file:\\C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\out\\artifacts\\ClassHotLoader_jar\\ClassHotLoader.jar")});
//        JarFile jarFile = new JarFile(new File("C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\out\\artifacts\\ClassHotLoader_jar\\ClassHotLoader.jar"));
//        for (Iterator<JarEntry> iterator = jarFile.entries().asIterator(); iterator.hasNext(); ) {
//            JarEntry next = iterator.next();
//            if (next.getRealName().startsWith("packet/") && next.getRealName().endsWith(".class")) {
//                Class<?> clazz = urlClassLoader.loadClass("/" + next.getRealName());
//                if (Modifier.isAbstract(clazz.getModifiers())) {
//                    continue;
//                }
//                Object o = clazz.newInstance();
//                for (Method method : o.getClass().getMethods()) {
//                    if (method.getName().equals("getId")) {
//                        int id = (Integer) method.invoke(o);
//                        objectHashMap.put(id, clazz);
//                    }
//
//                }
//
//
////                InputStream inputStream = jarFile.getInputStream(next);
////                String[] names = next.getRealName().split("/");
////                String needName = names[names.length - 1];
////                var classInstance = urlClassLoader.def
//            }
//        }
//    }

//    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
//        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
//        service.scheduleAtFixedRate(() -> {
//            File file = new File("C:\\Users\\Betta\\IdeaProjects\\ClassHotLoader\\out\\artifacts\\ClassHotLoader_jar\\ClassHotLoader.jar");
//            if (file.lastModified() != refreshTime) {
//                try {
//                    init();
//                } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 0, 3, TimeUnit.SECONDS);
//        Thread.sleep(2000);
//
//        while (true) {
//            int rand = new Random().nextInt(5);
//            Object o = createPacket(rand % 2);
//            for (Method method : o.getClass().getMethods()) {
//                if (method.getName().equals("process")) {
//                    method.invoke(o);
//                }
//            }
//            Thread.sleep(rand * 1000);
//        }
//    }

    public static Object createPacket(int id) throws InstantiationException, IllegalAccessException {
        Class<?> clazz = objectHashMap.getOrDefault(id, null);
        if (clazz != null) {
            return objectHashMap.get(id).newInstance();
        } else {
            return null;
        }

    }
}
