public class Message23 extends Message {
    private float ne_lon;
    private float ne_lat;
    private float sw_lon;
    private float sw_lat;
    private String station_type;
    private String ship_type;
    private int txrx;
    private String interval;
    private int quiet;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        ne_lon = payload.getNextNbits(18).toSignedInt() * 0.0001f / 60;
        ne_lat = payload.getNextNbits(17).toSignedInt() * 0.0001f / 60;
        sw_lon = payload.getNextNbits(18).toSignedInt() * 0.0001f / 60;
        sw_lat = payload.getNextNbits(17).toSignedInt() * 0.0001f / 60;
        station_type = Types.stationTypes[payload.getNextNbits(4).toInteger()];
        ship_type = Types.shipTypes[payload.getNextNbits(8).toInteger()];
        payload.getNextNbits(22); //sin usar
        txrx = payload.getNextNbits(2).toInteger();
        interval = Types.intervals[payload.getNextNbits(4).toInteger()];
        quiet = payload.getNextNbits(4).toInteger();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Ne longitud: %f\n", ne_lon);
        System.out.printf("Ne latiud: %f\n", ne_lat);
        System.out.printf("SW longitud: %f\n", sw_lon);
        System.out.printf("SW latiud: %f\n", sw_lat);
        System.out.printf("Tipo de estación: %s\n", station_type);
        System.out.printf("Tipo de navío: %s\n", ship_type);
        System.out.printf("Modo Tx/Rx: %s\n", Types.transmitModes[txrx]);
    }
}
