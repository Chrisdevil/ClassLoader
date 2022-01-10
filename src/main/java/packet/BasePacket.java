package packet;

public abstract class BasePacket {
    int id;

    public BasePacket(int id) {
        this.id = id;
    }


    abstract public void process();

    public int getId() {
        return id;
    }


}
