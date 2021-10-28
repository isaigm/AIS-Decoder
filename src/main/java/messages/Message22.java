/*
    +---------+-----+------------------+-----------+----+-------------------------------+
    | Field   | Len | Description      | Member    | T  | Units                         |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 0-5     | 6   | Message Type     | type      | u  | Constant: 22                  |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 6-7     | 2   | Repeat Indicator | repeat    | u  | As in Common Navigation Block |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 8-37    | 30  | MMSI             | mmsi      | u  | 9 decimal digits              |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 38-39   | 2   | Spare            |           | x  | Not used                      |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 40-51   | 12  | Channel A        | channel_a | u  | Channel number                |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 52-63   | 12  | Channel B        | channel_b | u  | Channel number                |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 64-67   | 4   | Tx/Rx mode       | txrx      | u  | Transmit/receive mode         |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 68-68   | 1   | Power            | power     | b  | Low=0, high=1                 |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 69-86   | 18  | NE Longitude     | ne_lon    | I1 | NE longitude to 0.1 minutes   |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 87-103  | 17  | NE Latitude      | ne_lat    | I1 | NE latitude to 0.1 minutes    |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 104-121 | 18  | SW Longitude     | sw_lon    | I1 | SW longitude to 0.1 minutes   |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 122-138 | 17  | SW Latitude      | sw_lat    | I1 | SW latitude to 0.1 minutes    |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 69-98   | 30  | MMSI1            | dest1     | u  | MMSI of destination 1         |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 104-133 | 30  | MMSI2            | dest2     | u  | MMSI of destination 2         |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 139-139 | 1   | Addressed        | addressed | b  | 0=Broadcast, 1=Addressed      |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 140-140 | 1   | Channel A Band   | band_a    | b  | 0=Default, 1=12.5kHz          |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 141-141 | 1   | Channel B Band   | band_b    | b  | 0=Default, 1=12.5kHz          |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 142-144 | 3   | Zone size        | zonesize  | u  | Size of transitional zone     |
    +---------+-----+------------------+-----------+----+-------------------------------+
    | 145-167 | 23  | Spare            |           | x  | Reserved for future use       |
    +---------+-----+------------------+-----------+----+-------------------------------+
 */
package messages;
import decoder.Payload;
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
        payload.getNextNbits(2);
        channel_a = payload.getNextNbits(12).toInteger();
        channel_b = payload.getNextNbits(12).toInteger();
        txrx = payload.getNextNbits(4).toInteger();
        power = payload.getNextNbits(1).toInteger();
        if(addressed == 0)
        {
            ne_lon = payload.getNextNbits(18).toSignedInt() * 0.0001f / 60;
            ne_lat = payload.getNextNbits(17).toSignedInt() * 0.0001f / 60;
            sw_lon = payload.getNextNbits(18).toSignedInt() * 0.0001f / 60;
            sw_lat = payload.getNextNbits(17).toSignedInt();
            payload.getNextNbits(1);
            band_a = payload.getNextNbits(1).toInteger();
            band_b = payload.getNextNbits(1).toInteger();
            zonesize = payload.getNextNbits(3).toInteger();
        }else
        {
            dest1 =  payload.getNextNbits(30).toInteger();
            dest2 =  payload.getNextNbits(30).toInteger();
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
