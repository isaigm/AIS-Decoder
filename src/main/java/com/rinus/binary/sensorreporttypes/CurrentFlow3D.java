package com.rinus.binary.sensorreporttypes;
import com.rinus.decoder.Payload;

public class CurrentFlow3D extends SensorReport {
    private float cnorth1;
    private float ceast1;
    private float cup1;
    private int cdepth1;
    private float cnorth2;
    private float ceast2;
    private float cup2;
    private int cdepth2;
    private int sensortype;
    @Override
    public void parse(Payload payload) {

    }
}
