package com.rinus.binary.sensorreporttypes;
import com.rinus.decoder.Payload;

public class SeaState extends SensorReport {
    private float swheight;
    private int swperiod;
    private int swelldir;
    private int seastate;
    private int swelltype;
    private float watertemp;
    private float distance1;
    private int depthtype;
    private float waveheight;
    private int waveperiod;
    private int wavedir;
    private int wavetype;
    private float salinity;
    @Override
    public void parse(Payload payload) {

    }
}
