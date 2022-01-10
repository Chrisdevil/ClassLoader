package lastInstrument;

import packet.ConnectPacket;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class App {
    public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException, ClassNotFoundException, NoSuchMethodException {
        //  GamePacketFactory.init();
        ArrayList<ConnectPacket> list = new ArrayList<>();
        while (true) {
            int rand = new Random().nextInt(5);
//            Object o = GamePacketFactory.createPacket(rand % 2);
//            for (Method method : o.getClass().getMethods()) {
//                if (method.getName().equals("process")) {
//                    method.invoke(o);
//                }
//            }
            ConnectPacket connectPacket = new ConnectPacket();
            connectPacket.meanless();
//            for (int i = 100000000; i > 0; i--) {
//                ConnectPacket connectPacket = new ConnectPacket();
//                connectPacket.meanless();
//                list.add(connectPacket);
//            }
            Thread.sleep(rand * 1000);
            list.clear();
        }
//        while (true) {
//            System.out.println(System.currentTimeMillis());
//            Thread.sleep(1000);
//        }

    }
}
