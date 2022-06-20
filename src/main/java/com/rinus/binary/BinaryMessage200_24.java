package com.rinus.binary;
import com.rinus.decoder.Payload;
import java.util.ArrayList;
public class BinaryMessage200_24 implements BinaryMessage{
    private String country;
    private ArrayList<Gauge> gauges = new ArrayList<>();
    @Override
    public void parse(Payload payload) {
        country = payload.getNextNbits(2).toSixBitAscii();
        while (payload.getRemainingSize() >= 25)
        {
            Gauge gauge = new Gauge();
            gauge.id = payload.getNextNbits(11).toInteger();
            gauge.level = payload.getNextNbits(14).toInteger();
            gauges.add(gauge);
        }
    }
}
