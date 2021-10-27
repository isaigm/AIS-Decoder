package binary;

import binary.BinaryMessage;

import java.util.ArrayList;

public class BinaryMessage1_14 implements BinaryMessage {
    private int month;
    private int day;
    private final ArrayList<Tidal> tidals = new ArrayList<>();
    @Override
    public void parse(Payload payload) {
        month = payload.getNextNbits(4).toInteger();
        day = payload.getNextNbits(5).toInteger();
        while (payload.getRemainingSize() >= 93)
        {
            Tidal tidal = new Tidal();
            tidal.lat = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
            tidal.lon = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
            tidal.from_hour = payload.getNextNbits(5).toInteger();
            tidal.from_min = payload.getNextNbits(6).toInteger();
            tidal.to_hour = payload.getNextNbits(5).toInteger();
            tidal.to_min = payload.getNextNbits(6).toInteger();
            tidal.cdir = payload.getNextNbits(9).toInteger();
            tidal.cspeed = payload.getNextNbits(7).toInteger() / 10.f;
            tidals.add(tidal);
        }
    }
}
