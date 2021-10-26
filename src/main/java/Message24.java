/*
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | Field   | Len | Description            | Member          | T | Units                         |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 0-5     | 6   | Message Type           | type            | u | Constant: 24                  |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 6-7     | 2   | Repeat Indicator       | repeat          | u | As in CNB                     |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 8-37    | 30  | MMSI                   | mmsi            | u | 9 digits                      |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 38-39   | 2   | Part Number            | partno          | u | 0-1                           |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 40-159  | 120 | Vessel Name            | shipname        | t | (Part A) 20 sixbit chars      |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 160-167 | 8   | Spare                  |                 | x | (Part A) Not used             |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 40-47   | 8   | Ship Type              | shiptype        | e | (Part B) See "Ship Types"     |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 48-65   | 18  | Vendor ID              | vendorid        | t | (Part B) 3 six-bit chars      |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 66-69   | 4   | Unit Model Code        | model           | u | (Part B)                      |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 70-89   | 20  | Serial Number          | serial          | u | (Part B)                      |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 90-131  | 42  | Call Sign              | callsign        | t | (Part B) As in Message Type 5 |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 132-140 | 9   | Dimension to Bow       | to_bow          | u | (Part B) Meters               |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 141-149 | 9   | Dimension to Stern     | to_stern        | u | (Part B) Meters               |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 150-155 | 6   | Dimension to Port      | to_port         | u | (Part B) Meters               |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 156-161 | 6   | Dimension to Starboard | to_starboard    | u | (Part B) Meters               |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 132-161 | 30  | Mothership MMSI        | mothership_mmsi | u | (Part B) See below            |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
    | 162-167 | 6   | Spare                  |                 | x | (Part B) Not used             |
    +---------+-----+------------------------+-----------------+---+-------------------------------+
 */
public class Message24 extends Message {
    private int partno;
    private String shipname;
    private String shiptype;
    private int model;
    private int serial;
    private String vendorid;
    private String callsign;
    private int to_bow;
    private int to_stern;
    private int to_port;
    private int to_starboard;
    private int mothership_mmsi;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        partno = payload.getNextNbits(2).toInteger();
        if(partno == 0)
        {
            shipname = payload.getNextNbits(120).toSixBitAscii();
        }else if(partno == 1)
        {
            shiptype = Types.getType(payload.getNextNbits(8).toInteger(), Types.shipTypes);
            vendorid = payload.getNextNbits(18).toSixBitAscii();
            model = payload.getNextNbits(4).toInteger();
            serial = payload.getNextNbits(20).toInteger();
            callsign = payload.getNextNbits(42).toSixBitAscii();
            to_bow = payload.getNextNbits(9).toInteger();
            to_stern = payload.getNextNbits(9).toInteger();
            to_port = payload.getNextNbits(6).toInteger();
            to_starboard = payload.getNextNbits(6).toInteger();
            mothership_mmsi = payload.getNextNbits(30).toInteger();
        }else throw new NMEAMessageException("Mensaje tipo 24: número de parte incorrecto");
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Part number: %d\n", partno);
        if(partno == 0)
        {
            System.out.printf("Nombre del navío: %s\n", shipname);
        }else if(partno == 1)
        {
            System.out.printf("Tipo de navío: %s\n", shiptype);
            System.out.printf("Vendorid: %s\n", vendorid);
            System.out.printf("Callsign: %s\n", callsign);
            System.out.printf("To bow: %d\n", to_bow);
            System.out.printf("To stern: %d\n", to_stern);
            System.out.printf("To port: %d\n", to_port);
            System.out.printf("To starboard: %d\n", to_starboard);
        }
    }
    public int getPartno() {
        return partno;
    }
    public void setPartno(int partno) {
        this.partno = partno;
    }
    public String getShipname() {
        return shipname;
    }
    public void setShipname(String shipname) {
        this.shipname = shipname;
    }
    public String getShiptype() {
        return shiptype;
    }
    public void setShiptype(String shiptype) {
        this.shiptype = shiptype;
    }
    public String getVendorid() {
        return vendorid;
    }
    public void setVendorid(String vendorid) {
        this.vendorid = vendorid;
    }
    public String getCallsign() {
        return callsign;
    }
    public void setCallsign(String callsign) {
        this.callsign = callsign;
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
}
