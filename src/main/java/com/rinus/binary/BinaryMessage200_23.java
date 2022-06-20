package com.rinus.binary;
import com.rinus.decoder.Payload;
public class BinaryMessage200_23 implements BinaryMessage {
    private int start_year;
    private int start_month;
    private int start_day;
    private int end_year;
    private int end_month;
    private int end_day;
    private int start_hour;
    private int start_minute;
    private int end_hour;
    private int end_minute;
    private float start_lon;
    private float start_lat;
    private float end_lon;
    private float end_lat;
    private int type;
    private int min;
    private int max;
    private int intensity;
    private int wind;
    @Override
    public void parse(Payload payload) {
        start_year = payload.getNextNbits(8).toInteger();
        start_month = payload.getNextNbits(4).toInteger();
        start_day = payload.getNextNbits(5).toInteger();
        end_year = payload.getNextNbits(8).toInteger();
        end_month = payload.getNextNbits(4).toInteger();
        end_day = payload.getNextNbits(5).toInteger();
        start_hour = payload.getNextNbits(5).toInteger();
        start_minute = payload.getNextNbits(6).toInteger();
        end_hour = payload.getNextNbits(5).toInteger();
        end_minute = payload.getNextNbits(6).toInteger();
        start_lon = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
        start_lat = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
        end_lon = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
        end_lat = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
        type = payload.getNextNbits(4).toInteger();
        min = payload.getNextNbits(9).toInteger();
        max = payload.getNextNbits(9).toInteger();
        intensity = payload.getNextNbits(2).toInteger();
        wind = payload.getNextNbits(4).toInteger();
    }
}
