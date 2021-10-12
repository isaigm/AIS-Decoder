public class Message1_2_3  extends  Message{
    private int status;
    private float turn;
    private int accuracy;
    private float speed;
    private float longitude;
    private float latitude;
    private float course;
    private int heading;
    private int second;
    private int maneuver;
    private int raim;
    private int radio;
    @Override
    public void parse(Payload payload) throws Exception {
        super.parse(payload);
        status = payload.getData().getNbits(4).toInteger();
        turn = (float) Math.pow(payload.getData().getNbits(8).toInteger() / 4.733f, 2);
        speed = payload.getData().getNbits(10).toInteger() / 10.0f;
        accuracy = payload.getData().getNbits(1).toInteger();
        longitude = payload.getData().getNbits(28).toSignedInt() * 0.0001f / 60;;
        latitude = payload.getData().getNbits(27).toSignedInt() * 0.0001f / 60;;
        course = payload.getData().getNbits(12).toInteger() * 0.1f;
        heading = payload.getData().getNbits(9).toInteger();
        second = payload.getData().getNbits(6).toInteger();
        maneuver = payload.getData().getNbits(2).toInteger();
        payload.getData().getNbits(3); //sin usar
        raim = payload.getData().getNbits(1).toInteger();
        radio = payload.getData().getNbits(19).toInteger();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Estado de navegacion: %s\n", Types.navigationStatus[status]);
        System.out.printf("Velocidad de giro: %f\n", turn);
        System.out.printf("Velocidad: %f\n", speed);
        System.out.printf("Precisión de posición: %s\n", Types.possitionAccuracy[accuracy]);
        System.out.printf("Latitud: %f\n", latitude);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Curso: %f\n", course);
        System.out.printf("Heading: %d\n", heading);
        System.out.printf("Time stamp: %s\n", second);
        System.out.printf("Maniobra: %s\n", Types.maneuverIndicators[maneuver]);
        System.out.printf("Raim: %d\n", raim);
        System.out.printf("Estado de comunicación: %d\n", radio);
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public float getTurn() {
        return turn;
    }
    public void setTurn(float turn) {
        this.turn = turn;
    }
    public int getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(int accuracy) {
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
    public int getManeuver() {
        return maneuver;
    }
    public void setManeuver(int maneuver) {
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
