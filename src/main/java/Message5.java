public class Message5 extends Message {
    private int ais_version;
    private int imo;
    private String callsign;
    private String shipname;
    private String shiptype;
    private int to_bow;
    private int to_stern;
    private int to_port;
    private int to_starboard;
    private int epfd;
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
        ais_version = payload.getData().getNbits(2).toInteger();
        imo = payload.getData().getNbits(30).toInteger();
        callsign = payload.getData().getNbits(42).toSixBitAscii();
        shipname = payload.getData().getNbits(120).toSixBitAscii();
        int stype = payload.getData().getNbits(8).toInteger();
        if(stype > 99)
        {
            stype = 0;
        }
        shiptype = Types.shipTypes[stype];
        to_bow = payload.getData().getNbits(9).toInteger();
        to_stern = payload.getData().getNbits(9).toInteger();
        to_port = payload.getData().getNbits(6).toInteger();
        to_starboard = payload.getData().getNbits(6).toInteger();
        epfd = payload.getData().getNbits(4).toInteger();
        month = payload.getData().getNbits(4).toInteger();
        day = payload.getData().getNbits(5).toInteger();
        hour = payload.getData().getNbits(5).toInteger();
        minute = payload.getData().getNbits(6).toInteger();
        draught =  payload.getData().getNbits(8).toInteger();
        destination = payload.getData().getNbits(120).toSixBitAscii();
        dte = payload.getData().getNbits(1).toInteger();
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
        System.out.printf("EPFD: %s\n", Types.epfdTypes[epfd]);
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
    public int getEpfd() {
        return epfd;
    }
    public void setEpfd(int epfd) {
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
}
