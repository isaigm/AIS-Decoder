public class Message1_2_3  extends Message {
    private String status;
    private int turn;
    private String accuracy;
    private float speed;
    private float longitude;
    private float latitude;
    private float course;
    private int heading;
    private int second;
    private String maneuver;
    private int raim;
    private int radio;
    @Override
    public void parse(Payload payload) throws NMEAMessageException {
        if(payload.size() != 168)
        {
            throw new NMEAMessageException(String.format("Mensaje tipo 1, 2 o 3 de longitud errónea, se recibieron %d bits", payload.size()));
        }
        super.parse(payload);
        int st = payload.getData().getNbits(4).toInteger();
        if(st >= Types.navigationStatus.length)
        {
            status = Types.navigationStatus[0];
        }else
        {
            status = Types.navigationStatus[st];
        }
        turn = payload.getData().getNbits(8).toSignedInt();
        speed = payload.getData().getNbits(10).toInteger() / 10.0f;
        int acc = payload.getData().getNbits(1).toInteger();
        if(acc >= Types.possitionAccuracy.length)
        {
            accuracy = Types.possitionAccuracy[0];
        }else
        {
            accuracy = Types.possitionAccuracy[acc];
        }
        longitude = payload.getData().getNbits(28).toSignedInt() * 0.0001f / 60;;
        latitude = payload.getData().getNbits(27).toSignedInt() * 0.0001f / 60;;
        course = payload.getData().getNbits(12).toInteger() * 0.1f;
        heading = payload.getData().getNbits(9).toInteger();
        second = payload.getData().getNbits(6).toInteger();
        int mnv = payload.getData().getNbits(2).toInteger();
        if(mnv >= Types.maneuverIndicators.length){
            maneuver = Types.maneuverIndicators[0];
        }else
        {
            maneuver = Types.maneuverIndicators[mnv];
        }
        payload.getData().getNbits(3); //sin usar
        raim = payload.getData().getNbits(1).toInteger();
        radio = payload.getData().getNbits(19).toInteger();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Estado de navegacion: %s\n", status);
        System.out.printf("Velocidad de giro: %d\n", turn);
        System.out.printf("Velocidad: %f\n", speed);
        System.out.printf("Precisión de posición: %s\n", accuracy);
        System.out.printf("Latitud: %f\n", latitude);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Curso: %f\n", course);
        System.out.printf("Heading: %d\n", heading);
        System.out.printf("Time stamp: %s\n", second);
        System.out.printf("Maniobra: %s\n", maneuver);
        System.out.printf("Raim: %d\n", raim);
        System.out.printf("Estado de comunicación: %d\n", radio);
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public String getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
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
    public String getManeuver() {
        return maneuver;
    }
    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }
    public int getRaim() {
        return raim;
    }
    public void setRaim(int raim) {
        this.raim = raim;
    }
    public int getRadio() {
        return radio;
    }
    public void setRadio(int radio) {
        this.radio = radio;
    }
}
