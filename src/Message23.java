public class Message23 extends Message{
    private float ne_lon;
    private float ne_lat;
    private float sw_lon;
    private float sw_lat;
    private int station_type;
    private int ship_type;
    private int txrx;
    private int interval;
    private int quiet;
    @Override
    public void parse(Payload payload) throws Exception
    {
        super.parse(payload);
        payload.getData().getNbits(2); //sin usar
        ne_lon = payload.getData().getNbits(18).toSignedInt() * 0.0001f / 60;
        ne_lat = payload.getData().getNbits(17).toSignedInt() * 0.0001f / 60;
        sw_lon = payload.getData().getNbits(18).toSignedInt() * 0.0001f / 60;
        sw_lat = payload.getData().getNbits(17).toSignedInt() * 0.0001f / 60;
        station_type = payload.getData().getNbits(4).toInteger();
        ship_type = payload.getData().getNbits(8).toInteger();
        payload.getData().getNbits(22); //sin usar
        txrx = payload.getData().getNbits(2).toInteger();
        interval = payload.getData().getNbits(4).toInteger();
        quiet = payload.getData().getNbits(4).toInteger();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Ne longitud: %f\n", ne_lon);
        System.out.printf("Ne latiud: %f\n", ne_lat);
        System.out.printf("SW longitud: %f\n", sw_lon);
        System.out.printf("SW latiud: %f\n", sw_lat);
        System.out.printf("Tipo de estación: %d\n", station_type);
        System.out.printf("Tipo de navío: %s\n", Types.shipTypes[ship_type]);
        System.out.printf("Modo Tx/Rx: %s\n", Types.transmitModes[txrx]);
    }
}
