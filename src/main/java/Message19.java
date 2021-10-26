/*
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | Field   | Len | Description            | Member       | T  | Units                                         |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 0-5     | 6   | Message Type           | type         | u  | Constant: 19                                  |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 6-7     | 2   | Repeat Indicator       | repeat       | u  | As in CNN                                     |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 8-37    | 30  | MMSI                   | mmsi         | u  | 9 digits                                      |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 38-45   | 8   | Regional Reserved      | reserved     | u  |                                               |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 46-55   | 10  | Speed Over Ground      | speed        | U1 | As in CNB.                                    |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 56-56   | 1   | Position Accuracy      | accuracy     | b  | As in CNB.                                    |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 57-84   | 28  | Longitude              | lon          | I4 | Minutes/10000 (as in CNB)                     |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 85-111  | 27  | Latitude               | lat          | I4 | Minutes/10000 (as in CNB)                     |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 112-123 | 12  | Course Over Ground     | course       | U1 | Relative to true north, units of 0.1 degrees  |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 124-132 | 9   | True Heading           | heading      | u  | 0 to 359 degrees, 511 = N/A                   |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 133-138 | 6   | Time Stamp             | second       | u  | Second of UTC timestamp.                      |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 139-142 | 4   | Regional reserved      | regional     | u  | Uninterpreted                                 |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 143-262 | 120 | Name                   | shipname     | s  | 20 6-bit characters                           |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 263-270 | 8   | Type of ship and cargo | shiptype     | u  | As in Message 5                               |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 271-279 | 9   | Dimension to Bow       | to_bow       | u  | Meters                                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 280-288 | 9   | Dimension to Stern     | to_stern     | u  | Meters                                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 289-294 | 6   | Dimension to Port      | to_port      | u  | Meters                                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 295-300 | 6   | Dimension to Starboard | to_starboard | u  | Meters                                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 301-304 | 4   | Position Fix Type      | epfd         | e  | See "EPFD Fix Types"                          |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 305-305 | 1   | RAIM flag              | raim         | b  | As in CNB.                                    |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 306-306 | 1   | DTE                    | dte          | b  | 0=Data terminal ready, 1=Not ready (default). |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 307-307 | 1   | Assigned mode flag     | assigned     | u  | See [IALA] for details                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
    | 308-311 | 4   | Spare                  |              | x  | Unused, should be zero                        |
    +---------+-----+------------------------+--------------+----+-----------------------------------------------+
 */

public class Message19 extends Message {
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
        reserved = payload.getNextNbits(8).toInteger();
        speed = payload.getNextNbits(10).toInteger() / 10.0f;
        accuracy = payload.getNextNbits(1).toInteger();
        longitude = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
        course = payload.getNextNbits(12).toInteger() * 0.1f;
        heading = payload.getNextNbits(9).toInteger();
        second = payload.getNextNbits(6).toInteger();
        payload.getNextNbits(4); //sin interpretar
        shipname = payload.getNextNbits(120).toSixBitAscii();
        shiptype = payload.getNextNbits(8).toInteger();
        to_bow = payload.getNextNbits(9).toInteger();
        to_stern = payload.getNextNbits(9).toInteger();
        to_port = payload.getNextNbits(6).toInteger();
        to_starboard = payload.getNextNbits(6).toInteger();
        epfd = payload.getNextNbits(4).toInteger();
        raim = payload.getNextNbits(1).toInteger();
        dte = payload.getNextNbits(1).toInteger();
        assigned = payload.getNextNbits(1).toInteger();
        payload.getNextNbits(4); //sin usar
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
