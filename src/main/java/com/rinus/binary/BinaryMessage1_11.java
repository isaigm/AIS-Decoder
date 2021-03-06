package com.rinus.binary;
import com.rinus.decoder.Payload;

public class BinaryMessage1_11 implements BinaryMessage {
    private float lat;
    private float lon;
    private int day;
    private int minute;
    private int hour;
    private int wspeed;
    private int wgust;
    private int wdir;
    private int wgustdir;
    private int temperature;
    private int humidity;
    private int dewpoint;
    private int pressure;
    private int pressuretend;
    private float visibility;
    private int leveltrend;
    private float waterlevel;
    private float cspeed, cspeed2, cspeed3;
    private float cdepth2, cdepth3;
    private int cdir, cdir2, cdir3;
    private float waveheight;
    private int waveperiod;
    private int wavedir;
    private float swellheight;
    private int swellperiod;
    private int swelldir;
    private int seastate;
    private float watertemp;
    private int preciptype;
    private float salinity;
    private int ice;
    @Override
    public void parse(Payload payload) {
        lat = payload.getNextNbits(24).toSignedInt() / 60000f;
        lon = payload.getNextNbits(25).toSignedInt() / 60000f;
        day = payload.getNextNbits(5).toInteger();
        hour = payload.getNextNbits(5).toInteger();
        minute = payload.getNextNbits(6).toInteger();
        wspeed = payload.getNextNbits(7).toInteger();
        wgust = payload.getNextNbits(7).toInteger();
        wdir = payload.getNextNbits(9).toInteger();
        wgustdir = payload.getNextNbits(9).toInteger();
        temperature = payload.getNextNbits(11).toInteger();
        humidity = payload.getNextNbits(7).toInteger();
        dewpoint = payload.getNextNbits(10).toInteger();
        pressure = payload.getNextNbits(9).toInteger();
        pressuretend = payload.getNextNbits(2).toInteger();
        visibility = payload.getNextNbits(8).toInteger() * 0.1f;
        waterlevel = payload.getNextNbits(9).toSignedInt() * 0.1f;
        leveltrend = payload.getNextNbits(2).toInteger();
        cspeed = payload.getNextNbits(8).toInteger() * 0.1f;
        cdir = payload.getNextNbits(9).toInteger();
        cspeed2 = payload.getNextNbits(8).toInteger() * 0.1f;
        cdir2 = payload.getNextNbits(9).toInteger();
        cdepth2 = payload.getNextNbits(5).toInteger() * 0.1f;
        cspeed3 = payload.getNextNbits(8).toInteger() * 0.1f;
        cdir3 = payload.getNextNbits(9).toInteger();
        cdepth3 = payload.getNextNbits(5).toInteger() * 0.1f;
        waveheight = payload.getNextNbits(8).toInteger() * 0.1f;
        waveperiod = payload.getNextNbits(6).toInteger();
        wavedir = payload.getNextNbits(9).toInteger();
        swellheight = payload.getNextNbits(8).toInteger() * 0.1f;
        swellperiod = payload.getNextNbits(6).toInteger();
        swelldir = payload.getNextNbits(9).toInteger();
        seastate = payload.getNextNbits(4).toInteger();
        watertemp = payload.getNextNbits(10).toSignedInt() * 0.1f;
        preciptype = payload.getNextNbits(3).toInteger();
        salinity = payload.getNextNbits(9).toInteger() * 0.1f;
        ice = payload.getNextNbits(2).toInteger();
    }
}
