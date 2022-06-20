package com.rinus.binary;
import com.rinus.decoder.Payload;
import java.util.ArrayList;

public class BinaryMessage1_32 implements BinaryMessage {
    private int month;
    private int day;
    private final ArrayList<Tidal> tidals = new ArrayList<>();
    @Override
    public void parse(Payload payload) {
        month = payload.getNextNbits(4).toInteger();
        day = payload.getNextNbits(5).toInteger();
        while (payload.getRemainingSize() >= 88)
        {
            Tidal tidal = new Tidal();
            tidal.lon = payload.getNextNbits(25).toSignedInt() / 60000f;
            tidal.lat = payload.getNextNbits(24).toSignedInt() / 60000f;
            tidal.from_hour = payload.getNextNbits(5).toInteger();
            tidal.from_min = payload.getNextNbits(6).toInteger();
            tidal.to_hour = payload.getNextNbits(5).toInteger();
            tidal.to_min = payload.getNextNbits(6).toInteger();
            tidal.cdir = payload.getNextNbits(9).toInteger();
            tidal.cspeed = payload.getNextNbits(8).toInteger() / 10.f;
            tidals.add(tidal);
        }
    }
}
