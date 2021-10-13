public class Message9 extends Message{
    private int altitude;
    private float speed;
    private int accuracy;
    private float longitude;
    private float latitude;
    private float course;
    private int second;
    private int dte;
    private int assigned;
    private int raim;
    private int radio;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        altitude = payload.getData().getNbits(12).toInteger();
        speed = payload.getData().getNbits(10).toInteger() / 10.0f;
        accuracy = payload.getData().getNbits(1).toInteger();
        longitude = payload.getData().getNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getData().getNbits(27).toSignedInt() * 0.0001f / 60;
        course = payload.getData().getNbits(12).toInteger() * 0.1f;
        second = payload.getData().getNbits(6).toInteger();
        payload.getData().getNbits(8);//sin usar
        dte = payload.getData().getNbits(1).toInteger();
        payload.getData().getNbits(3);//sin usar
        assigned = payload.getData().getNbits(1).toInteger();
        raim = payload.getData().getNbits(1).toInteger();
        radio = payload.getData().getNbits(20).toInteger();
    }
    @Override
    public void print(){
        super.print();
        System.out.printf("Altitud: %d\n", altitude);
        System.out.printf("Velocidad: %f\n", speed);
        System.out.printf("Precisión de posición: %s\n", Types.possitionAccuracy[accuracy]);
        System.out.printf("Latitud: %f\n", latitude);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Curso: %f\n", course);
        System.out.printf("Time stamp: %d\n", second);
        System.out.printf("DTE: %d\n", dte);
        System.out.printf("Asignado: %d\n", assigned);
        System.out.printf("Raim: %d\n", raim);
        System.out.printf("Estado de comunicación: %d\n", radio);
    }
    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
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

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
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
