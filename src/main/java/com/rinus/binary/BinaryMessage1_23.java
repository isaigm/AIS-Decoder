package com.rinus.binary;
import com.rinus.decoder.Payload;
import com.rinus.decoder.BitString;
public class BinaryMessage1_23 implements BinaryMessage {
    private int linkage;
    private int notice;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int duration;

    private int subarea_type1;
    private BitString shape_data1;

    private int subarea_type2;
    private BitString shape_data2;

    private int subarea_type3;
    private BitString shape_data3;

    private int subarea_type4;
    private BitString shape_data4;

    private int subarea_type5;
    private BitString shape_data5;

    private int subarea_type6;
    private BitString shape_data6;

    private int subarea_type7;
    private BitString shape_data7;

    private int subarea_type8;
    private BitString shape_data8;

    private int subarea_type9;
    private BitString shape_data9;

    private int subarea_type10;
    private BitString shape_data10;

    @Override
    public void parse(Payload payload) {
        linkage = payload.getNextNbits(10).toInteger();
        notice = payload.getNextNbits(7).toInteger();
        month = payload.getNextNbits(4).toInteger();
        day = payload.getNextNbits(5).toInteger();
        hour = payload.getNextNbits(5).toInteger();
        minute = payload.getNextNbits(6).toInteger();
        duration = payload.getNextNbits(18).toInteger();

        subarea_type1 = payload.getNextNbits(3).toInteger();
        shape_data1 = payload.getNextNbits(84);

        subarea_type2 = payload.getNextNbits(3).toInteger();
        shape_data2 = payload.getNextNbits(84);

        subarea_type3 = payload.getNextNbits(3).toInteger();
        shape_data3 = payload.getNextNbits(84);

        subarea_type4 = payload.getNextNbits(3).toInteger();
        shape_data4 = payload.getNextNbits(84);

        subarea_type5 = payload.getNextNbits(3).toInteger();
        shape_data5 = payload.getNextNbits(84);

        subarea_type6 = payload.getNextNbits(3).toInteger();
        shape_data6 = payload.getNextNbits(84);

        subarea_type7 = payload.getNextNbits(3).toInteger();
        shape_data7  = payload.getNextNbits(84);

        subarea_type8 = payload.getNextNbits(3).toInteger();
        shape_data8 = payload.getNextNbits(84);

        subarea_type9 = payload.getNextNbits(3).toInteger();
        shape_data9 = payload.getNextNbits(84);

        subarea_type10 = payload.getNextNbits(3).toInteger();
        shape_data10 = payload.getNextNbits(84);
    }
}
