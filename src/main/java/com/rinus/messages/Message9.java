/*
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | Field   | Len | Description        | Member   | T  | Encoding                                                   |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 0-5     | 6   | Message Type       | type     | u  | Constant: 9                                                |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 6-7     | 2   | Repeat Indicator   | repeat   | u  | As in Common Navigation Block                              |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 8-37    | 30  | MMSI               | mmsi     | u  | 9 decimal digits                                           |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 38-49   | 12  | Altitude           | alt      | u  | See below                                                  |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 50-59   | 10  | SOG                | speed    | u  | See below                                                  |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 60-60   | 1   | Position Accuracy  | accuracy | u  | See below                                                  |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 61-88   | 28  | Longitude          | lon      | I4 | Minutes/10000 (as in CNB)                                  |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 89-115  | 27  | Latitude           | lat      | I4 | Minutes/10000 (as in CNB)                                  |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 116-127 | 12  | Course Over Ground | course   | U1 | True bearing, 0.1 degree units                             |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 128-133 | 6   | Time Stamp         | second   | u  | UTC second.                                                |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 134-141 | 8   | Regional reserved  | regional | x  | Reserved                                                   |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 142-142 | 1   | DTE                | dte      | b  | 0=Data terminal ready, 1=Data terminal not ready (default) |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 143-145 | 3   | Spare              |          | x  | Not used                                                   |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 146-146 | 1   | Assigned           | assigned | b  | Assigned-mode flag                                         |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 147-147 | 1   | RAIM flag          | raim     | b  | As for common navigation block                             |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
    | 148-167 | 20  | Radio status       | radio    | u  | See [IALA] for details.                                    |
    +---------+-----+--------------------+----------+----+------------------------------------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;

public class Message9 extends Message {
    private int altitude;
    private int speed;
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
        altitude = payload.getNextNbits(12).toInteger();
        speed = payload.getNextNbits(10).toInteger();
        accuracy = payload.getNextNbits(1).toInteger();
        longitude = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
        course = payload.getNextNbits(12).toInteger() * 0.1f;
        second = payload.getNextNbits(6).toInteger();
        payload.getNextNbits(8);//sin usar
        dte = payload.getNextNbits(1).toInteger();
        payload.getNextNbits(3);//sin usar
        assigned = payload.getNextNbits(1).toInteger();
        raim = payload.getNextNbits(1).toInteger();
        radio = payload.getNextNbits(20).toInteger();
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
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
