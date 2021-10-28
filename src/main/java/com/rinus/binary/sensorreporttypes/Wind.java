package com.rinus.binary.sensorreporttypes;

import com.rinus.decoder.Payload;

public class Wind extends SensorReport {
    private int wspeed;
    private int wgust;
    private int wdir;
    private int wgustdir;
    private int sensortype;
    private int fwspeed;
    private int fwgust;
    private int fwdir;
    private int day;
    private int hour;
    private int minute;
    private int duration;
    @Override
    public void parse(Payload payload) {

    }
}
