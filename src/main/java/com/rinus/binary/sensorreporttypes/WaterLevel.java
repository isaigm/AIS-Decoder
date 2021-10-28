package com.rinus.binary.sensorreporttypes;

import com.rinus.decoder.Payload;

public class WaterLevel extends SensorReport {
    private int absolute;
    private int level;
    private int leveltrend;
    private int datum;
    private int sensortype;
    private int absolute2;
    private int level2;
    private int day;
    private int hour;
    private int minute;
    private int duration;
    @Override
    public void parse(Payload payload) {

    }
}
