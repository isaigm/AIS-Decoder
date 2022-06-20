package com.rinus.binary.sensorreporttypes;
import com.rinus.decoder.Payload;

public class AirGapDraft extends SensorReport {
    private float airdraught;
    private float airgap;
    private int gaptrend;
    private float fairgap;
    private int day;
    private int hour;
    private int minute;
    @Override
    public void parse(Payload payload) {

    }
}
