public class Message21 extends Message{
    private int aid_type;
    private String name;
    private int accuracy;
    private float longitude;
    private float latitude;
    private int to_bow;
    private int to_stern;
    private int to_port;
    private int to_starboard;
    private int epfd;
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
        aid_type =  payload.getData().getNbits(5).toInteger();
        name =  payload.getData().getNbits(120).toSixBitAscii();
        accuracy =  payload.getData().getNbits(1).toInteger();
        longitude = payload.getData().getNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getData().getNbits(27).toSignedInt() * 0.0001f / 60;
        to_bow = payload.getData().getNbits(9).toInteger();
        to_stern = payload.getData().getNbits(9).toInteger();
        to_port = payload.getData().getNbits(6).toInteger();
        to_starboard = payload.getData().getNbits(6).toInteger();
        epfd = payload.getData().getNbits(4).toInteger();
        second = payload.getData().getNbits(6).toInteger();
        off_position = payload.getData().getNbits(1).toInteger();
        payload.getData().getNbits(8); //sin interpretar
        raim = payload.getData().getNbits(1).toInteger();
        virtual_aid = payload.getData().getNbits(1).toInteger();
        assigned = payload.getData().getNbits(1).toInteger();
        payload.getData().getNbits(1); //sin usar
        name_extension = payload.getData().getNbits(payload.size()).toSixBitAscii();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Tipo de AID (Ayuda a la navegación): %s\n", Types.navaidTypes[aid_type]);
        System.out.printf("Nombre: %s\n", name);
        System.out.printf("Precisión de posición: %s\n", Types.possitionAccuracy[accuracy]);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Longitud: %f\n", latitude);
        System.out.printf("To bow: %d\n", to_bow);
        System.out.printf("To stern: %d\n", to_stern);
        System.out.printf("To port: %d\n", to_port);
        System.out.printf("To starboard: %d\n", to_starboard);
        System.out.printf("Time stamp: %d\n", second);
        System.out.printf("EPFD: %s\n", Types.epfdTypes[epfd]);
        System.out.printf("RAIM: %d\n", raim);
        System.out.printf("Asignado: %d\n", assigned);
    }
    public int getAid_type() {
        return aid_type;
    }
    public void setAid_type(int aid_type) {
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
    public int getEpfd() {
        return epfd;
    }
    public void setEpfd(int epfd) {
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
