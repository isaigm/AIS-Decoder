package binary;

import binary.BinaryMessage;

public class BinaryMessage1_18 implements BinaryMessage {
    private int linkage;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String portname;
    private String destination;
    private float lon;
    private float lat;
    @Override
    public void parse(Payload payload) {
        linkage = payload.getNextNbits(10).toInteger();
        month = payload.getNextNbits(4).toInteger();
        day = payload.getNextNbits(5).toInteger();
        hour = payload.getNextNbits(5).toInteger();
        minute = payload.getNextNbits(6).toInteger();
        portname = payload.getNextNbits(120).toSixBitAscii();
        destination = payload.getNextNbits(30).toSixBitAscii();
        lon = payload.getNextNbits(25).toSignedInt() * 0.0001f / 60;
        lat = payload.getNextNbits(24).toSignedInt() * 0.0001f / 60;
    }
}
