package com.rinus.binary.sensorreporttypes;

import com.rinus.decoder.Payload;

public class SiteLocation extends SensorReport {
    private float lon;
    private float lat;
    private int alt;
    private int owner;
    private int timeout;

    @Override
    public void parse(Payload payload) {

    }
}
