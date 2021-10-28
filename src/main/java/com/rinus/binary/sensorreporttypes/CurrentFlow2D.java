package com.rinus.binary.sensorreporttypes;

import com.rinus.decoder.Payload;

public class CurrentFlow2D extends SensorReport {
    private float cspeed1;
    private int cdir1;
    private int cdepth1;
    private float cspeed2;
    private int cdir2;
    private int cdepth2;
    private float cspeed3;
    private int cdir3;
    private int cdepth3;
    private int sensortype;
    @Override
    public void parse(Payload payload) {

    }
}
