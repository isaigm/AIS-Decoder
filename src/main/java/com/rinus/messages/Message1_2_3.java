/*
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | Field   | Len | Description              | Member   | T  | Units                                           |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 0-5     | 6   | Message Type             | type     | u  | Constant: 1-3                                   |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 6-7     | 2   | Repeat Indicator         | repeat   | u  | Message repeat count                            |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 8-37    | 30  | MMSI                     | mmsi     | u  | 9 decimal digits                                |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 38-41   | 4   | Navigation Status        | status   | e  | See "Navigation Status"""                       |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 42-49   | 8   | Rate of Turn (ROT)       | turn     | I3 | See below                                       |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 50-59   | 10  | Speed Over Ground (SOG)  | speed    | U1 | See below                                       |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 60-60   | 1   | Position Accuracy        | accuracy | b  | See below                                       |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 61-88   | 28  | Longitude                | lon      | I4 | Minutes/10000 (see below)                       |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 89-115  | 27  | Latitude                 | lat      | I4 | Minutes/10000 (see below)                       |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 116-127 | 12  | Course Over Ground (COG) | course   | U1 | Relative to true north, to 0.1 degree precision |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 128-136 | 9   | True Heading (HDG)       | heading  | u  | 0 to 359 degrees, 511 = not available.          |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 137-142 | 6   | Time Stamp               | second   | u  | Second of UTC timestamp                         |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 143-144 | 2   | Maneuver Indicator       | maneuver | e  | See "Maneuver Indicator"""                      |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 145-147 | 3   | Spare                    |          | x  | Not used                                        |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 148-148 | 1   | RAIM flag                | raim     | b  | See below                                       |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
    | 149-167 | 19  | Radio status             | radio    | u  | See below                                       |
    +---------+-----+--------------------------+----------+----+-------------------------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.messages.Message;

public class Message1_2_3 extends Message {
    private String status;
    private int turn;
    private String turn_description;
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
            throw new NMEAMessageException(String.format("Mensaje tipo 1, 2 o 3 de longitud errónea, se recibieron %d bits, la longitud debe ser de 168 bits", payload.size()));
        }
        super.parse(payload);
        int st = payload.getNextNbits(4).toInteger();
        turn = payload.getNextNbits(8).toSignedInt();
        speed = payload.getNextNbits(10).toInteger() / 10.0f;
        int acc = payload.getNextNbits(1).toInteger();
        longitude = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
        latitude = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
        course = payload.getNextNbits(12).toInteger() * 0.1f;
        heading = payload.getNextNbits(9).toInteger();
        second = payload.getNextNbits(6).toInteger();
        int mnv = payload.getNextNbits(2).toInteger();
        payload.getNextNbits(3); //sin usar
        raim = payload.getNextNbits(1).toInteger();
        radio = payload.getNextNbits(19).toInteger();
        status = Types.getType(st,  Types.navigationStatus);
        accuracy = Types.getType(acc, Types.possitionAccuracy);
        maneuver = Types.getType(mnv, Types.maneuverIndicators);
        if(turn == 0)
        {
            turn_description = "No está girando";
        }else if(turn >= 1 && turn <= 126)
        {
            turn_description = "Girando a la derecha a 708 grados por minuto o más";
        }else if(turn >= -126 && turn < 1)
        {
            turn_description = "Girando a la izquierda a 708 grados por minuto o más";
        }else if(turn == 127)
        {
            turn_description = "Girando a la derecha a más de 5 grados cada 30 segundos";
        }else if(turn == -127)
        {
            turn_description = "Girando a la izquierda a más de 5 grados cada 30 segundos";
        }else if(turn == 128)
        {
            turn_description = "Sin información disponible";
        }
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
