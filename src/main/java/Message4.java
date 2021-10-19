public class Message4 extends Message {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int acuraccy;
    private float longitude;
    private float latitude ;
    private int epfd;
    private int raim;
    private int radio;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        year = payload.getData().getNbits(14).toInteger();
        month = payload.getData().getNbits(4).toInteger();
        day = payload.getData().getNbits(5).toInteger();
        hour = payload.getData().getNbits(5).toInteger();
        minute = payload.getData().getNbits(6).toInteger();
        second = payload.getData().getNbits(6).toInteger();
        acuraccy = payload.getData().getNbits(1).toInteger();
        longitude = payload.getData().getNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getData().getNbits(27).toSignedInt() * 0.0001f / 60;
        epfd = payload.getData().getNbits(4).toInteger();
        payload.getData().getNbits(10); //sin usar
        raim = payload.getData().getNbits(1).toInteger();
        radio = payload.getData().getNbits(19).toInteger();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Año: %d\n", year);
        System.out.printf("Mes: %d\n", month);
        System.out.printf("Día: %d\n", day);
        System.out.printf("Minuto: %d\n", minute);
        System.out.printf("Segundo: %d\n", second);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Latitud: %f\n", latitude);
        System.out.printf("EPFD: %s\n", Types.epfdTypes[epfd]);
        System.out.printf("RAIM: %d\n", raim);
        System.out.printf("Estado de comunicación: %d\n", radio);
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
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
    public int getSecond() {
        return second;
    }
    public void setSecond(int second) {
        this.second = second;
    }
    public int getAcuraccy() {
        return acuraccy;
    }
    public void setAcuraccy(int acuraccy) {
        this.acuraccy = acuraccy;
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
    public int getRadio() {
        return radio;
    }
    public void setRadio(int radio) {
        this.radio = radio;
    }
}
