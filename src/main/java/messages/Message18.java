/*
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | Field   | Len | Description        | Member   | T  | Units                                                                                                                    |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 0-5     | 6   | Message Type       | type     | u  | Constant: 18                                                                                                             |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 6-7     | 2   | Repeat Indicator   | repeat   | u  | As in Common Navigation Block                                                                                            |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 8-37    | 30  | MMSI               | mmsi     | u  | 9 decimal digits                                                                                                         |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 38-45   | 8   | Regional Reserved  | reserved | x  | Not used                                                                                                                 |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 46-55   | 10  | Speed Over Ground  | speed    | U1 | As in common navigation block                                                                                            |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 56-56   | 1   | Position Accuracy  | accuracy | b  | See below                                                                                                                |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 57-84   | 28  | Longitude          | lon      | I4 | Minutes/10000 (as in CNB)                                                                                                |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 85-111  | 27  | Latitude           | lat      | I4 | Minutes/10000 (as in CNB)                                                                                                |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 112-123 | 12  | Course Over Ground | course   | U1 | 0.1 degrees from true north                                                                                              |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 124-132 | 9   | True Heading       | heading  | u  | 0 to 359 degrees, 511 = N/A                                                                                              |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 133-138 | 6   | Time Stamp         | second   | u  | Second of UTC timestamp.                                                                                                 |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 139-140 | 2   | Regional reserved  | regional | u  | Uninterpreted                                                                                                            |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 141-141 | 1   | CS Unit            | cs       | b  | 0=Class B SOTDMA unit 1=Class B CS (Carrier Sense) unit                                                                  |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 142-142 | 1   | Display flag       | display  | b  | 0=No visual display, 1=Has display, (Probably not reliable).                                                             |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 143-143 | 1   | DSC Flag           | dsc      | b  | If 1, unit is attached to a VHF voice radio with DSC capability.                                                         |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 144-144 | 1   | Band flag          | band     | b  | Base stations can command units to switch frequency. If this flag is 1, the unit can use any part of the marine channel. |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 145-145 | 1   | Message 22 flag    | msg22    | b  | If 1, unit can accept a channel assignment via Message Type 22.                                                          |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 146-146 | 1   | Assigned           | assigned | b  | Assigned-mode flag: 0 = autonomous mode (default), 1 = assigned mode.                                                    |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 147-147 | 1   | RAIM flag          | raim     | b  | As for common navigation block                                                                                           |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
    | 148-167 | 20  | Radio status       | radio    | u  | See [IALA] for details.                                                                                                  |
    +---------+-----+--------------------+----------+----+--------------------------------------------------------------------------------------------------------------------------+
 */
package messages;
import decoder.Payload;
public class Message18  extends Message {
    private int accuracy;
    private float speed;
    private float longitude;
    private float latitude;
    private float course;
    private int heading;
    private int second;
    private int raim;
    private int radio;
    private int cs;
    private int display;
    private int dsc;
    private int band;
    private int msg22;
    private int assigned;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(8); //sin usar
        speed = payload.getNextNbits(10).toInteger() / 10.0f;
        accuracy = payload.getNextNbits(1).toInteger();
        longitude =  payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
        course =  payload.getNextNbits(12).toInteger() * 0.1f;
        heading = payload.getNextNbits(9).toInteger();
        second = payload.getNextNbits(6).toInteger();
        payload.getNextNbits(2); //sin usar
        cs = payload.getNextNbits(1).toInteger();
        display = payload.getNextNbits(1).toInteger();
        dsc =  payload.getNextNbits(1).toInteger();
        band =  payload.getNextNbits(1).toInteger();
        msg22 =  payload.getNextNbits(1).toInteger();
        assigned =  payload.getNextNbits(1).toInteger();
        raim =  payload.getNextNbits(1).toInteger();
        payload.getNextNbits(1);
        radio = payload.getNextNbits(19).toInteger();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Velocidad: %f\n", speed);
        System.out.printf("Precisión de posición: %s\n", Types.possitionAccuracy[accuracy]);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Latitud: %f\n", latitude);
        System.out.printf("Curso: %f\n", course);
        System.out.printf("Heading: %d\n", heading);
        System.out.printf("Time stamp: %s\n", second);
        System.out.printf("Display flag: %d\n", display);
        System.out.printf("DSC flag: %d\n", dsc);
        System.out.printf("Band flag: %d\n", band);
        System.out.printf("Bandera mensaje 22: %d\n", msg22);
        System.out.printf("Asignado: %d\n", assigned);
        System.out.printf("Raim: %d\n", raim);
        System.out.printf("Estado de comunicación: %d\n", radio);
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
    public int getCs() {
        return cs;
    }
    public void setCs(int cs) {
        this.cs = cs;
    }
    public int getDisplay() {
        return display;
    }
    public void setDisplay(int display) {
        this.display = display;
    }
    public int getDsc() {
        return dsc;
    }
    public void setDsc(int dsc) {
        this.dsc = dsc;
    }
    public int getBand() {
        return band;
    }
    public void setBand(int band) {
        this.band = band;
    }
    public int getMsg22() {
        return msg22;
    }
    public void setMsg22(int msg22) {
        this.msg22 = msg22;
    }
    public int getAssigned() {
        return assigned;
    }
    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }
}
