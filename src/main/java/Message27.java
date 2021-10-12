public class Message27 extends Message {
    private int accuracy;
    private int raim;
    private int status;
    private float longitude;
    private float latitude;
    private float speed;
    private int course;
    private int gnss;
    private int radio;
    @Override
    public void parse(Payload payload) throws Exception
    {
        super.parse(payload);
        accuracy = payload.getData().getNbits(1).toInteger();
        status = payload.getData().getNbits(4).toInteger();
        longitude = payload.getData().getNbits(18).toSignedInt() * 0.0001f / 60;
        latitude = payload.getData().getNbits(17).toSignedInt() * 0.0001f / 60;
        speed =  payload.getData().getNbits(6).toInteger() / 10.0f;
        course = payload.getData().getNbits(9).toInteger();
        gnss =  payload.getData().getNbits(1).toInteger();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Estado de navegacion: %s\n", Types.navigationStatus[status]);
        System.out.printf("Velocidad: %f\n", speed);
        System.out.printf("Precisión de posición: %s\n", Types.possitionAccuracy[accuracy]);
        System.out.printf("Latitud: %f\n", latitude);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Curso: %d\n", course);
        System.out.printf("Raim: %d\n", raim);
        System.out.printf("Estado de comunicación: %d\n", radio);
    }
    public int getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
    public int getRaim() {
        return raim;
    }
    public void setRaim(int raim) {
        this.raim = raim;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
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
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getCourse() {
        return course;
    }
    public void setCourse(int course) {
        this.course = course;
    }
    public int getGnss() {
        return gnss;
    }
    public void setGnss(int gnss) {
        this.gnss = gnss;
    }
    public int getRadio() {
        return radio;
    }
    public void setRadio(int radio) {
        this.radio = radio;
    }
}
