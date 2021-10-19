public class Message22 extends Message {
    private int channel_a;
    private int channel_b;
    private int txrx;
    private int power;
    private float ne_lon;
    private float ne_lat;
    private float sw_lon;
    private float sw_lat;
    private int dest1;
    private int dest2;
    private int addressed;
    private int band_a;
    private int band_b;
    private int zonesize;
    @Override
    public void parse(Payload payload) throws NMEAMessageException {
        if(payload.size() != 168) throw new NMEAMessageException("Mensaje tipo 22: longitud erronea");
        addressed = payload.getData().substring(139, 140).toInteger();
        super.parse(payload);
        payload.getData().getNbits(2);
        channel_a = payload.getData().getNbits(12).toInteger();
        channel_b = payload.getData().getNbits(12).toInteger();
        txrx = payload.getData().getNbits(4).toInteger();
        power = payload.getData().getNbits(1).toInteger();
        if(addressed == 0)
        {
            ne_lon = payload.getData().getNbits(18).toSignedInt() * 0.0001f / 60;
            ne_lat = payload.getData().getNbits(17).toSignedInt() * 0.0001f / 60;
            sw_lon = payload.getData().getNbits(18).toSignedInt() * 0.0001f / 60;
            sw_lat = payload.getData().getNbits(17).toSignedInt();
            payload.getData().getNbits(1);
            band_a = payload.getData().getNbits(1).toInteger();
            band_b = payload.getData().getNbits(1).toInteger();
            zonesize = payload.getData().getNbits(3).toInteger();
        }else
        {
            dest1 =  payload.getData().getNbits(30).toInteger();
            dest2 =  payload.getData().getNbits(30).toInteger();
        }
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Canal A: %d\n", channel_a);
        System.out.printf("Canal B: %d\n", channel_b);
        System.out.printf("Modo TX/RX: %d\n", txrx);
        System.out.printf("Power: %d\n", power);
        System.out.printf("Ne longitud: %f\n", ne_lon);
        System.out.printf("Ne latiud: %f\n", ne_lat);
        System.out.printf("SW longitud: %f\n", sw_lon);
        System.out.printf("SW latiud: %f\n", sw_lat);
        System.out.printf("MMSI1: %d\n", dest1);
        System.out.printf("MMSI2: %d\n", dest2);
        System.out.printf("Addressed: %d\n", addressed);
        System.out.printf("Banda A %d (0 = Por defecto, 1 = 12.5kHz)\n", band_a);
        System.out.printf("Banda B %d (0 = Por defecto, 1 = 12.5kHz)\n", band_b);
        System.out.printf("Zonesize: %d\n", zonesize);
    }

}
