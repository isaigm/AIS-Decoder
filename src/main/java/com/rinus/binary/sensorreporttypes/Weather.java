package com.rinus.binary.sensorreporttypes;
import com.rinus.decoder.Payload;

public class Weather extends SensorReport {
    private int temperature;
    private int sensortype;
    private int preciptype;
    private float visibility;
    private int dewpoint;
    private int dewtype;
    private int pressure;
    private int pressuretend;
    private int pressuretype;
    private float salinity;
    @Override
    public void parse(Payload payload) {

    }
}
