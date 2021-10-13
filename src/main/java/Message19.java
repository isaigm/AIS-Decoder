public class Message19 extends Message{
    private float speed;
    private int reserved;
    private int accuracy;
    private float longitude;
    private float latitude;
    private float course;
    private int heading;
    private int second;
    private String shipname;
    private int shiptype;
    private int to_bow;
    private int to_stern;
    private int to_port;
    private int to_starboard;
    private int epfd;
    private int raim;
    private int dte;
    private int assigned;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        reserved = payload.getData().getNbits(8).toInteger();
        speed = payload.getData().getNbits(10).toInteger() / 10.0f;
        accuracy = payload.getData().getNbits(1).toInteger();
        longitude = payload.getData().getNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getData().getNbits(27).toSignedInt() * 0.0001f / 60;
        course = payload.getData().getNbits(12).toInteger() * 0.1f;
        heading = payload.getData().getNbits(9).toInteger();
        second = payload.getData().getNbits(6).toInteger();
        payload.getData().getNbits(4); //sin interpretar
        shipname = payload.getData().getNbits(120).toSixBitAscii();
        shiptype = payload.getData().getNbits(8).toInteger();
        to_bow = payload.getData().getNbits(9).toInteger();
        to_stern = payload.getData().getNbits(9).toInteger();
        to_port = payload.getData().getNbits(6).toInteger();
        to_starboard = payload.getData().getNbits(6).toInteger();
        epfd = payload.getData().getNbits(4).toInteger();
        raim = payload.getData().getNbits(1).toInteger();
        dte = payload.getData().getNbits(1).toInteger();
        assigned = payload.getData().getNbits(1).toInteger();
        payload.getData().getNbits(4); //sin usar
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Velocidad: %f\n", speed);
        System.out.printf("Precisión de posición: %s\n", Types.possitionAccuracy[accuracy]);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Longitud: %f\n", latitude);
        System.out.printf("Curso: %f\n", course);
        System.out.printf("Heading: %d\n", heading);
        System.out.printf("Time stamp: %d\n", second);
        System.out.printf("Nombre del navío: %s\n", shipname);
        System.out.printf("Tipo de navío: %d\n", shiptype);
        System.out.printf("EPFD: %s\n", Types.epfdTypes[epfd]);
    }
    public int getReserved() {
        return reserved;
    }
    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public int getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
    public float getLongitude() {
        return longitude;
    }
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    public float getCourse() {
        return course;
    }
    public void setCourse(float course) {
        this.course = course;
    }
    public int getHeading() {
        return heading;
    }
    public void setHeading(int heading) {
        this.heading = heading;
    }
    public int getSecond() {
        return second;
    }
    public void setSecond(int second) {
        this.second = second;
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
    public int getEpfd() {
        return epfd;
    }
    public void setEpfd(int epfd) {
        this.epfd = epfd;
    }
    public int getRaim() {
        return raim;
    }
    public void setRaim(int raim) {
        this.raim = raim;
    }
    public int getDte() {
        return dte;
    }
    public void setDte(int dte) {
        this.dte = dte;
    }
    public int getAssigned() {
        return assigned;
    }
    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }
}
