package com.rinus.binary.sensorreporttypes;
import com.rinus.decoder.Payload;

public class HorizontalCurrentFlow extends SensorReport {
    private int bearing1;
    private int distance1;
    private float speed1;
    private int direction1;
    private int depth1;

    private int bearing2;
    private int distance2;
    private float speed2;
    private int direction2;
    private int depth22;

    @Override
    public void parse(Payload payload) {

    }
}
