import com.google.gson.Gson;

public class Message {
    protected int msgType; //tipo de mensaje, entre 1 y 27
    protected int mmsi;  //Número de Identificación del Servicio Móvil Marítimo
    protected int repeat; //Indica si este mensaje debe ser reenviado por un ais transceiver

    /**
     * Obtiene cada campo del mensaje
     * @param payload
     * @throws NMEAMessageException
     */
    void parse(Payload payload) throws NMEAMessageException {
        msgType = payload.getNextNbits(6).toInteger();
        repeat = payload.getNextNbits(2).toInteger();
        mmsi = payload.getNextNbits(30).toInteger();
    }

    /**
     * Imprime por consola los campos del mensaje
     */
    void print()
    {
        System.out.printf("Tipo de mensaje: %d\n", msgType);
        System.out.printf("Indicador de repeticion: %d\n", repeat);
        System.out.printf("MMSI: %d\n", mmsi);
    }
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

    /**
     * Convierte esta objeto en formato JSON
     * @return
     */
    String toJson()
    {
        return new Gson().toJson(this);
    }
}
