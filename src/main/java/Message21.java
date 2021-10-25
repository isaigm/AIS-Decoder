/*
    +----------+-----+------------------------+--------------+----+---------------------------+
    | Field    | Len | Description            | Member       | T  | Units                     |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 0-5      | 6   | Message Type           | type         | u  | Constant: 21              |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 6-7      | 2   | Repeat Indicator       | repeat       | u  | As in CNB                 |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 8-37     | 30  | MMSI                   | mmsi         | u  | 9 digits                  |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 38-42    | 5   | Aid type               | aid_type     | e  | See "Navaid Types"        |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 43-162 1 | 120 | Name                   | name         | t  | Name in sixbit chars      |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 163-163  | 1   | Position Accuracy      | accuracy     | b  | As in CNB                 |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 164-191  | 28  | Longitude              | lon          | I4 | Minutes/10000 (as in CNB) |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 192-218  | 27  | Latitude               | lat          | I4 | Minutes/10000 (as in CNB) |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 219-227  | 9   | Dimension to Bow       | to_bow       | u  | Meters                    |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 228-236  | 9   | Dimension to Stern     | to_stern     | u  | Meters                    |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 237-242  | 6   | Dimension to Port      | to_port      | u  | Meters                    |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 243-248  | 6   | Dimension to Starboard | to_starboard | u  | Meters                    |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 249-252  | 4   | Type of EPFD           | epfd         | e  | As in Message Type 4      |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 253-258  | 6   | UTC second             | second       | u  | As in Message Types 1-3   |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 259-259  | 1   | Off-Position Indicator | off_position | b  | See Below                 |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 260-267  | 8   | Regional reserved      | regional     | u  | Uninterpreted             |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 268-268  | 1   | RAIM flag              | raim         | b  | As in CNB                 |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 269-269  | 1   | Virtual-aid flag       | virtual_aid  | b  | See Below                 |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 270-270  | 1   | Assigned-mode flag     | assigned     | b  | See [IALA] for details    |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 271-271  | 1   | Spare                  |              | x  | Not used                  |
    +----------+-----+------------------------+--------------+----+---------------------------+
    | 272-360  | 88  | Name Extension         |              | t  | See Below                 |
    +----------+-----+------------------------+--------------+----+---------------------------+
 */
public class Message21 extends Message {
    private String aid_type;
    private String name;
    private int accuracy;
    private float longitude;
    private float latitude;
    private int to_bow;
    private int to_stern;
    private int to_port;
    private int to_starboard;
    private String epfd;
    private int second;
    private int off_position;
    private int raim;
    private int virtual_aid;
    private int assigned;
    private String name_extension;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        aid_type = Types.getType(payload.getNextNbits(5).toInteger(), Types.navaidTypes);
        name =  payload.getNextNbits(120).toSixBitAscii();
        accuracy =  payload.getNextNbits(1).toInteger();
        longitude = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
        to_bow = payload.getNextNbits(9).toInteger();
        to_stern = payload.getNextNbits(9).toInteger();
        to_port = payload.getNextNbits(6).toInteger();
        to_starboard = payload.getNextNbits(6).toInteger();
        epfd = Types.getType(payload.getNextNbits(4).toInteger(), Types.epfdTypes);
        second = payload.getNextNbits(6).toInteger();
        off_position = payload.getNextNbits(1).toInteger();
        payload.getNextNbits(8); //sin interpretar
        raim = payload.getNextNbits(1).toInteger();
        virtual_aid = payload.getNextNbits(1).toInteger();
        assigned = payload.getNextNbits(1).toInteger();
        payload.getNextNbits(1); //sin usar
        name_extension = payload.getLastBits().toSixBitAscii();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Tipo de AID (Ayuda a la navegación): %s\n", aid_type);
        System.out.printf("Nombre: %s\n", name);
        System.out.printf("Precisión de posición: %s\n", Types.possitionAccuracy[accuracy]);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Longitud: %f\n", latitude);
        System.out.printf("To bow: %d\n", to_bow);
        System.out.printf("To stern: %d\n", to_stern);
        System.out.printf("To port: %d\n", to_port);
        System.out.printf("To starboard: %d\n", to_starboard);
        System.out.printf("Time stamp: %d\n", second);
        System.out.printf("EPFD: %s\n", epfd);
        System.out.printf("RAIM: %d\n", raim);
        System.out.printf("Asignado: %d\n", assigned);
    }
    public String getAid_type() {
        return aid_type;
    }
    public void setAid_type(String aid_type) {
        this.aid_type = aid_type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getEpfd() {
        return epfd;
    }
    public void setEpfd(String epfd) {
        this.epfd = epfd;
    }
    public int getSecond() {
        return second;
    }
    public void setSecond(int second) {
        this.second = second;
    }
    public int getOff_position() {
        return off_position;
    }
    public void setOff_position(int off_position) {
        this.off_position = off_position;
    }
    public int getRaim() {
        return raim;
    }
    public void setRaim(int raim) {
        this.raim = raim;
    }
    public int getVirtual_aid() {
        return virtual_aid;
    }
    public void setVirtual_aid(int virtual_aid) {
        this.virtual_aid = virtual_aid;
    }
    public int getAssigned() {
        return assigned;
    }
    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }
    public String getName_extension() {
        return name_extension;
    }
    public void setName_extension(String name_extension) {
        this.name_extension = name_extension;
    }
}
