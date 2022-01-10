package packet;

public class ConnectPacket extends BasePacket {
    public ConnectPacket() {
        super(1);
    }


    @Override
    public void process() {
        System.out.println("ConnectPacket  v1.0");
        meanless();
//        Player player = GamePacketFactory.playerHashMap.get(getId());
//        System.out.println(Player.class.getClassLoader());
//        if (player == null) {
//            player = new Player("connect", 1, true);
//            GamePacketFactory.playerHashMap.put(getId(), player);
//        } else {
//            System.out.println(player);
//        }
//        System.out.println("next");
//        meanless();
    }

    public void meanless() {
        System.out.println(System.currentTimeMillis());
       System.out.println("connect ");
       new NewTest();
//        System.out.println("mean");
//        new NewAdd();
//        new NewAdd().anotherAdd();
//        NewAdd.staticAdd();
    }
}
