package com.rinus.binary;
import com.rinus.decoder.Payload;

public class BinaryMessage200_40 implements BinaryMessage {
    private float lon;
    private float lat;
    private int form;
    private int facing;
    private int direction;
    private int status;
    @Override
    public void parse(Payload payload) {
        lon = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
        lat = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
        form = payload.getNextNbits(4).toInteger();
        facing = payload.getNextNbits(9).toInteger();
        direction = payload.getNextNbits(3).toInteger();
        status = payload.getNextNbits(30).toInteger();
    }
}
