package com.rinus.binary;
import com.rinus.binary.sensorreporttypes.Report;
import com.rinus.decoder.Payload;
import java.util.ArrayList;

public class BinaryMessage1_26 implements BinaryMessage{
    private final ArrayList<Report> reports = new ArrayList<>();
    @Override
    public void parse(Payload payload) {
        while (payload.getRemainingSize() >= 112)
        {
            Report report = new Report();
            report.sensor = payload.getNextNbits(4).toInteger();
            report.day = payload.getNextNbits(5).toInteger();
            report.hour = payload.getNextNbits(5).toInteger();
            report.minute = payload.getNextNbits(6).toInteger();
            report.site = payload.getNextNbits(7).toInteger();
            report.sensor_payload = new Payload(payload.getNextNbits(85));

            reports.add(report);
        }
    }
}
