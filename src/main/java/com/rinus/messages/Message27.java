/*
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | Field | Len | Description          | Member   | T  | Units                                                           |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 0-5   | 6   | Message Type         | type     | u  | Constant: 27                                                    |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 6-7   | 2   | Repeat Indicator     | repeat   | u  | As in CNB                                                       |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 8-37  | 30  | MMSI                 | mmsi     | u  | 9 decimal digits                                                |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 38-38 | 1   | Position Accuracy    | accuracy | u  | See Common Navigation Block                                     |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 39-39 | 1   | RAIM flag            | raim     | u  | See Common Navigation Block                                     |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 40-43 | 4   | Navigation Status    | status   | u  | See Common Navigation Block                                     |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 44-61 | 18  | Longitude            | lon      | I4 | minutes/10 East positive, West negative 181000 = N/A (default)  |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 62-78 | 17  | Latitude             | lat      | I4 | minutes/10 North positive, South negative 91000 = N/A (default) |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 79-84 | 6   | Speed Over Ground    | speed    | u  | Knots (0-62); 63 = N/A (default)                                |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 85-93 | 9   | Course Over Ground   | course   | u  | 0 to 359 degrees, 511 = not available.                          |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 94-94 | 1   | GNSS Position status | gnss     | u  | 0 = current GNSS position 1 = not GNSS position (default)       |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
    | 95-95 | 1   | Spare                |          | x  | Not used                                                        |
    +-------+-----+----------------------+----------+----+-----------------------------------------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.messages.Message;

public class Message27 extends Message {
    private String accuracy;
    private int raim;
    private String status;
    private float longitude;
    private float latitude;
    private float speed;
    private int course;
    private int gnss;
    private int radio;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        accuracy = Types.getType(payload.getNextNbits(1).toInteger(), Types.possitionAccuracy);
        raim = payload.getNextNbits(1).toInteger();
        status = Types.getType(payload.getNextNbits(4).toInteger(), Types.navigationStatus);
        longitude = payload.getNextNbits(18).toSignedInt() * 0.0001f / 60;
        latitude = payload.getNextNbits(17).toSignedInt() * 0.0001f / 60;
        speed =  payload.getNextNbits(6).toInteger() / 10.0f;
        course = payload.getNextNbits(9).toInteger();
        gnss =  payload.getNextNbits(1).toInteger();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Estado de navegacion: %s\n", status);
        System.out.printf("Velocidad: %f\n", speed);
        System.out.printf("Precisión de posición: %s\n", accuracy);
        System.out.printf("Latitud: %f\n", latitude);
        System.out.printf("Longitud: %f\n", longitude);
        System.out.printf("Curso: %d\n", course);
        System.out.printf("Raim: %d\n", raim);
        System.out.printf("Estado de comunicación: %d\n", radio);
    }
    public String getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }
    public int getRaim() {
        return raim;
    }
    public void setRaim(int raim) {
        this.raim = raim;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
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
