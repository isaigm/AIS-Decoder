/*
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | Field   | Len | Description            | Member/Type  | T  | Encoding                                      |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 0-5     | 6   | Message Type           | type         | u  | Constant: 5                                   |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 6-7     | 2   | Repeat Indicator       | repeat       | u  | Message repeat count                          |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 8-37    | 30  | MMSI                   | mmsi         | u  | 9 digits                                      |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 38-39   | 2   | AIS Version            | ais_version  | u  | 0=[ITU1371], 1-3 = future editions            |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 40-69   | 30  | IMO Number             | imo          | u  | IMO ship ID number                            |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 70-111  | 42  | Call Sign              | callsign     | t  | 7 six-bit characters                          |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 112-231 | 120 | Vessel Name            | shipname     | t  | 20 six-bit characters                         |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 232-239 | 8   | Ship Type              | shiptype     | e  | See "Codes for Ship Type"                     |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 240-248 | 9   | Dimension to Bow       | to_bow       | u  | Meters                                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 249-257 | 9   | Dimension to Stern     | to_stern     | u  | Meters                                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 258-263 | 6   | Dimension to Port      | to_port      | u  | Meters                                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 264-269 | 6   | Dimension to Starboard | to_starboard | u  | Meters                                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 270-273 | 4   | Position Fix Type      | epfd         | e  | See "EPFD Fix Types"                          |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 274-277 | 4   | ETA month (UTC)        | month        | u  | 1-12, 0=N/A (default)                         |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 278-282 | 5   | ETA day (UTC)          | day          | u  | 1-31, 0=N/A (default)                         |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 283-287 | 5   | ETA hour (UTC)         | hour         | u  | 0-23, 24=N/A (default)                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 288-293 | 6   | ETA minute (UTC)       | minute       | u  | 0-59, 60=N/A (default)                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 294-301 | 8   | Draught                | draught      | U1 | Meters/10                                     |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 302-421 | 120 | Destination            | destination  | t  | 20 6-bit characters                           |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 422-422 | 1   | DTE                    | dte          | b  | 0=Data terminal ready, 1=Not ready (default). |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 423-423 | 1   | Spare                  |              | x  | Not used                                      |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.messages.Message;

public class Message5 extends Message {
    private int ais_version;
    private int imo;
    private String callsign;
    private String shipname;
    private String shiptype;
    private int to_bow; //proa
    private int to_stern; //popa
    private int to_port; //babor
    private int to_starboard; //estribor
    private String epfd;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int draught;
    private String destination;
    private int dte;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        ais_version = payload.getNextNbits(2).toInteger();
        imo = payload.getNextNbits(30).toInteger();
        callsign = payload.getNextNbits(42).toSixBitAscii();
        shipname = payload.getNextNbits(120).toSixBitAscii();
        shiptype = Types.getType(payload.getNextNbits(8).toInteger(), Types.shipTypes);
        to_bow = payload.getNextNbits(9).toInteger();
        to_stern = payload.getNextNbits(9).toInteger();
        to_port = payload.getNextNbits(6).toInteger();
        to_starboard = payload.getNextNbits(6).toInteger();
        epfd = Types.getType(payload.getNextNbits(4).toInteger(), Types.epfdTypes);
        month = payload.getNextNbits(4).toInteger();
        day = payload.getNextNbits(5).toInteger();
        hour = payload.getNextNbits(5).toInteger();
        minute = payload.getNextNbits(6).toInteger();
        draught =  payload.getNextNbits(8).toInteger();
        destination = payload.getNextNbits(120).toSixBitAscii();
        dte = payload.getNextNbits(1).toInteger();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Versión AIS: %d\n", ais_version);
        System.out.printf("IMO: %d\n", imo);
        System.out.printf("Callsign: %s\n", callsign);
        System.out.printf("Nombre del navío: %s\n", shipname);
        System.out.printf("Tipo de navío: %s\n", shiptype);
        System.out.printf("To bow: %d\n", to_bow);
        System.out.printf("To stern: %d\n", to_stern);
        System.out.printf("To port: %d\n", to_port);
        System.out.printf("To starboard: %d\n", to_starboard);
        System.out.printf("EPFD: %s\n", epfd);
        System.out.printf("Mes: %d\n", month);
        System.out.printf("Día: %d\n", day);
        System.out.printf("Hora: %d\n", hour);
        System.out.printf("Minuto: %d\n", minute);
        System.out.printf("Draught: %f\n", draught / 10.0f);
        System.out.printf("Destino: %s\n", destination);
    }
    public int getTo_bow() {
        return to_bow;
    }
    public void setTo_bow(int to_bow) {
        this.to_bow = to_bow;
    }
    public int getTo_stern() {
        return to_stern;
    }
    public void setTo_stern(int to_stern) {
        this.to_stern = to_stern;
    }
    public int getTo_port() {
        return to_port;
    }
    public void setTo_port(int to_port) {
        this.to_port = to_port;
    }
    public int getTo_starboard() {
        return to_starboard;
    }
    public void setTo_starboard(int to_starboard) {
        this.to_starboard = to_starboard;
    }
    public int getDraught() {
        return draught;
    }
    public void setDraught(int draught) {
        this.draught = draught;
    }
    public int getDte() {
        return dte;
    }
    public void setDte(int dte) {
        this.dte = dte;
    }
    public int getAis_version() {
        return ais_version;
    }
    public void setAis_version(int ais_version) {
        this.ais_version = ais_version;
    }
    public int getImo() {
        return imo;
    }
    public void setImo(int imo) {
        this.imo = imo;
    }
    public String getCallsign() {
        return callsign;
    }
    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }
    public String getShipname() {
        return shipname;
    }
    public void setShipname(String shipname) {
        this.shipname = shipname;
    }
    public String getEpfd() {
        return epfd;
    }
    public void setEpfd(String epfd) {
        this.epfd = epfd;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getShiptype() { return shiptype;}
}
