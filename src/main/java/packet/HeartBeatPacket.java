package packet;

import classes.Player;
import loader.GamePacketFactory;

public class HeartBeatPacket extends BasePacket{
    public HeartBeatPacket() {
        super(0);
    }


    @Override
    public void process() {
        System.out.println("HeartBeatPacket  v1212.0");
//        Player player =  GamePacketFactory.playerHashMap.get(getId());
//        System.out.println(Player.class.getClassLoader());
//        if(player==null){
//            player = new Player("heartbeat",0,false);
//            GamePacketFactory.playerHashMap.put(getId(),player);
//        }else {
//            System.out.println(player);
//        }
    }
    public static void test(){
        System.out.println("test");
    }
}
