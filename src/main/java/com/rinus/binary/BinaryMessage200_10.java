package com.rinus.binary;
import com.rinus.decoder.Payload;

public class BinaryMessage200_10 implements BinaryMessage{
    private String vin;
    private int length;
    private int beam;
    private int shiptype;
    private int hazard;
    private int draught;
    private int loaded;
    private int speed_q;
    private int course_q;
    private int heading_q;
    @Override
    public void parse(Payload payload) {
        vin = payload.getNextNbits(48).toSixBitAscii();
        length = payload.getNextNbits(13).toInteger();
        beam = payload.getNextNbits(10).toInteger();
        shiptype = payload.getNextNbits(14).toInteger();
        hazard = payload.getNextNbits(3).toInteger();
        draught = payload.getNextNbits(11).toInteger();
        loaded = payload.getNextNbits(2).toInteger();
        speed_q = payload.getNextNbits(1).toInteger();
        course_q = payload.getNextNbits(1).toInteger();
        heading_q = payload.getNextNbits(1).toInteger();
    }
}
