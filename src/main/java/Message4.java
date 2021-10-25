/*
    +---------+-----+------------------+----------+----+--------------------------------+
    | Field   | Len | Description      | Member   | T  | Units                          |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 0-5     | 6   | Message Type     | type     | u  | Constant: 4                    |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 6-7     | 2   | Repeat Indicator | repeat   | u  | As in Common Navigation Block  |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 8-37    | 30  | MMSI             | mmsi     | u  | 9 decimal digits               |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 38-51   | 14  | Year (UTC)       | year     | u  | UTC, 1-9999, 0 = N/A (default) |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 52-55   | 4   | Month (UTC)      | month    | u  | 1-12; 0 = N/A (default)        |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 56-60   | 5   | Day (UTC)        | day      | u  | 1-31; 0 = N/A (default)        |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 61-65   | 5   | Hour (UTC)       | hour     | u  | 0-23; 24 = N/A (default)       |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 66-71   | 6   | Minute (UTC)     | minute   | u  | 0-59; 60 = N/A (default)       |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 72-77   | 6   | Second (UTC)     | second   | u  | 0-59; 60 = N/A (default)       |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 78-78   | 1   | Fix quality      | accuracy | b  | As in Common Navigation Block  |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 79-106  | 28  | Longitude        | lon      | I4 | As in Common Navigation Block  |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 107-133 | 27  | Latitude         | lat      | I4 | As in Common Navigation Block  |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 134-137 | 4   | Type of EPFD     | epfd     | e  | See "EPFD Fix Types"           |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 138-147 | 10  | Spare            |          | x  | Not used                       |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 148-148 | 1   | RAIM flag        | raim     | b  | As for common navigation block |
    +---------+-----+------------------+----------+----+--------------------------------+
    | 149-167 | 19  | SOTDMA state     | radio    | u  | As in same bits for Type 1     |
    +---------+-----+------------------+----------+----+--------------------------------+
 */
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
    private String epfd;
    private int raim;
    private int radio;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        year = payload.getNextNbits(14).toInteger();
        month = payload.getNextNbits(4).toInteger();
        day = payload.getNextNbits(5).toInteger();
        hour = payload.getNextNbits(5).toInteger();
        minute = payload.getNextNbits(6).toInteger();
        second = payload.getNextNbits(6).toInteger();
        acuraccy = payload.getNextNbits(1).toInteger();
        longitude = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
        epfd = Types.getType(payload.getNextNbits(4).toInteger(), Types.epfdTypes);
        payload.getNextNbits(10); //sin usar
        raim = payload.getNextNbits(1).toInteger();
        radio = payload.getNextNbits(19).toInteger();
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
        System.out.printf("EPFD: %s\n", epfd);
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
    public String getEpfd() {
        return epfd;
    }
    public void setEpfd(String epfd) {
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
