import com.google.gson.Gson;

public class Message {
    protected int msgType;
    protected int mmsi;
    protected int repeat;
    public int getMmsi() {
        return mmsi;
    }
    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }
    public int getRepeat() {
        return repeat;
    }
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
    void parse(Payload payload) throws NMEAMessageException {
        msgType = payload.getNextNbits(6).toInteger();
        repeat = payload.getNextNbits(2).toInteger();
        mmsi = payload.getNextNbits(30).toInteger();
    }
    void print()
    {
        System.out.printf("Tipo de mensaje: %d\n", msgType);
        System.out.printf("Indicador de repeticion: %d\n", repeat);
        System.out.printf("MMSI: %d\n", mmsi);
    }
    String toJson()
    {
        return new Gson().toJson(this);
    }
}
