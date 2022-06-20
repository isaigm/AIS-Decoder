/*
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | Field   | Len | Description      | Member       | T | Units                                |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 0-5     | 6   | Message Type     | type         | u | Unsigned Integer: 23                 |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 6-7     | 2   | Repeat Indicator | repeat       | u | As in Common Navigation Block        |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 8-37    | 30  | MMSI             | mmsi         | u | Unsigned Integer: 9 digits           |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 38-39   | 2   | Spare            |              | x | Not used                             |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 40-57   | 18  | NE Longitude     | ne_lon       | u | Same as broadcast type 22            |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 58-74   | 17  | NE Latitude      | ne_lat       | u | Same as broadcast type 22            |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 75-92   | 18  | SW Longitude     | sw_lon       | u | Same as broadcast type 22            |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 93-109  | 17  | SW Latitude      | sw_lat       | u | Same as broadcast type 22            |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 110-113 | 4   | Station Type     | station_type | e | See "Station Types"                  |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 114-121 | 8   | Ship Type        | ship_type    | e | See "Ship Types"                     |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 122-143 | 22  | Spare            |              | x | Not used                             |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 144-145 | 2   | Tx/Rx Mode       | txrx         | u | See "Transmit/Receive Modes"         |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 146-149 | 4   | Report Interval  | interval     | e | See "Station Intervals"              |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 150-153 | 4   | Quiet Time       | quiet        | u | 0 = none, 1-15 quiet time in minutes |
    +---------+-----+------------------+--------------+---+--------------------------------------+
    | 154-159 | 6   | Spare            |              | x | Not used                             |
    +---------+-----+------------------+--------------+---+--------------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.messages.Message;

public class Message23 extends Message {
    private float ne_lon;
    private float ne_lat;
    private float sw_lon;
    private float sw_lat;
    private String station_type;
    private String ship_type;
    private String txrx;
    private String interval;
    private int quiet;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        ne_lon = payload.getNextNbits(18).toSignedInt() / 600f;
        ne_lat = payload.getNextNbits(17).toSignedInt() / 600f;
        sw_lon = payload.getNextNbits(18).toSignedInt() / 600f;
        sw_lat = payload.getNextNbits(17).toSignedInt() / 600f;
        station_type = Types.getType(payload.getNextNbits(4).toInteger(), Types.stationTypes);
        ship_type = Types.getType(payload.getNextNbits(8).toInteger(), Types.shipTypes);
        payload.getNextNbits(22); //sin usar
        txrx = Types.getType(payload.getNextNbits(2).toInteger(), Types.transmitModes);
        interval = Types.getType(payload.getNextNbits(4).toInteger(), Types.intervals);
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
        System.out.printf("Modo Tx/Rx: %s\n", txrx);
    }
}
