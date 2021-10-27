package binary;

import binary.BinaryMessage;

public class BinaryMessage1_19 implements BinaryMessage {
    private int linkage;
    private String station;
    private float lon;
    private float lat;
    private int status;
    private int signal;
    private int hour;
    private int minute;
    private int nextsignal;
    @Override
    public void parse(Payload payload) {
        linkage = payload.getNextNbits(10).toInteger();
        station = payload.getNextNbits(120).toSixBitAscii();
        lon = payload.getNextNbits(25).toSignedInt() * 0.0001f / 60;
        lat = payload.getNextNbits(24).toSignedInt() * 0.0001f / 60;
        status = payload.getNextNbits(2).toInteger();
        signal = payload.getNextNbits(5).toInteger();
        hour = payload.getNextNbits(5).toInteger();
        minute = payload.getNextNbits(6).toInteger();
        nextsignal = payload.getNextNbits(5).toInteger();
    }
}
