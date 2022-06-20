package com.rinus.binary.sensorreporttypes;
import com.rinus.decoder.Payload;

public class Salinity extends SensorReport {
    private float watertemp;
    private float conductivity;
    private float pressure;
    private float salinity;
    private int salinitytype;
    private int sensortype;
    @Override
    public void parse(Payload payload) {

    }
}
