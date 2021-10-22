public class Message24 extends Message {
    private int partno;
    private String shipname;
    private int shiptype;
    private String vendorid;
    private String callsign;
    private int to_bow;
    private int to_stern;
    private int to_port;
    private int to_starboard;
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
            shiptype = payload.getNextNbits(8).toInteger();
            vendorid = payload.getNextNbits(42).toSixBitAscii();
            callsign = payload.getNextNbits(42).toSixBitAscii();
            to_bow = payload.getNextNbits(9).toInteger();
            to_stern = payload.getNextNbits(9).toInteger();
            to_port = payload.getNextNbits(6).toInteger();
            to_starboard = payload.getNextNbits(6).toInteger();
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
            System.out.printf("Tipo de navío: %s\n", Types.shipTypes[shiptype]);
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
    public int getShiptype() {
        return shiptype;
    }
    public void setShiptype(int shiptype) {
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
